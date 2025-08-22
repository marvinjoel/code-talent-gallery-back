package code_talent_gallery_back.com.service;

import code_talent_gallery_back.com.model.DeveloperProfile;

import java.util.List;
import java.util.Optional;

public interface DeveloperProfileService {
    List<DeveloperProfile> findAllProfile();
    Optional<DeveloperProfile> findById(Long id);
    DeveloperProfile updateProfile(Long id, DeveloperProfile updatedProfile);
    DeveloperProfile createProfile(DeveloperProfile newProfile);
}
