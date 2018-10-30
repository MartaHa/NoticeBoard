package pl.coderslab.notice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.notice.repository.UploadFileRepository;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private final UploadFileRepository uploadFileRepository;

    public FileUploadController(UploadFileRepository uploadFileRepository) {
        this.uploadFileRepository = uploadFileRepository;


    }
}

