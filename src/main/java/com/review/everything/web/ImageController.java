package com.review.everything.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class ImageController {

    @Value("${storage.location}")
    private String fileRealPath;

    @PutMapping("/image/upload")
    public @ResponseBody
    String imageUpload(@RequestParam("imgFile") MultipartFile imgFile) {
        try {
            System.out.println(imgFile.getBytes().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //파일명
        UUID uuid = UUID.randomUUID();
        String uuidFilename = uuid + "_" + imgFile.getOriginalFilename();

        Path filePath = Paths.get(fileRealPath + uuidFilename);

        try {
            Files.write(filePath, imgFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uuidFilename;
    }
}
