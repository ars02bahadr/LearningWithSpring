package com.mycompany.learning.Infrastructure.File;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class FtpService {

    // FTP Bağlantı Bilgilerini Manuel Tanımla
    private final String ftpHost = "ftp.example.com";
    private final int ftpPort = 21;
    private final String ftpUsername = "your_username";
    private final String ftpPassword = "your_password";
    private final String remoteDir = "/uploads";

    /**
     * FTP Sunucusuna dosya yükler.
     *
     * @param file MultipartFile türünde gelen dosya
     * @return Yükleme başarılıysa true, değilse false
     */
    public boolean uploadFile(MultipartFile file) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ftpHost, ftpPort);
            ftpClient.login(ftpUsername, ftpPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // Dosyanın uzaktaki yolu
            String remoteFilePath = remoteDir + "/" + file.getOriginalFilename();

            // FTP'ye dosya yükleme
            InputStream inputStream = file.getInputStream();
            boolean done = ftpClient.storeFile(remoteFilePath, inputStream);
            inputStream.close();
            ftpClient.logout();
            ftpClient.disconnect();
            return done;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * FTP Sunucusundan dosya indirir.
     *
     * @param fileName İndirilecek dosyanın adı
     * @return Dosyanın içeriğini byte[] olarak döndürür
     */
    public byte[] downloadFile(String fileName) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ftpHost, ftpPort);
            ftpClient.login(ftpUsername, ftpPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // FTP'den dosya çekme
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            boolean success = ftpClient.retrieveFile(remoteDir + "/" + fileName, outputStream);
            ftpClient.logout();
            ftpClient.disconnect();

            return success ? outputStream.toByteArray() : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


