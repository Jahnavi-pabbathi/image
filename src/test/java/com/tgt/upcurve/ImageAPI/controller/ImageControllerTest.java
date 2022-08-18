package com.tgt.upcurve.ImageAPI.controller;
import com.tgt.upcurve.ImageAPI.entity.ImageEntity;
import com.tgt.upcurve.ImageAPI.utility.JsonUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ImageControllerTest {
    @Autowired
    MockMvc mockMvc;
    private static final String URI_SAVE = "/image_api/v1";
    private static final String URI_GET_BY_ID = "/get_image/{id}";
    private static final String ORDER_JSON_FILE_PATH = "/ImageData.json";
    private static final String URI_GENERATE_BY_ORDER_ID_CUSTOMER_ID ="/generate_image/order/{order_id}/customer/{customer_id}" ;

    @Test
    public void testGenerateImageId() throws Exception {
        String imageString = JsonUtility.getResourceAsString(ORDER_JSON_FILE_PATH);
            MvcResult responseSave = mockMvc.perform(post(URI_SAVE)
                            .content(imageString)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andReturn();
            String savedResponse = responseSave.getResponse().getContentAsString();
            MvcResult responseFetch = mockMvc.perform(get(URI_GENERATE_BY_ORDER_ID_CUSTOMER_ID, 1, 1)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andReturn();
            String fetchedResponse = responseFetch.getResponse().getContentAsString();

            ImageEntity savedImage = JsonUtility.readValue(savedResponse, ImageEntity.class);
            ImageEntity fetchedImage = JsonUtility.readValue(fetchedResponse, ImageEntity.class);
            Assertions.assertEquals(savedImage.getId(), fetchedImage.getId());
           // Assertions.assertEquals(savedImage.getImageCode(), fetchedImage.getImageCode());
           // Assertions.assertEquals(savedImage.getCreatedAt(), fetchedImage.getCreatedAt());
        }

    @Test
    public void testGetById() throws Exception {
        String imageString = JsonUtility.getResourceAsString(ORDER_JSON_FILE_PATH);
        MvcResult responseSave = mockMvc.perform(post(URI_SAVE)
                        .content(imageString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String savedResponse = responseSave.getResponse().getContentAsString();
        MvcResult responseFetch = mockMvc.perform(get(URI_GET_BY_ID, 77L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String fetchedResponse = responseFetch.getResponse().getContentAsString();
        ImageEntity savedImage = JsonUtility.readValue(savedResponse, ImageEntity.class);
        ImageEntity fetchedImage = JsonUtility.readValue(fetchedResponse, ImageEntity.class);
        Assertions.assertEquals(savedImage.getId(), fetchedImage.getId());
        Assertions.assertEquals(savedImage.getImageCode(), fetchedImage.getImageCode());
        Assertions.assertEquals(savedImage.getCreatedAt(), fetchedImage.getCreatedAt());
    }
}

