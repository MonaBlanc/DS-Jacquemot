package fr.polytech.cours.service;


import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
@NoArgsConstructor
public class S3Service {

    private static final String PREFIX = "kim_";
    private static final String BUCKET_NAME = "examillustrations";
    private static final String BUCKET_AVIS = "examphotos";
    String url = null;


    MinioClient minioClient = MinioClient.builder().endpoint("http://92.222.10.101:9000").credentials("hI2na8J1hHFEN7u5ijvl", "sUVdSRgYfwnstqsPFh9S4gE7qVyd59BqRI4dP9LE").build();

    public String postIllustration(String name) {
        return getString(name, BUCKET_NAME);
    }
    public String postAvis(String name) {
        return getString(name, BUCKET_AVIS);
    }
    private String getString(String name, String bucketName) {
        try {
            url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT)
                            .bucket(bucketName)
                            .object(PREFIX + name + ".jpg")  // Add the prefix to the object name
                            .build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException |
                 InvalidKeyException | InvalidResponseException | IOException |
                 NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new RuntimeException(e);
        }
        return url;
    }
    public String uploadPhoto(File file) {
        try {
            String name = file.getName();
            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT)
                            .bucket(BUCKET_NAME)
                                .object(PREFIX + name)  // Add the prefix to the object name
                                .build());

                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(BUCKET_NAME)
                                .object(PREFIX + name + ".jpg")
                                .build());
                return url;
            } catch (Exception e) {
                log.error("Error uploading photo to S3", e);
                throw new RuntimeException(e);
            }
        }
    }
