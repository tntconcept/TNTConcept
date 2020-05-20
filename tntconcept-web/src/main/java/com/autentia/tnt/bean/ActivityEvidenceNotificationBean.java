package com.autentia.tnt.bean;

import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.search.ActivitySearch;
import com.autentia.tnt.dao.search.UserSearch;
import com.autentia.tnt.mail.MailService;
import com.autentia.tnt.manager.activity.ActivityManager;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.util.ConfigurationUtil;
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityEvidenceNotificationBean {
    private static final Log log = LogFactory.getLog(ContractExpirationNotificationBean.class);

    private MailService mailService;


    public ActivityEvidenceNotificationBean(MailService mailService) {
        this.mailService = mailService;
    }

    public void checkActivitiesWithNoEvidence() throws MessagingException {
        log.info("Checking for users with no activity evidence images attached for the past 7 days");

        authenticateAs(ConfigurationUtil.getDefault().getAdminUser());

        Date oneWeekAgo = Date.from(LocalDate.now().plusDays(-7).atStartOfDay(ZoneId.systemDefault()).toInstant());

        UserSearch userSearch = new UserSearch();
        userSearch.setActive(true);
        List<User> users = UserManager.getDefault().getAllEntities(userSearch,null);

        for (User user: users) {
            ActivitySearch search = new ActivitySearch();
            search.setStartStartDate(oneWeekAgo);
            search.setUser(user);
            List<Activity> activities = ActivityManager.getDefault().getAllEntities(search, null);
            activities = activities.stream().filter(activity -> activity.getRole().getRequireEvidence()).collect(Collectors.toList());

            if (activities.isEmpty()) {
                break;
            }

            boolean anyHasImage = false;
            for (Activity activity : activities) {
                if (activity.isHasImage()) {
                    anyHasImage = true;
                    break;
                }
            }

            if (!anyHasImage && user.getEmail() != null) {
                String emailSubject = ConfigurationUtil.getDefault().getNoEvidenceInActivityMailSubject();
                String emailBody = ConfigurationUtil.getDefault().getNoEvidenceInActivityMailBody();
                mailService.send(user.getEmail(), emailSubject, emailBody);
                log.info("Email sent to: " + user.getEmail());
            }
        }
    }

    // TODO Extract to a Security Utils Class or similar
    private void authenticateAs(final String userLogin) {
        final Principal principal = (Principal) AuthenticationManager.getDefault().loadUserByUsername(userLogin);
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, principal.getUser().getPassword(),principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
