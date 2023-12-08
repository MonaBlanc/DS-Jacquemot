package fr.polytech.cours.controller;

import fr.polytech.cours.service.S3Service;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class S3Controller {

    private final S3Service s3Service;

    @GetMapping("/Library/Illustration")
    public String getURLIllustration(@RequestParam String name) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String url = s3Service.postIllustration(name);
        return url;
    }

    @GetMapping("/Library/Avis")
    public String getURLAvis(String name) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String url = s3Service.postAvis(name);
        return url;
    }
    @PostMapping("/restaurants/upload-photo")
        public ResponseEntity<String> uploadPhoto(@RequestParam("file") File file) {
            try {
                String url = s3Service.uploadPhoto(file);
                return ResponseEntity.ok().body(url);
            } catch (Exception e) {
                log.error("Error uploading photo", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading photo");
            }
        }
    }


    /*@GetMapping("/Library")
    public String getImage() {
        return url;
    }*/


