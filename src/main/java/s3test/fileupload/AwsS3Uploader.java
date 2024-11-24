package s3test.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Component
public class AwsS3Uploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convertToFile(multipartFile) // 파일 생성
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 변환 실패"));

        try {
            return uploadToS3(uploadFile, dirName);
        } finally {
            removeLocalFile(uploadFile);
        }
    }

    // 1. 로컬에 파일 생성
    private Optional<File> convertToFile(MultipartFile file) throws IOException {
        File localFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(localFile)) {
            fos.write(file.getBytes());
        }
        return Optional.of(localFile);
    }

    // 2. S3에 파일 업로드
    private String uploadToS3(File uploadFile, String dirName) {
        String fileName = dirName + "/" + UUID.randomUUID() + "_" + uploadFile.getName();
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        log.info("S3 업로드 완료: {}", fileName);
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 3. 로컬 파일 삭제
    private void removeLocalFile(File targetFile) {
        if (targetFile.exists() && targetFile.delete()) {
            log.info("로컬 파일 삭제 성공: {}", targetFile.getAbsolutePath());
        } else {
            log.warn("로컬 파일 삭제 실패: {}", targetFile.getAbsolutePath());
        }
    }

    // S3 파일 삭제
    public void deleteFromS3(String fileName) {
        amazonS3Client.deleteObject(bucket, fileName);
        log.info("S3 파일 삭제 완료: {}", fileName);
    }
}
