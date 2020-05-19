package com.autentia.tnt.upload.impl;

import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.util.ConfigurationUtil;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class ActivityImageUploader {

    private static final String EXTENSION = "jpg";
    private static final int MAX_WIDTH = 1024;
    private static final int MAX_HEIGHT = 768;

    public static boolean store(UploadedFile file, Activity activity) {
        String fileName = generateFileName(activity.getInsertDate(),activity.getId());
        File destinationFile = new File(fileName);

        OutputStream out = null;
        ImageOutputStream ios = null;
        BufferedImage bufferedImage;

        try {
            bufferedImage = ImageIO.read(file.getInputStream());
            destinationFile.getParentFile().mkdirs();
            out = new FileOutputStream(fileName);

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(EXTENSION);
            ImageWriter writer = writers.next();

            ios = ImageIO.createImageOutputStream(out);
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();

            bufferedImage = transformImage(bufferedImage);

            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(0.3f);
            writer.write(null, new IIOImage(bufferedImage, null, null), param);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ios != null) {
                try {
                    ios.close();
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

        return true;
    }

    private static BufferedImage transformImage(BufferedImage image) {
        // Resizes image preserving aspect ratio and also removes alpha channel so image can be saved as JPG

        int originalHeight = image.getHeight();
        int originalWidth = image.getWidth();
        float aspectRatio = (float) originalWidth / originalHeight;

        int width = originalWidth;
        int height = originalHeight;

        if (width > MAX_WIDTH) {
            width = MAX_WIDTH;
            height = (int) (width / aspectRatio);
        }

        if (height > MAX_HEIGHT) {
            height = MAX_HEIGHT;
            width = (int) (height * aspectRatio);
        }

        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        return outputImage;
    }

    public static boolean remove(Activity activity) {
        String filename = generateAbsolutePath(activity.getInsertDate(), activity.getId().toString());
        File fileToDelete = new File(filename);
        return fileToDelete.delete();
    }

    public static void exists() {

    }

    private static String generateFileName(Date date, long id) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String fileName = getBasePath() +
                calendar.get(Calendar.YEAR) + "/" +
                (calendar.get(Calendar.MONTH) + 1) + "/" +
                id + "." + EXTENSION;

        return fileName;
    }

    private static String generateAbsolutePath(Date date, String name) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String fileName = getBasePath() +
                calendar.get(Calendar.YEAR) + "/" +
                (calendar.get(Calendar.MONTH) + 1) + "/" +
                name + "." + EXTENSION;

        return fileName;
    }

    private static String getBasePath() {
        return ConfigurationUtil.getDefault().getUploadPath() + "activity/images/";
    }
}
