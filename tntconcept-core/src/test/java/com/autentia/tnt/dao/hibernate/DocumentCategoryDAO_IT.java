package com.autentia.tnt.dao.hibernate;


import com.autentia.tnt.businessobject.DocumentCategory;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.DocumentCategorySearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DocumentCategoryDAO_IT extends IntegrationTest {
    final DocumentCategoryDAO documentCategoryDAO;

    public DocumentCategoryDAO_IT() { documentCategoryDAO =
            (DocumentCategoryDAO) SpringUtils.getSpringBean("daoDocumentCategory");}

    @Test
    public void loadByIdShouldLoadDocumentCategory() {
        final int documentCategoryId = 1;

        final DocumentCategory documentCategory = documentCategoryDAO.loadById(documentCategoryId);

        assertEquals("Documentos de Calidad", documentCategory.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullDocumentCategory() {
        final int documentCategoryId = Integer.MAX_VALUE;

        final DocumentCategory documentCategory = documentCategoryDAO.loadById(documentCategoryId);

        assertNull(documentCategory);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultDocumentCategory() {
        DocumentCategory documentCategory = createDocumentCategory();
        documentCategoryDAO.insert(documentCategory);

        List<DocumentCategory> documentCategories = documentCategoryDAO.search(new SortCriteria());

        assert(documentCategories.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedDocumentCategory() {
        DocumentCategory documentCategory = createDocumentCategory();
        documentCategoryDAO.insert(documentCategory);

        DocumentCategorySearch documentCategorySearch = new DocumentCategorySearch();
        documentCategorySearch.setName(documentCategory.getName());
        List<DocumentCategory> documentCategories = documentCategoryDAO.search(documentCategorySearch, new SortCriteria());

        assert(documentCategories.size() == 1);
    }

    @Test
    public void updateShouldChangeDocumentCategoryName() {
        DocumentCategory documentCategoryToUpdate = documentCategoryDAO.getById(1);
        documentCategoryToUpdate.setName("Update");

        documentCategoryDAO.update(documentCategoryToUpdate);

        DocumentCategory updatedDocumentCategory = documentCategoryDAO.getById(1);
        assertEquals("Update", updatedDocumentCategory.getName());
    }

    @Test
    public void deleteShouldRemoveDocumentCategory() {
        DocumentCategory documentCategoryToDelete = documentCategoryDAO.getById(1);

        documentCategoryDAO.delete(documentCategoryToDelete);

        assertNull(documentCategoryDAO.getById(1));
    }


    private DocumentCategory createDocumentCategory() {
        DocumentCategory documentCategory = new DocumentCategory();
        documentCategory.setName("Document Category");
        documentCategory.setDescription("Tests");

        return documentCategory;

    }
}