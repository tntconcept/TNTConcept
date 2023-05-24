package com.autentia.tnt.upload.impl;

import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.test.utils.SpringUtilsForTesting;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ActivityImageUploaderTest {

    @BeforeClass
    public static void init() {
        SpringUtilsForTesting.configure(new ClassPathXmlApplicationContext("applicationContext-test.xml"));
    }

    @Test
    public void imageShouldBeSaved() throws IOException {
        Activity activity = mock(Activity.class);
        UploadedFile file = mock(UploadedFile.class);

        when(activity.getId()).thenReturn(666);
        when(activity.getStart()).thenReturn(new Date());
        when(activity.getEndDate()).thenReturn(new Date());
        when(activity.getInsertDate()).thenReturn(new Date());
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("testImage.jpg");
        when(file.getInputStream()).thenReturn(inputStream);

        ActivityImageUploader.store(file, activity);
    }
}
