package com.autentia.tnt.servlet;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;

import static org.junit.Assert.*;

public class DocumentFinderTest {


    @Rule
    public TemporaryFolder baseFolder = new TemporaryFolder();

    private final DocumentFinder documentFinder = DocumentFinder.getDocumentFinder();


    @Before
    public void setUp() throws IOException {
        baseFolder.newFile("12345.png");
        baseFolder.newFile("12346");
        baseFolder.newFile("77777.pdf");
    }

    @Test
    public void shouldFindADocumentThatExists() {
        DocumentFinder.RequestedDocumentFile requestedDocumentFile = documentFinder.search(baseFolder.getRoot().getAbsolutePath() + "/12345.png");

        assertTrue(requestedDocumentFile.requestedFile().exists());
        assertEquals("12345.png", requestedDocumentFile.requestedFile().getName());
        assertTrue(requestedDocumentFile.original());
    }


    @Test
    public void shouldFindADocumentThatDoesNotExistButExistsCommonName() {
        DocumentFinder.RequestedDocumentFile requestedDocumentFile = documentFinder.search(baseFolder.getRoot().getAbsolutePath() + "/12345.pdf");

        assertTrue(requestedDocumentFile.requestedFile().exists());
        assertEquals("12345.png", requestedDocumentFile.requestedFile().getName());
        assertFalse(requestedDocumentFile.original());

    }

    @Test
    public void shouldNotFindADocumentThatDoesNotExist() {
        DocumentFinder.RequestedDocumentFile requestedDocumentFile = documentFinder.search(baseFolder.getRoot().getAbsolutePath() + "/1234.pdf");

        assertFalse(requestedDocumentFile.requestedFile().exists());
        assertFalse(requestedDocumentFile.original());
    }

    @Test
    public void shouldFindADocumentWithoutExtension() {
        DocumentFinder.RequestedDocumentFile requestedDocumentFile = documentFinder.search(baseFolder.getRoot().getAbsolutePath() + "/12346");

        assertTrue(requestedDocumentFile.requestedFile().exists());
        assertEquals("12346", requestedDocumentFile.requestedFile().getName());
        assertTrue(requestedDocumentFile.original());
    }

    @Test
    public void shouldFindADocumentWithExtensionRequestedWithoutExtension() {
        DocumentFinder.RequestedDocumentFile requestedDocumentFile = documentFinder.search(baseFolder.getRoot().getAbsolutePath() + "/77777");

        assertTrue(requestedDocumentFile.requestedFile().exists());
        assertEquals("77777.pdf", requestedDocumentFile.requestedFile().getName());
        assertFalse(requestedDocumentFile.original());
    }

}
