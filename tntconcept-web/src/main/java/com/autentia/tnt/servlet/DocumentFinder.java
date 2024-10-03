package com.autentia.tnt.servlet;

import java.io.File;

public class DocumentFinder {

    private static DocumentFinder theSingleton;

    private String[] getNameAndExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex == -1 || dotIndex == 0) {
            return new String[]{fileName, ""};
        } else {
            String name = fileName.substring(0, dotIndex);
            String extension = fileName.substring(dotIndex + 1);
            return new String[]{name, extension};
        }
    }

    public RequestedDocumentFile search(String requestedFilePath) {
        File requestedFile = new File(requestedFilePath);

        if (requestedFile.exists()) {
            return new RequestedDocumentFile(requestedFile, true);
        }

        String[] nameAndExtension = getNameAndExtension(requestedFile);

        File[] evidencesFound = requestedFile.getParentFile().listFiles((dir, name) -> name.startsWith(nameAndExtension[0] + "."));

        if (evidencesFound != null && evidencesFound.length > 0) {
            return new RequestedDocumentFile(evidencesFound[0], false);
        } else {
            return new RequestedDocumentFile(requestedFile, false);
        }

    }

    public record RequestedDocumentFile(File requestedFile, boolean original) {
    }

    DocumentFinder() {
        super();
    }

    public static DocumentFinder getDocumentFinder() {
        if (theSingleton == null) {
            initSingleton();
        }
        return theSingleton;

    }

    private synchronized static void initSingleton() {
        if (theSingleton == null) {
            theSingleton = new DocumentFinder();
        }
    }

}
