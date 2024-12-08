package org.example.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    private static final String UPLOAD_DIR = "/Users/alia/Desktop/uploaded-images/";

    public Photo uploadPhoto(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        Files.write(filePath, file.getBytes());

        Photo photo = new Photo();
        photo.setFileName(fileName);
        photo.setImage_link("/images/" + fileName);
        return photoRepository.save(photo);
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }
}
