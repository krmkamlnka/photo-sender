package org.example.photo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Controller
public class PhotoController {
    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/upload")
    public String uploadPage(Model model) {
        model.addAttribute("photos", photoService.getAllPhotos());
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadPhoto(@RequestParam("file") MultipartFile file, Model model) {
        try {
            Photo photo = photoService.uploadPhoto(file);
            model.addAttribute("successMessage", "Фотография успешно загружена!");
            model.addAttribute("photos", photoService.getAllPhotos());
            return "upload";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Ошибка при загрузке фотографии.");
            return "upload";
        }
    }
}
