package code_talent_gallery_back.com.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public String uploadFile(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            System.out.println("CLOUDINARY RESPONSE: " + uploadResult);
            return (String) uploadResult.get("secure_url");
        } catch (Exception e) {
            throw new RuntimeException("Error uploading to Cloudinary", e);
        }
    }
}
