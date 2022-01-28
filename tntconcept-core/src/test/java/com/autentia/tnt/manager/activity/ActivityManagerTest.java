package com.autentia.tnt.manager.activity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.businessobject.ProjectRole;
import com.autentia.tnt.dao.hibernate.ActivityDAO;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;

public class ActivityManagerTest{

    private static final Integer WORKING_ROLEPROJECT_ID_1 = 1;
    private static final Integer WORKING_ROLEPROJECT_ID_2 = 2;
    private static final Integer NOT_WORKING_ROLEPROJECT_ID_1 = 3;
    private static final Integer NOT_WORKING_ROLEPROJECT_ID_2 = 4;

    private final ConfigurationUtil configurationUtil = mock(ConfigurationUtil.class);
    ActivityManager activityManager = new ActivityManager(mock(ActivityDAO.class), configurationUtil);
    final ProjectRole workingRoleProject1 = mock(ProjectRole.class);
    final ProjectRole workingRoleProject2 = mock(ProjectRole.class);
    final ProjectRole notWorkingRoleProject1 = mock(ProjectRole.class);
    final ProjectRole notWorkingRoleProject2 = mock(ProjectRole.class);

    final static ApplicationContext ctx = mock(ApplicationContext.class);

    @Before
    public void init(){
        when(workingRoleProject1.getId()).thenReturn(WORKING_ROLEPROJECT_ID_1);
        when(workingRoleProject2.getId()).thenReturn(WORKING_ROLEPROJECT_ID_2);
        when(notWorkingRoleProject1.getId()).thenReturn(NOT_WORKING_ROLEPROJECT_ID_1);
        when(notWorkingRoleProject2.getId()).thenReturn(NOT_WORKING_ROLEPROJECT_ID_2);

        when(configurationUtil.getNotWorkingTimeProjectRoleIds()).thenReturn(
                Arrays.asList(NOT_WORKING_ROLEPROJECT_ID_1, NOT_WORKING_ROLEPROJECT_ID_2));

        when(ctx.getBean("configuration")).thenReturn(configurationUtil);
        SpringUtils.configureTest(ctx);

    }

    @Test
    public void shouldWorkedTime(){
        final Activity workingActivity1 = new Activity();
        workingActivity1.setRole(workingRoleProject1);
        workingActivity1.setDuration(100);

        final Activity workingActivity2 = new Activity();
        workingActivity2.setRole(workingRoleProject2);
        workingActivity2.setDuration(150);

        final Activity notWorkingActivity1 = new Activity();
        notWorkingActivity1.setRole(notWorkingRoleProject1);
        notWorkingActivity1.setDuration(11);

        final Activity notWorkingActivity2 = new Activity();
        notWorkingActivity2.setRole(notWorkingRoleProject2);
        notWorkingActivity2.setDuration(12);

        final List<Activity> activities = Arrays.asList(workingActivity1, notWorkingActivity1, workingActivity2,
                notWorkingActivity2);

        Assert.assertEquals( 250L, activityManager.workedTime(activities));
    }

}