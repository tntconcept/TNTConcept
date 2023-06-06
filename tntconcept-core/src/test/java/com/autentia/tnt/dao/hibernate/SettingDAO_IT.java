package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Setting;
import com.autentia.tnt.businessobject.SettingType;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.SettingSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SettingDAO_IT extends IntegrationTest {
    final SettingDAO settingDAO;

    public SettingDAO_IT() {
        settingDAO = (SettingDAO) SpringUtils.getSpringBean("daoSetting");
    }

    @Test
    public void loadByIdShouldLoadSetting() {
        final int settingId = 1;

        final Setting setting = settingDAO.loadById(settingId);

        assertEquals("Test", setting.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullSetting() {
        final int settingId = Integer.MAX_VALUE;

        final Setting setting = settingDAO.loadById(settingId);

        assertNull(setting);
    }

    @Test
    public void getByIdShouldGetSetting() {
        final int settingId = 1;

        final Setting setting = settingDAO.getById(settingId);

        assertEquals("Test", setting.getName());
    }

    @Test
    public void getByIdShouldGetNullSetting() {
        final int settingId = Integer.MAX_VALUE;

        final Setting setting = settingDAO.getById(settingId);

        assertNull(setting);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultSetting() {
        Setting setting = createSetting();
        settingDAO.insert(setting);

        List<Setting> settings = settingDAO.search(new SortCriteria());

        assert(settings.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedSetting() {
        Setting setting = createSetting();
        settingDAO.insert(setting);

        SettingSearch settingSearch = new SettingSearch();
        settingSearch.setName(setting.getName());
        List<Setting> settings = settingDAO.search(settingSearch, new SortCriteria());

        assert(settings.size() == 1);
    }

    @Test
    public void updateShouldChangeSettingName() {
        Setting settingToUpdate = settingDAO.getById(1);
        settingToUpdate.setName("Update");

        settingDAO.update(settingToUpdate);

        Setting updatedSetting = settingDAO.getById(1);
        assertEquals("Update", updatedSetting.getName());
    }

    @Test
    public void deleteShouldRemoveSetting() {
        Setting settingToDelete = settingDAO.getById(1);

        settingDAO.delete(settingToDelete);

        assertNull(settingDAO.getById(1));
    }

    private Setting createSetting() {
        Setting setting = new Setting();
        setting.setType(SettingType.STRING);
        setting.setName("Setting");
        setting.setValue("");
        setting.setOwnerId(1);
        setting.setDepartmentId(1);

        return setting;
    }
}