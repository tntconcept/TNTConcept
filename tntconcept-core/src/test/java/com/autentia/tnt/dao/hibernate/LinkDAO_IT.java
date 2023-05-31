package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Link;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.LinkSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class LinkDAO_IT extends IntegrationTest {
    private final LinkDAO linkDAO;
    private final String linkText = "link";

    public LinkDAO_IT() {
        linkDAO = (LinkDAO) SpringUtils.getSpringBean("daoLink");
    }

    @Override
    public void rollback() throws SQLException {
        super.rollback();
        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().connection().prepareStatement("ALTER TABLE Link AUTO_INCREMENT=0").execute();
    }

    @Test
    public void shouldLoadById() {
        insertLink(linkText);

        final Link result = linkDAO.loadById(1);

        assertEquals(linkText, result.getLink());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrownAnException() {
        final Link result = linkDAO.loadById(100);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        insertLink(linkText);

        final Link result = linkDAO.getById(1);

        assertEquals(linkText, result.getLink());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExists() {
        final Link result = linkDAO.getById(100);

        assertNull(result);
    }

    @Test
    public void searchShouldFindLinks() {
        insertLink(linkText);

        final List<Link> result = linkDAO.search(new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void searchShouldFindByCriteria() {
        insertLink(linkText);
        final LinkSearch linkSearch = new LinkSearch();
        linkSearch.setLink(linkText);

        final List<Link> result = linkDAO.search(linkSearch, new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void updateShouldChangeObject() {
        final String updatedLink = "change";
        insertLink(linkText);
        final Link link = linkDAO.getById(1);
        link.setLink(updatedLink);

        linkDAO.update(link);
        final Link result = linkDAO.getById(1);

        assertEquals(updatedLink, result.getLink());
    }

    @Test
    public void shouldNotLoadByIdAfterDelete() {
        insertLink(linkText);
        final Link link = linkDAO.loadById(1);

        linkDAO.delete(link);

        assertThrows(DataAccException.class, () -> {
            final Link result = linkDAO.loadById(1);
        });
    }


    private void insertLink(String linkText) {
        Link link = new Link();
        link.setLink(linkText);
        link.setUser("user");

        linkDAO.insert(link);
    }

}
