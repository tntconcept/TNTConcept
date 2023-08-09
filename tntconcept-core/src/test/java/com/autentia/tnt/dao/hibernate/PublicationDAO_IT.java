package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Publication;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.PublicationSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.test.utils.MagazineForTesting;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PublicationDAO_IT extends IntegrationTest {
    private final PublicationDAO publicationDAO;
    private final String nameFirstRow = "publicationname";

    public PublicationDAO_IT() {
        publicationDAO = (PublicationDAO) SpringUtils.getSpringBean("daoPublication");
    }

    @Test
    public void shouldLoadById() {
        final Publication result = publicationDAO.loadById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrowAnExceptionWhenIdDoesntExists() {
        final Publication result = publicationDAO.loadById(100);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        final Publication result = publicationDAO.getById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExists() {
        final Publication result = publicationDAO.getById(100);

        assertNull(result);
    }

    @Test
    public void searchShouldFindPublications() {
        final List<Publication> result = publicationDAO.search(new SortCriteria());

        assertFalse(result.isEmpty());
    }

    @Test
    public void searchShouldFindPublicationsByCriteria() {
        final PublicationSearch publicationSearch = new PublicationSearch();
        publicationSearch.setName(nameFirstRow);

        final List<Publication> result = publicationDAO.search(publicationSearch, new SortCriteria());

        assertFalse(result.isEmpty());
    }

    @Test
    public void updateShouldChangePublication() {
        final String expectedName = "change";
        final Publication publication = publicationDAO.getById(1);
        publication.setName(expectedName);

        publicationDAO.update(publication);
        final Publication result = publicationDAO.getById(1);

        assertEquals(expectedName, result.getName());
    }

    @Test
    public void shouldNotFindPublicationAfterDelete() {
        final Publication publication = publicationDAO.getById(1);

        publicationDAO.delete(publication);
        final Publication result = publicationDAO.getById(1);

        assertNull(result);
    }

    @Test
    public void insertShouldPersistPublication() {
        final String expectedName = "newPublication";
        final Publication publication = createPublication(expectedName);
        publicationDAO.insert(publication);

        final List<Publication> result = publicationDAO.search(new SortCriteria());

        assertEquals(expectedName, result.get(result.size()-1).getName());
    }

    private Publication createPublication(String name) {
        MagazineForTesting magazine = new MagazineForTesting();
        magazine.setId(1);

        final Publication publication = new Publication();
        publication.setName(name);
        publication.setMagazine(magazine);
        publication.setAccepted(true);
        return publication;
    }
}
