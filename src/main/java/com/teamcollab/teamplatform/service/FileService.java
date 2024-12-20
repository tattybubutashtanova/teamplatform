package com.teamcollab.teamplatform.service;

import com.teamcollab.teamplatform.model.File;
import com.teamcollab.teamplatform.model.User;
import com.teamcollab.teamplatform.repository.FileRepository;
import com.teamcollab.teamplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    public File saveFile(MultipartFile file, Long userId) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        File newFile = new File();
        newFile.setFileName(file.getOriginalFilename());
        newFile.setFileType(file.getContentType());
        newFile.setData(file.getBytes());
        newFile.setUploadedAt(LocalDateTime.now());
        newFile.setUploadedBy(user);

        return fileRepository.save(newFile);
    }
}
