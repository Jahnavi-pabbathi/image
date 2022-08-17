package com.tgt.upcurve.ImageAPI.service;


import com.tgt.upcurve.ImageAPI.ImageApiApplication;
import com.tgt.upcurve.ImageAPI.entity.ImageEntity;
import com.tgt.upcurve.ImageAPI.utility.JsonUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ImageApiApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ImageServiceTest {
    @Autowired
    ImageService imageService;
    private static final String ORDER_JSON_FILE_PATH = "/ImageData.json";

    @Test
    void testGenerateImage() throws Exception {
        ImageEntity image = JsonUtility.getImageRequest(ORDER_JSON_FILE_PATH);
        image.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        imageService.generateImage(10, 1);
        assert image != null;

    }

    @Test
    public void testGetImage() throws Exception {
        ImageEntity image = JsonUtility.getImageRequest(ORDER_JSON_FILE_PATH);
            ImageEntity savedImageEntity = imageService.saveImage(image);
            ImageEntity existingImage = imageService.getImage(2L);
            assert image!= null;
    }

    @Test
    public void testSaveImage() throws Exception {
        ImageEntity image = JsonUtility.getImageRequest(ORDER_JSON_FILE_PATH);
        ImageEntity savedImage = imageService.saveImage(image);
        Assertions.assertNotNull(image);
    }
}
