package code_talent_gallery_back.com.controller;

import code_talent_gallery_back.com.DTO.DeveloperProfileDTO;
import code_talent_gallery_back.com.mapper.DeveloperProfileMapper;
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

    @PostMapping
    public ResponseEntity<?> createDeveloperProfile(@RequestBody DeveloperProfileDTO dto){
        DeveloperProfileDTO createdProfile = developerProfileService.createProfile(dto);
        return ResponseEntity.ok(createdProfile);
    }

    @PostMapping("/{id}/upload-photo")
    public ResponseEntity<?> uploadProfilePhoto(@PathVariable Long id, @RequestParam("file")MultipartFile file){
        String imageUrl = cloudinaryService.uploadFile(file);
        DeveloperProfileDTO updateProfile = developerProfileService.updateProfilePicture(id, imageUrl);
        return ResponseEntity.ok(updateProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeveloperProfileDTO> updateDeveloperProfile(@PathVariable Long id,@RequestBody DeveloperProfileDTO dto){
        DeveloperProfileDTO updatedProfile = developerProfileService.updateProfile(id, dto);
        return ResponseEntity.ok(updatedProfile);
    }
}
