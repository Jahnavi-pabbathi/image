package com.tgt.upcurve.ImageAPI.service;

import com.tgt.upcurve.ImageAPI.entity.ImageEntity;
import com.tgt.upcurve.ImageAPI.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final QRCodeServiceImpl qrCodeService;

    @Value("${qr.code.width}")
    private Integer qrCodeWidth;
    @Value("${qr.code.height}")
    private Integer qrCodeHeight;

    public ImageService(ImageRepository imageRepository, QRCodeServiceImpl qrCodeService){
        this.imageRepository=imageRepository;
        this.qrCodeService = qrCodeService;
    }

    public ImageEntity generateImage(Integer orderId, Integer customerId) {
        String qrContent = orderId + "-" + customerId;
        byte[] newQRCode = qrCodeService.generateQRCode(qrContent, qrCodeWidth, qrCodeHeight);
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setImageCode(newQRCode);
        return imageRepository.saveAndFlush(imageEntity);
    }

    public ImageEntity getImage(Long imageId) {
        Optional<ImageEntity> image = null;
       image = imageRepository.findById(imageId);
       if (image.isPresent()) {
           return image.get();
       }
       return null;
    }
}
