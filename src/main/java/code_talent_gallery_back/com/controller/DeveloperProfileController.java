package code_talent_gallery_back.com.controller;

import code_talent_gallery_back.com.model.DeveloperProfile;
import code_talent_gallery_back.com.service.CloudinaryService;
import code_talent_gallery_back.com.service.DeveloperProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/developer-profiles")
public class DeveloperProfileController {

    private final DeveloperProfileService developerProfileService;
    private final CloudinaryService cloudinaryService;

    @GetMapping
    public ResponseEntity<?> getAllProfile(){
        return ResponseEntity.ok(developerProfileService.findAllProfile());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeveloperProfile(@PathVariable Long id){
        return developerProfileService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDeveloperProfile(@PathVariable Long id, @RequestBody DeveloperProfile updateProfile){
        try{
            DeveloperProfile saveProfile = developerProfileService.updateProfile(id, updateProfile);
            return ResponseEntity.ok(saveProfile);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createDeveloperProfile(@RequestBody DeveloperProfile newProfile){
        developerProfileService.createProfile(newProfile);
        return ResponseEntity.ok("Datos de usurio creado");
    }

    @PostMapping("/{id}/upload-photo")
    public ResponseEntity<?> uploadProfilePhoto(@PathVariable Long id, @RequestParam("file")MultipartFile file){
        String imageUrl = cloudinaryService.uploadFile(file);
        DeveloperProfile updateProfile = developerProfileService.updateProfilePicture(id, imageUrl);
        return ResponseEntity.ok("Datos de usurio creado");
    }
}
