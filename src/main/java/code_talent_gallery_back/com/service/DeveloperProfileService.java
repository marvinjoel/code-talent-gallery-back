package code_talent_gallery_back.com.service;

import code_talent_gallery_back.com.DTO.DeveloperProfileDTO;

import java.util.List;
import java.util.Optional;

public interface DeveloperProfileService {
    List<DeveloperProfileDTO> findAllProfile();
    Optional<DeveloperProfileDTO> findById(Long id);
    DeveloperProfileDTO createProfile(DeveloperProfileDTO newProfileDTO);
    DeveloperProfileDTO updateProfilePicture(Long id, String imageUrl);

    DeveloperProfileDTO updateProfile(Long id, DeveloperProfileDTO updateDTO);
}
