package code_talent_gallery_back.com.service;

import code_talent_gallery_back.com.DTO.DeveloperProfileDTO;
import code_talent_gallery_back.com.mapper.DeveloperProfileMapper;
import code_talent_gallery_back.com.model.DeveloperProfile;
import code_talent_gallery_back.com.repository.DeveloperProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeveloperProfileServiceImpl implements DeveloperProfileService{

    private final DeveloperProfileRepository developerProfileRepository;

    @Override
    public List<DeveloperProfileDTO> findAllProfile(){
        return developerProfileRepository.findAll().stream()
                .map(DeveloperProfileMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DeveloperProfileDTO> findById(Long id) {
        return developerProfileRepository.findById(id)
                .map(DeveloperProfileMapper::toDTO);
    }

    @Override
    public DeveloperProfile createProfile(DeveloperProfile newProfile){
        if (newProfile.getUser() == null || newProfile.getUser().getId() == null){
            throw new RuntimeException("User ID es requerido para crear el perfil");
        }
        newProfile.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        return developerProfileRepository.save(newProfile);
    }

    @Override
    public DeveloperProfile updateProfile(Long id, DeveloperProfile updatedProfile) {
        return developerProfileRepository.findById(id)
                .map(profile -> {
                    profile.setLocation(updatedProfile.getLocation());
                    profile.setProfessionalTitle(updatedProfile.getProfessionalTitle());
                    profile.setDescription(updatedProfile.getDescription());
                    profile.setCategory(updatedProfile.getCategory());
                    profile.setYearsExperience(updatedProfile.getYearsExperience());
                    profile.setAvailability(updatedProfile.getAvailability());
                    profile.setHourlyRate(updatedProfile.getHourlyRate());
                    profile.setProfilePictureUrl(updatedProfile.getProfilePictureUrl());
                    profile.setGithubUrl(updatedProfile.getGithubUrl());
                    profile.setLinkedinUrl(updatedProfile.getLinkedinUrl());
                    profile.setPortfolioUrl(updatedProfile.getPortfolioUrl());
                    profile.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
                    return developerProfileRepository.save(profile);
                })
                .orElseThrow(() -> new RuntimeException("Developer profile not found with id: " + id));
    }

    @Override
    public DeveloperProfile updateProfilePicture(Long id, String imageUrl) {
        return developerProfileRepository.findById(id)
                .map(profile -> {
                    profile.setProfilePictureUrl(imageUrl);
                    profile.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
                    return developerProfileRepository.save(profile);
                })
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado con id: " + id));
    }

}
