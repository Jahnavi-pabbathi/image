package com.tgt.upcurve.ImageAPI.controller;

import com.tgt.upcurve.ImageAPI.entity.ImageEntity;
import com.tgt.upcurve.ImageAPI.service.ImageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image_api/v1")
public class ImageController {
    private final ImageService imageService;
    public ImageController(ImageService imageService){
        this.imageService=imageService;
    }

    @GetMapping("/generate_image/order/{order_id}/customer/{customer_id}")
    public ImageEntity generateImage(@PathVariable("order_id") Integer orderId, @PathVariable("customer_id") Integer customerId) {
        return imageService.generateImage(orderId, customerId);
    }

    @GetMapping("/get_image/{id}")
    public ImageEntity getImage(@PathVariable("id") Long imageId) {
        return imageService.getImage(imageId);
    }
}

