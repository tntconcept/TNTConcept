package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Position;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.PositionSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class PositionDAO_IT extends IntegrationTest {
    private final PositionDAO positionDAO;

    public PositionDAO_IT() {
        positionDAO = (PositionDAO) SpringUtils.getSpringBean("daoPosition");
    }

    @NotNull
    private Position createPosition() {
        final Position position = new Position();
        position.setName("Position");
        position.setDescription("PositionDescription");
        position.setEmail("position@test.com");
        position.setFax("positionFax");
        position.setAddress("positionAddress");
        position.setPostalCode("12345");
        position.setCity("positionCity");
        position.setCountry("positionCountry");
        position.setPhone("positionPhone");
        return position;
    }

    @Test
    public void getByIdShouldReturnNull() {
        final int positionId = Integer.MAX_VALUE;

        final Position Position = positionDAO.getById(positionId);

        assertNull(Position);
    }

    @Test
    public void getByIdShouldGetPosition() {
        final int positionId = 1;

        final Position position = positionDAO.getById(positionId);

        assertNotNull(position);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNull() {
        final int positionId = 0;

        final Position position = positionDAO.loadById(positionId);

        assertNull(position);
    }

    @Test
    public void loadByIdShouldGetPosition() {
        final int positionId = 1;

        final Position position = positionDAO.loadById(positionId);

        assertNotNull(position);
    }

    @Test
    public void searchShouldReturnPosition() {
        final PositionSearch positionSearch = new PositionSearch();
        positionSearch.setName("Indefinido");

        final List<Position> positions = positionDAO.search(positionSearch, new SortCriteria());

        assertEquals(1, positions.size());
        assertEquals("Indefinido", positions.get(0).getName());
    }

    @Test
    public void insertPositionShouldGiveInstanceId() {
        final Position position = createPosition();

        positionDAO.insert(position);

        assertTrue(position.getId() > 0);
    }

    @Test
    public void updateShouldChangeName() {
        final Position position = positionDAO.getById(1);
        position.setName("NewPositionName");

        positionDAO.update(position);

        final Position positionUpdated = positionDAO.getById(1);
        assertEquals("NewPositionName", positionUpdated.getName());
    }

    @Test
    public void deleteShouldRemovePosition() {
        final Position position = positionDAO.getById(1);

        positionDAO.delete(position);

        assertNull(positionDAO.getById(1));
    }
}