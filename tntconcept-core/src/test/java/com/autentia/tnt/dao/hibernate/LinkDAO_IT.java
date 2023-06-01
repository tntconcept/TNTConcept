package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Link;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.LinkSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LinkDAO_IT extends IntegrationTest {
    private final LinkDAO linkDAO;
    private final String linkText = "link";

    public LinkDAO_IT() {
        linkDAO = (LinkDAO) SpringUtils.getSpringBean("daoLink");
    }


    @Test
    public void shouldLoadById() {
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
        final List<Link> result = linkDAO.search(new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void searchShouldFindByCriteria() {
        final LinkSearch linkSearch = new LinkSearch();
        linkSearch.setLink(linkText);

        final List<Link> result = linkDAO.search(linkSearch, new SortCriteria());

        assert (result.size() > 0);
    }

    @Test
    public void updateShouldChangeObject() {
        final String updatedLink = "change";
        final Link link = linkDAO.getById(1);
        link.setLink(updatedLink);

        linkDAO.update(link);
        final Link result = linkDAO.getById(1);

        assertEquals(updatedLink, result.getLink());
    }

    @Test
    public void shouldNotLoadByIdAfterDelete() {
        final Link link = linkDAO.getById(1);

        linkDAO.delete(link);

        assertThrows(DataAccException.class, () -> {
            final Link result = linkDAO.loadById(1);
        });
    }

}
