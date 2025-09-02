package code_talent_gallery_back.com.service;

import code_talent_gallery_back.com.DTO.DeveloperProfileDTO;
import code_talent_gallery_back.com.exception.ResourceNotFoundException;
import code_talent_gallery_back.com.mapper.DeveloperProfileMapper;
import code_talent_gallery_back.com.model.Availability;
import code_talent_gallery_back.com.model.Category;
import code_talent_gallery_back.com.model.DeveloperProfile;
import code_talent_gallery_back.com.model.Skill;
import code_talent_gallery_back.com.repository.DeveloperProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeveloperProfileServiceImpl implements DeveloperProfileService{

    private final DeveloperProfileRepository developerProfileRepository;

    @Override
    public Page<DeveloperProfileDTO> findAllProfile(Pageable pageable) {
        return developerProfileRepository.findAll(pageable)
                .map(DeveloperProfileMapper::toDTO);
    }

    @Override
    public Optional<DeveloperProfileDTO> findById(Long id) {
        return developerProfileRepository.findById(id)
                .map(DeveloperProfileMapper::toDTO);
    }

    @Override
    public DeveloperProfileDTO createProfile(DeveloperProfileDTO newProfileDTO){
        if (newProfileDTO.getUser() == null || newProfileDTO.getUser().getId() == null){
            throw new RuntimeException("User ID es requerido para crear el perfil");
        }

        DeveloperProfile entity = DeveloperProfileMapper.toEntity(newProfileDTO);
        entity.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        DeveloperProfile saved = developerProfileRepository.save(entity);
        return DeveloperProfileMapper.toDTO(saved);
    }

    @Override
    public DeveloperProfileDTO updateProfilePicture(Long id, String imageUrl) {
        DeveloperProfile profile = developerProfileRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No se encontró el perfil con id = " + id));
        profile.setProfilePictureUrl(imageUrl);
        DeveloperProfile saved = developerProfileRepository.save(profile);
        return DeveloperProfileMapper.toDTO(saved);
    }

    @Override
    public DeveloperProfileDTO updateProfile(Long id, DeveloperProfileDTO updateDTO){
        DeveloperProfile existingProfile = developerProfileRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Perfil no encontrado con id = " + id));

        if (updateDTO.getLocation() != null){
            existingProfile.setLocation(updateDTO.getLocation());
        }
        if (updateDTO.getProfessionalTitle() != null){
            existingProfile.setProfessionalTitle(updateDTO.getProfessionalTitle());
        }
        if (updateDTO.getDescription() != null){
            existingProfile.setDescription(updateDTO.getDescription());
        }

        if (updateDTO.getCategory() != null){
            Category category = new Category();
            category.setId(updateDTO.getCategory().getId());
            category.setName(updateDTO.getCategory().getName());
            existingProfile.setCategory(category);
        }

        if (updateDTO.getYearsExperience() != null){
            existingProfile.setYearsExperience(updateDTO.getYearsExperience());
        }

        if (updateDTO.getAvailability() != null){
            existingProfile.setAvailability(Availability.valueOf(updateDTO.getAvailability()));
        }

        if (updateDTO.getHourlyRate() != null){
            existingProfile.setHourlyRate(updateDTO.getHourlyRate());
        }

        if (updateDTO.getRating() != null){
            existingProfile.setRating(updateDTO.getRating());
        }

        if (updateDTO.getNumProjects() != null){
            existingProfile.setNumProjects(updateDTO.getNumProjects());
        }

        if (updateDTO.getGithubUrl() != null){
            existingProfile.setGithubUrl(updateDTO.getGithubUrl());
        }

        if (updateDTO.getLinkedinUrl() != null){
            existingProfile.setLinkedinUrl(updateDTO.getLinkedinUrl());
        }

        if (updateDTO.getPortfolioUrl() != null){
            existingProfile.setPortfolioUrl(updateDTO.getPortfolioUrl());
        }

        if (updateDTO.getSkills() != null){
            existingProfile.setSkills(
                    updateDTO.getSkills().stream()
                            .map(skillDTO -> {
                                Skill skill = new Skill();
                                skill.setId(skillDTO.getId());
                                skill.setName(skillDTO.getName());
                                return skill;
                            })
                            .collect(Collectors.toSet())
            );
        }

        existingProfile.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        DeveloperProfile savedProfile = developerProfileRepository.save(existingProfile);
        return DeveloperProfileMapper.toDTO(savedProfile);
    }

}
