package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.BulletinBoard;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.BulletinBoardSearch;
import com.autentia.tnt.test.utils.BulletinBoardCategoryForTesting;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.UserForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BulletinBoardDAO_IT extends IntegrationTest {
    final BulletinBoardDAO bulletinBoardDAO;

    public BulletinBoardDAO_IT() {
        bulletinBoardDAO = (BulletinBoardDAO) SpringUtils.getSpringBean("daoBulletinBoard");
    }

    @Test
    public void loadByIdShouldLoadBulletinBoard() {
        final int bulletinBoardId = 1;

        final BulletinBoard bulletinBoard = bulletinBoardDAO.loadById(bulletinBoardId);

        assertEquals("Test", bulletinBoard.getTitle());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullBulletinBoard() {
        final int bulletinBoardId = Integer.MAX_VALUE;

        final BulletinBoard bulletinBoard = bulletinBoardDAO.loadById(bulletinBoardId);

        assertNull(bulletinBoard);
    }

    @Test
    public void getByIdShouldGetBulletinBoard() {
        final int bulletinBoardId = 1;

        final BulletinBoard bulletinBoard = bulletinBoardDAO.getById(bulletinBoardId);

        assertEquals("Test", bulletinBoard.getTitle());
    }

    @Test
    public void getByIdShouldGetNullBulletinBoard() {
        final int bulletinBoardId = Integer.MAX_VALUE;

        final BulletinBoard bulletinBoard = bulletinBoardDAO.getById(bulletinBoardId);

        assertNull(bulletinBoard);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultBulletinBoard() {
        BulletinBoard bulletinBoard = createBulletinBoard();
        bulletinBoardDAO.insert(bulletinBoard);

        List<BulletinBoard> bulletinBoards = bulletinBoardDAO.search(new SortCriteria());

        assert (bulletinBoards.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedBulletinBoard() {
        BulletinBoard bulletinBoard = createBulletinBoard();
        bulletinBoardDAO.insert(bulletinBoard);

        BulletinBoardSearch bulletinBoardSearch = new BulletinBoardSearch();
        bulletinBoardSearch.setTitle(bulletinBoard.getTitle());
        List<BulletinBoard> bulletinBoards = bulletinBoardDAO.search(bulletinBoardSearch, new SortCriteria());

        assert (bulletinBoards.size() == 1);
    }

    @Test
    public void updateShouldChangeBulletinBoardName() {
        BulletinBoard bulletinBoardToUpdate = bulletinBoardDAO.getById(1);
        bulletinBoardToUpdate.setTitle("Update");

        bulletinBoardDAO.update(bulletinBoardToUpdate);

        BulletinBoard updatedBulletinBoard = bulletinBoardDAO.getById(1);
        assertEquals("Update", updatedBulletinBoard.getTitle());
    }

    @Test
    public void deleteShouldRemoveBulletinBoard() {
        BulletinBoard bulletinBoardToDelete = bulletinBoardDAO.getById(1);

        bulletinBoardDAO.delete(bulletinBoardToDelete);

        assertNull(bulletinBoardDAO.getById(1));
    }

    private BulletinBoard createBulletinBoard() {
        BulletinBoardCategoryForTesting bulletinBoardCategory = new BulletinBoardCategoryForTesting();
        bulletinBoardCategory.setId(1);

        UserForTesting user = new UserForTesting();
        user.setId(1);

        BulletinBoard bulletinBoard = new BulletinBoard();
        bulletinBoard.setCategory(bulletinBoardCategory);
        bulletinBoard.setUser(user);
        bulletinBoard.setCreationDate(new Date());
        bulletinBoard.setMessage("");
        bulletinBoard.setTitle("BulletinBoard");
        bulletinBoard.setDocumentPath("");
        bulletinBoard.setDocumentContentType("");
        bulletinBoard.setOwnerId(1);
        bulletinBoard.setDepartmentId(1);

        return bulletinBoard;
    }

}