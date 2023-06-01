package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.ActivitySearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.ProjectRoleForTesting;
import com.autentia.tnt.test.utils.UserForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ActivityDAO_IT extends IntegrationTest {

    final ActivityDAO activityDAO;

    public ActivityDAO_IT() {
        activityDAO = (ActivityDAO) SpringUtils.getSpringBean("daoActivity");
    }

    @Test
    public void loadByIdShouldLoadActivity() {
        final int activityId = 1;

        final Activity activity = activityDAO.loadById(activityId);

        assertEquals("Test", activity.getDescription());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullActivity() {
        final int activityId = Integer.MAX_VALUE;

        final Activity activity = activityDAO.loadById(activityId);

        assertNull(activity);
    }

    @Test
    public void getByIdShouldGetActivity() {
        final int activityId = 1;

        final Activity activity = activityDAO.getById(activityId);

        assertEquals("Test", activity.getDescription());
    }

    @Test
    public void getByIdShouldGetNullActivity() {
        final int activityId = Integer.MAX_VALUE;

        final Activity activity = activityDAO.getById(activityId);

        assertNull(activity);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultActivity() {
        Activity activity = createActivity();
        activityDAO.insert(activity);

        List<Activity> activities = activityDAO.search(new SortCriteria());

        assert(activities.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedActivity() {
        Activity activity = createActivity();
        activityDAO.insert(activity);

        ActivitySearch activitySearch = new ActivitySearch();
        activitySearch.setDescription(activity.getDescription());
        List<Activity> activities = activityDAO.search(activitySearch, new SortCriteria());

        assert(activities.size() == 1);
    }

    @Test
    public void updateShouldChangeActivityName() {
        Activity activityToUpdate = activityDAO.getById(1);
        activityToUpdate.setDescription("Update");

        activityDAO.update(activityToUpdate);

        Activity updatedActivity = activityDAO.getById(1);
        assertEquals("Update", updatedActivity.getDescription());
    }

    @Test
    public void deleteShouldRemoveActivity() {
        Activity activityToDelete = activityDAO.getById(1);

        activityDAO.delete(activityToDelete);

        assertNull(activityDAO.getById(1));
    }

    private Activity createActivity() {
        UserForTesting user = new UserForTesting();
        user.setId(1);

        ProjectRoleForTesting projectRole = new ProjectRoleForTesting();
        projectRole.setId(1);

        Activity activity = new Activity();
        activity.setStart(new Date());
        activity.setDuration(30);
        activity.setBillable(true);
        activity.setRole(projectRole);
        activity.setDescription("IntegrationTest");
        activity.setHasEvidences(false);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 30);
        activity.setEnd(calendar.getTime());

        activity.setApprovalState(ActivityApprovalState.NA);
        activity.setOwnerId(1);
        activity.setDepartmentId(1);

        return activity;
    }
}