package com.mycompany.learning.Domain.Utilities;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static String convertToBase64(MultipartFile file) throws IOException {
        byte[] fileBytes = file.getBytes();
        return Base64.getEncoder().encodeToString(fileBytes);
    }

    public static String saveFile(MultipartFile file, String uploadDir) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Dosya boş olamaz");
        }

        // Dizin yoksa oluştur
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Dosya ismini al
        String filePath = uploadDir + File.separator + file.getOriginalFilename();

        // Dosyayı kaydet
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(file.getBytes());
        }

        return filePath; // Kaydedilen dosyanın yolu
    }
}

