package com.example.PortalBE.services.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.PortalBE.services.CloudinaryImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class cloudinaryImageServiceImpl implements CloudinaryImageService {

    private static final Logger logger = LoggerFactory.getLogger(cloudinaryImageServiceImpl.class);

    private final Cloudinary cloudinary;

    @Autowired
    public cloudinaryImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File không được để trống.");
        }

        // Tạo một public_id duy nhất
        String publicId = "HRPortal/" + UUID.randomUUID().toString();

        Map<String, Object> params = ObjectUtils.asMap(
                "Folder", "HRPortal",
                "public_id", publicId
        );

        try {
            Map<String, Object> res = cloudinary.uploader().upload(file.getBytes(), params);
            if (res != null && res.get("url") != null) {
                String url = res.get("url").toString();
                logger.info("Image uploaded successfully. URL: " + url);
                return url;
            } else {
                logger.warn("Failed to upload image.");
            }
        } catch (IOException e) {
            logger.error("IOException occurred during file upload: ", e);
            throw new RuntimeException("Failed to upload image", e);
        } catch (Exception e) {
            logger.error("Exception occurred during file upload: ", e);
            throw new RuntimeException("Failed to upload image", e);
        }

        return null;
    }
}
