package code_talent_gallery_back.com.mapper;

import code_talent_gallery_back.com.DTO.CategoryDTO;
import code_talent_gallery_back.com.DTO.DeveloperProfileDTO;
import code_talent_gallery_back.com.DTO.SkillDTO;
import code_talent_gallery_back.com.DTO.UserDTO;
import code_talent_gallery_back.com.model.DeveloperProfile;

import java.util.stream.Collectors;

public class DeveloperProfileMapper {

    public static DeveloperProfileDTO toDTO(DeveloperProfile profile){
        return DeveloperProfileDTO.builder()
                .id(profile.getId())
                .user(UserDTO.builder()
                        .id(profile.getUser().getId())
                        .fullName(profile.getUser().getFullName())
                        .build())
                .location(profile.getLocation())
                .professionalTitle(profile.getProfessionalTitle())
                .description(profile.getDescription())
                .category(profile.getCategory() != null ?
                        new CategoryDTO(profile.getCategory().getId(), profile.getCategory().getName()): null )
                .yearsExperience(profile.getYearsExperience())
                .availability(profile.getAvailability() != null ? profile.getAvailability().name() : null)
                .hourlyRate(profile.getHourlyRate())
                .rating(profile.getRating())
                .numProjects(profile.getNumProjects())
                .profilePictureUrl(profile.getProfilePictureUrl())
                .githubUrl(profile.getGithubUrl())
                .linkedinUrl(profile.getLinkedinUrl())
                .portfolioUrl(profile.getPortfolioUrl())
                .updatedAt(profile.getUpdatedAt())
                .skills(profile.getSkills() != null ?
                        profile.getSkills().stream()
                                .map(skill -> new SkillDTO(skill.getId(), skill.getName()))
                                .collect(Collectors.toList())
                        : null)
                .build();
    }
}
