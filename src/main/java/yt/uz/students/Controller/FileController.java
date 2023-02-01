package yt.uz.students.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import yt.uz.students.Service.FileStorageService;
import yt.uz.students.responses.UploadFileResponse;

@RestController
@RequestMapping("api")
public class FileController {
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = fileStorageService.storeFile("students", file);


        return new UploadFileResponse(fileName,
                file.getContentType(), file.getSize());
    }
}
