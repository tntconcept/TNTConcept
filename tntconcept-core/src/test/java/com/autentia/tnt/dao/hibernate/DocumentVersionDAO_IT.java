package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.DocumentVersion;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.DocumentVersionSearch;
import com.autentia.tnt.test.utils.DocumentForTesting;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DocumentVersionDAO_IT extends IntegrationTest {

    final DocumentVersionDAO documentVersionDAO;

    public DocumentVersionDAO_IT() {
        documentVersionDAO = (DocumentVersionDAO) SpringUtils.getSpringBean("daoDocumentVersion");
    }

    @Test
    public void loadByIdShouldGetDocumentVersion() {
        final int documentVersionId = 1;

        final DocumentVersion documentVersion = documentVersionDAO.loadById(documentVersionId);

        assertEquals("1", documentVersion.getVersion());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullDocument() {
        final int documentVersionId = Integer.MAX_VALUE;

        final DocumentVersion documentVersion = documentVersionDAO.loadById(documentVersionId);

        assertNull(documentVersion);
    }

    @Test
    public void shouldGetById() {
        final DocumentVersion result = documentVersionDAO.getById(1);

        assertEquals("1", result.getVersion());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExists() {
        final DocumentVersion result = documentVersionDAO.getById(Integer.MAX_VALUE);

        assertNull(result);
    }

    @Test
    public void searchShouldFinDocumentVersions() {
        List<DocumentVersion> documentVersions = documentVersionDAO.search(new SortCriteria());

        assert (documentVersions.size() > 0);
    }

    @Test
    public void searchShouldFinDocumentVersionsByCriteria() {
        final DocumentForTesting document = new DocumentForTesting();
        document.setId(1);
        final DocumentVersionSearch documentVersionSearch = new DocumentVersionSearch();
        documentVersionSearch.setDocument(document);

        final List<DocumentVersion> result = documentVersionDAO.search(documentVersionSearch, new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void updateShouldChangeDocumentVersion() {
        final String expectedVersion = "2";
        final DocumentVersion documentVersion = documentVersionDAO.getById(1);
        documentVersion.setVersion(expectedVersion);

        documentVersionDAO.update(documentVersion);
        final DocumentVersion result = documentVersionDAO.getById(1);

        assertEquals(expectedVersion, result.getVersion());
    }

    @Test
    public void shouldNotFindDocumentVersionAfterDelete() {
        final DocumentVersion documentVersion = documentVersionDAO.getById(1);

        documentVersionDAO.delete(documentVersion);
        final DocumentVersion result = documentVersionDAO.getById(1);

        assertNull(result);
    }

    @Test
    public void insertShouldPersistDocumentVersion() {
        final String expectedVersion = "5";
        final DocumentVersion documentVersion = createDocumentVersion(expectedVersion);

        documentVersionDAO.insert(documentVersion);
        final List<DocumentVersion> result = documentVersionDAO.search(new SortCriteria());

        assertEquals(expectedVersion, result.get(result.size() - 1).getVersion());
    }

    private DocumentVersion createDocumentVersion(String version) {
        DocumentForTesting document = new DocumentForTesting();
        document.setId(1);

        DocumentVersion documentVersion = new DocumentVersion();
        documentVersion.setVersion(version);
        documentVersion.setDocumentPath("");
        documentVersion.setDocument(document);
        documentVersion.setCreationDate(Date.from(Instant.now()));

        return documentVersion;
    }
}
