package com.autentia.tnt.upload.impl;

import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.util.ConfigurationUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class ActivityImageUploader {

    public static String store(UploadedFile file, Date date) {
        String fileName = generateFileName(date, FilenameUtils.getExtension(file.getName()));
        File destinationFile = new File(fileName);

        InputStream in = null;
        OutputStream out = null;
        byte[] buffer = new byte[65536];
        int nr;

        try {
            in = file.getInputStream();
            destinationFile.getParentFile().mkdirs();
            out = new FileOutputStream(fileName);

            while ((nr = in.read(buffer)) != -1) {
                out.write(buffer, 0, nr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }

            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        return destinationFile.getName();
    }

    public static boolean remove(Activity activity) {
        String filename = generateAbsolutePath(activity.getInsertDate(), activity.getImageFileName());
        File fileToDelete = new File(filename);
        return fileToDelete.delete();
    }

    public static void exists() {

    }

    private static String generateFileName(Date date, String extension) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String fileName = getBasePath() +
                calendar.get(Calendar.YEAR) + "/" +
                (calendar.get(Calendar.MONTH) + 1) + "/" +
                date.getTime() + "." + extension;

        return fileName;
    }

    private static String generateAbsolutePath(Date date, String nameWithExtension) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String fileName = getBasePath() +
                calendar.get(Calendar.YEAR) + "/" +
                (calendar.get(Calendar.MONTH) + 1) + "/" +
                nameWithExtension;

        return fileName;
    }

    private static String getBasePath() {
        return ConfigurationUtil.getDefault().getUploadPath() + "activity/images/";
    }
}
