package com.autentia.tnt.bean;

import com.autentia.tnt.businessobject.Link;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.mail.DefaultMailService;
import com.autentia.tnt.manager.admin.LinkManager;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

import javax.faces.context.ExternalContext;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class LinkBeanTest {
	
	final static ApplicationContext ctx = mock(ApplicationContext.class);
	final static UserManager userManager = mock(UserManager.class);
	final static LinkManager linkManager = mock(LinkManager.class);
	final static AuthenticationManager authManager = mock(AuthenticationManager.class);
	final static ConfigurationUtil configurationUtil = mock(ConfigurationUtil.class);

	private static DefaultMailService mailService = Mockito.mock(DefaultMailService.class);
	private static ExternalContext externalContext = Mockito.mock(ExternalContext.class);
	
	private final LinkBean sut = new LinkBean();
	private final LinkBean sutMock = mock(LinkBean.class, CALLS_REAL_METHODS);
	
	@BeforeClass
	public static void setUp() {
		when(ctx.getBean("managerLink")).thenReturn(linkManager);
		when(ctx.getBean("managerUser")).thenReturn(userManager);
		when(ctx.getBean("userDetailsService")).thenReturn(authManager);
		when(ctx.getBean("mailService")).thenReturn(mailService);
		when(ctx.getBean("configuration")).thenReturn(configurationUtil);
		
		SpringUtils.configureTest(ctx);


	}
	
	@Before
	public void setExternalContext() {
		doReturn(externalContext).when(sutMock).getFacesExternalContext();

		HttpServletRequest req = mock(HttpServletRequest.class);
		doReturn(req).when(externalContext).getRequest();
		StringBuffer url = new StringBuffer("http://localhost:8080/tntconcept/passwordChangeForm.jsf");
		doReturn(url).when(req).getRequestURL();
		doReturn("/tntconcept/passwordChangeForm.jsf").when(req).getRequestURI();
		doReturn("/tntconcept").when(req).getContextPath();
	}
	
	@Test
	public void shouldCheckLinkIsOnTime() {
		
		Link onTimeLink = mock(Link.class);

		when(onTimeLink.getInsertDate()).thenReturn(new Date());

		boolean isOnTime = sut.isOnTime(onTimeLink);
		
		assertThat(isOnTime, is(true));
	}
	
	@Test
	public void shouldCheckLinkIsNotOnTime() {
		
		Link outOfTimeLink = new Link();	
		Date yesterday = new Date();
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(yesterday); 
		cal.add(Calendar.DATE, -1);
		yesterday = cal.getTime();
		outOfTimeLink.setInsertDate(yesterday);
		
		boolean isOnTime = sut.isOnTime(outOfTimeLink);
		
		assertThat(isOnTime, is(false));	
	}
	
	@Test
	public void shouldGenerateRandomLink() {
	
		String name = "test";
		
		Link result = sut.generateLink(name);
		
		assertThat(result.getUser(), equalTo(name));
		assertThat(result.getLink(), is(notNullValue()));	
	}
	
	@Test
	public void shouldSendMail() throws MessagingException {

		Link link = new Link();
		link.setLink("dgfjhsadgflkjasghajksdhfk");
		String mailAddress = "test@mail.com";
		
		sutMock.sendMail(link, mailAddress);
				
		verify(mailService).send(mailAddress, "[RESETEO DE CONTRASEÑA] Email de verificación",
				"Haz click en el siguiente link para verificar que eres tú si quieres cambiar la contraseña: http://localhost:8080/tntconcept/linkEmailVerification.jsf?link=dgfjhsadgflkjasghajksdhfk");
	}
	
	@Test
	public void shouldProcessPasswordResetRequest() {

		User testUser = new User();
		testUser.setLogin("testName");
		testUser.setActive(true);
		testUser.setEmail("test@mail.com");
		sutMock.setName("testName");
		Link testLink = new Link();
		testLink.setLink("randomLink");
		doReturn(testUser).when(sutMock).getUserByName("testName");
		doReturn(externalContext).when(sutMock).getFacesExternalContext();
		doReturn(testLink).when(sutMock).generateLink("testName");
		
		String result = sutMock.passwordResetRequest();

		verify(sutMock).sendMail(testLink, "test@mail.com");
		assertThat(result, equalTo("emailSent"));
	}
	
	@Test
	public void shouldDismissPasswordResetRequestWithInactiveUser() {
		
		User testUser = new User();
		testUser.setLogin("testName");
		testUser.setActive(false);		
		sutMock.setName("testName");		
		doReturn(testUser).when(userManager).getUserByLogin("testName");
		
		String result = sutMock.passwordResetRequest();

		verify(sutMock, never()).sendMail((Link) any(), any());
		assertThat(result, equalTo("emailSentFailed"));
		
		
	}
	
	@Test
	public void shouldDismissPasswordResetRequestWithNonExistentUser() {
		sutMock.setName("testName");
		doReturn(new User()).when(userManager).getUserByLogin("testName");
		
		String result = sutMock.passwordResetRequest();

		verify(sutMock, never()).sendMail((Link) any(), any());
		assertThat(result, equalTo("emailSentFailed"));
	}
	
	@Test
	public void shouldResetPasswordWithLdap() {
		User testUser = new User();
		testUser.setPassword("test");
		String[] x = new String[] {"x"};
		String[] y = new String[] {"y"};
		String[] z = new String[] {"z"};
		String[] v = new String[] {"v"};
		String[] w = new String[] {"w"};		
		doReturn(x).when(sutMock).callFacesUtilsFormatMessage("AuthenticationManager.randomWords0");
		doReturn(y).when(sutMock).callFacesUtilsFormatMessage("AuthenticationManager.randomWords1");
		doReturn(z).when(sutMock).callFacesUtilsFormatMessage("AuthenticationManager.randomWords2");
		doReturn(v).when(sutMock).callFacesUtilsFormatMessage("AuthenticationManager.randomWords3");
		doReturn(w).when(sutMock).callFacesUtilsFormatMessage("AuthenticationManager.randomWords4");
		doReturn("changed").when(authManager).resetPasswordExternal(testUser, x, y, z, v, w);
		doReturn(false).when(configurationUtil).isLdapProviderEnabled();
		
		String changedPassword = sutMock.resetPassword(testUser);
		
		verify(userManager).updateEntity(testUser, true);
		assertThat(changedPassword, equalTo("changed"));
	}
	
	@Test
	public void shouldResetPasswordWithDB() {
		User testUser = new User();
		testUser.setPassword("test");
		String[] x = new String[] {"x"};
		String[] y = new String[] {"y"};
		String[] z = new String[] {"z"};
		String[] v = new String[] {"v"};
		String[] w = new String[] {"w"};		
		doReturn(x).when(sutMock).callFacesUtilsFormatMessage("AuthenticationManager.randomWords0");
		doReturn(y).when(sutMock).callFacesUtilsFormatMessage("AuthenticationManager.randomWords1");
		doReturn(z).when(sutMock).callFacesUtilsFormatMessage("AuthenticationManager.randomWords2");
		doReturn(v).when(sutMock).callFacesUtilsFormatMessage("AuthenticationManager.randomWords3");
		doReturn(w).when(sutMock).callFacesUtilsFormatMessage("AuthenticationManager.randomWords4");
		doReturn("changed").when(authManager).resetPasswordExternal(testUser, x, y, z, v, w);
		doReturn(true).when(configurationUtil).isLdapProviderEnabled();
		
		String changedPassword = sutMock.resetPassword(testUser);
		
		verify(userManager, times(0)).updateEntity(testUser, true);
		assertThat(changedPassword, equalTo("changed"));
	}
	
	@Test
	public void shouldCheckLinkAndCallResetPassword() {
		
		User testUser = new User();
		testUser.setLogin("testUser");
		testUser.setActive(true);
		Link testLink = new Link();
		testLink.setLink("linkTest");
		testLink.setUser("testUser");
		testLink.setInsertDate(new Date());
		doReturn(Arrays.asList(testLink)).when(sutMock).getLinksWithLink("linkTest");
		doReturn(testUser).when(sutMock).getUserByName(testLink.getUser());
		doReturn("changedPassword").when(sutMock).resetPassword(testUser);	
		
		String result = sutMock.checkLinkAndResetPassword(testLink.getLink());
		
		assertThat(result, equalTo("Tu nueva contraseña es: <b>changedPassword</b></br> <p>Se te pedirá que la modifiques al entrar por primera vez.</p>"));
	}
	
	@Test
	public void shouldFailWhenCheckingNonExistentLink() {
		doReturn(new ArrayList<Link>()).when(sutMock).getLinksWithLink("linkTest");
		
		String result = sutMock.checkLinkAndResetPassword("linkTest");
		
		assertThat(result, equalTo("<p>El enlace no existe o ha caducado</p>"));
	}
	
	@Test
	public void shouldFailWhenCheckingExpiredLink() {
		Link testLink = new Link();
		testLink.setLink("linkTest");
		testLink.setUser("testUser");	
		Date yesterday = new Date();
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(yesterday); 
		cal.add(Calendar.DATE, -1);
		yesterday = cal.getTime();
		testLink.setInsertDate(yesterday);
		doReturn(Arrays.asList(testLink)).when(sutMock).getLinksWithLink("linkTest");
		
		String result = sutMock.checkLinkAndResetPassword(testLink.getLink());
		
		assertThat(result, equalTo("<p>El enlace no existe o ha caducado</p>"));
	}
	
	@Test
	public void shouldFailWhenCheckingWithInactiveUser() {
		User testUser = new User();
		testUser.setLogin("testUser");
		testUser.setActive(false);
		Link testLink = new Link();
		testLink.setLink("linkTest");
		testLink.setUser("testUser");
		testLink.setInsertDate(new Date());
		doReturn(Arrays.asList(testLink)).when(sutMock).getLinksWithLink("linkTest");
		doReturn(testUser).when(sutMock).getUserByName(testLink.getUser());
		
		String result = sutMock.checkLinkAndResetPassword(testLink.getLink());
		
		assertThat(result, equalTo("<p>El enlace no existe o ha caducado</p>"));
		
	}
	
	@Test
	public void shouldFailWhenCheckingWithNonExistentUser() {
		Link testLink = new Link();
		testLink.setLink("linkTest");
		testLink.setUser("testUser");
		testLink.setInsertDate(new Date());
		doReturn(Arrays.asList(testLink)).when(sutMock).getLinksWithLink("linkTest");
		doReturn(new User()).when(sutMock).getUserByName("testUser");
		
		String result = sutMock.checkLinkAndResetPassword(testLink.getLink());
		
		assertThat(result, equalTo("<p>El enlace no existe o ha caducado</p>"));	
	}
	
}
