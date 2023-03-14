package ru.magic.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.magic.school.DTO.FacultyDTO;
import ru.magic.school.DTO.StudentDTO;
import ru.magic.school.model.Avatar;
import ru.magic.school.model.Student;
import ru.magic.school.repository.AvatarRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {
    @Value("avatar")
    private String avatarDir;
    private AvatarRepository avatarRepository;
    private StudentService studentService;

    public AvatarService() {
    }

    public AvatarService(AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }

    public void uploadAvatar(Long idS, MultipartFile file) throws IOException {
        StudentDTO student = studentService.getStudentByIdS(idS);

        Path filePath = Path.of(avatarDir,idS + "." + getExtencion(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);


        try(InputStream is = file.getInputStream();
            OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
          bis.transferTo(bos);
        }
        Avatar avatar = findStudentAvatar(idS);
        avatar.setStudent(student.toStudent());
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(generateImagePreview(filePath));

        avatarRepository.save(avatar);
    }

    public Avatar findStudentAvatar(Long idS) {
        return avatarRepository.findByStudentIdS(idS).orElse(new Avatar());
    }

    public byte[] generateImagePreview(Path filePath) throws IOException {
        try(InputStream is = Files.newInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage data = new BufferedImage(100, height, image.getType());
            Graphics2D graphics2D = data.createGraphics();
            graphics2D.drawImage(image, 0, 0, 100, height, null);
            graphics2D.dispose();

            ImageIO.write(data, getExtencion(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }

    public String getExtencion(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
