package code_talent_gallery_back.com.service;

import code_talent_gallery_back.com.DTO.DeveloperProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DeveloperProfileService {
    Page<DeveloperProfileDTO> findAllProfile(Pageable pageable);
    Optional<DeveloperProfileDTO> findById(Long id);
    DeveloperProfileDTO createProfile(DeveloperProfileDTO newProfileDTO);
    DeveloperProfileDTO updateProfilePicture(Long id, String imageUrl);

    DeveloperProfileDTO updateProfile(Long id, DeveloperProfileDTO updateDTO);
}
