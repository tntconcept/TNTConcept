package com.autentia.tnt.manager.activity;

import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.businessobject.ProjectRole;
import com.autentia.tnt.dao.hibernate.ActivityDAO;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActivityManagerTest{
    ActivityManager activityManager = new ActivityManager(mock(ActivityDAO.class));
    final ProjectRole workingRoleProject1 = mock(ProjectRole.class);
    final ProjectRole workingRoleProject2 = mock(ProjectRole.class);
    final ProjectRole notWorkingRoleProject1 = mock(ProjectRole.class);
    final ProjectRole notWorkingRoleProject2 = mock(ProjectRole.class);

    final static ApplicationContext ctx = mock(ApplicationContext.class);

    @Before
    public void init(){
        when(workingRoleProject1.getIsWorkingTime()).thenReturn(true);
        when(workingRoleProject2.getIsWorkingTime()).thenReturn(true);
        when(notWorkingRoleProject1.getIsWorkingTime()).thenReturn(false);
        when(notWorkingRoleProject2.getIsWorkingTime()).thenReturn(false);
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