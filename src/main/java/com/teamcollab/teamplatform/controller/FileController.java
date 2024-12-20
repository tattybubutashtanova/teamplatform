package com.teamcollab.teamplatform.controller;

import com.teamcollab.teamplatform.model.File;
import com.teamcollab.teamplatform.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
        try {
            fileService.saveFile(file, userId);
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("File upload failed: " + e.getMessage());
        }
    }
}
