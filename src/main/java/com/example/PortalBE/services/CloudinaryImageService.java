package com.example.PortalBE.services;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryImageService {

    String uploadImage(MultipartFile file);
}
