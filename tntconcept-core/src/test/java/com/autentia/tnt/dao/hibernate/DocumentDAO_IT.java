package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Document;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.DocumentSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DocumentDAO_IT extends IntegrationTest {

    final DocumentDAO documentDAO;

    public DocumentDAO_IT() { documentDAO = (DocumentDAO) SpringUtils.getSpringBean("daoDocument");}

    @Test
    public void loadByIdShouldGetDocument() {
        final int documentId = 1;

        final Document document = documentDAO.loadById(documentId);

        assertEquals("Test", document.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullDocument() {
        final int documentId = Integer.MAX_VALUE;

        final Document document = documentDAO.loadById(documentId);

        assertNull(document);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultDocument() {
        Document document = createDocument();
        documentDAO.insert(document);

        List<Document> documents = documentDAO.search(new SortCriteria());

        assert(documents.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedDocument() {
        Document document = createDocument();
        documentDAO.insert(document);

        DocumentSearch documentSearch = new DocumentSearch();
        documentSearch.setName(document.getName());
        List<Document> documents = documentDAO.search(documentSearch, new SortCriteria());

        assert(documents.size() == 1);
    }

    @Test
    public void updateShouldChangeDocumentName() {
        Document documentToUpdate = documentDAO.getById(1);
        documentToUpdate.setName("Update");

        documentDAO.update(documentToUpdate);

        Document updatedDocument = documentDAO.getById(1);
        assertEquals("Update", updatedDocument.getName());
    }

    @Test
    public void updateShouldChangeDocumentNameAndReturnTheDocument() {
        Document documentToUpdate = documentDAO.getById(1);
        documentToUpdate.setName("Update");

        documentDAO.updateAndRet(documentToUpdate);

        Document updatedDocument = documentDAO.getById(1);
        assertEquals(documentToUpdate, updatedDocument);
    }

    @Test
    public void deleteShouldRemoveDocument() {
        Document documentToDelete = documentDAO.getById(1);

        documentDAO.delete(documentToDelete);

        assertNull(documentDAO.getById(1));
    }

    private Document createDocument() {
        Document document = new Document();
        document.setName("Test 2");
        document.setDescription("Tests");
        document.setCreationDate(Date.from(Instant.now()));

        return document;
    }
}