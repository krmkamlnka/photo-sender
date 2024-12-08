package org.example.photo;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {

    private static final String UPLOAD_DIR = "/Users/alia/Desktop/uploaded-images/";

    @GetMapping("/images/{filename}")
    public Resource getImage(@PathVariable String filename) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR).resolve(filename);
        return new UrlResource(filePath.toUri());
    }
}
