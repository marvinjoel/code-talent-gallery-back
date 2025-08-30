package code_talent_gallery_back.com.mapper;

import code_talent_gallery_back.com.DTO.CategoryDTO;
import code_talent_gallery_back.com.DTO.DeveloperProfileDTO;
import code_talent_gallery_back.com.DTO.SkillDTO;
import code_talent_gallery_back.com.DTO.UserDTO;
import code_talent_gallery_back.com.model.*;

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

    public static DeveloperProfile toEntity(DeveloperProfileDTO dto){
        DeveloperProfile profile = new DeveloperProfile();
        profile.setId(dto.getId());
        if (dto.getUser() != null){
            var user = new User();
            user.setId(dto.getUser().getId());
            user.setFullName(dto.getUser().getFullName());
            profile.setUser(user);
        }
        profile.setLocation(dto.getLocation());
        profile.setProfessionalTitle(dto.getProfessionalTitle());
        profile.setDescription(dto.getDescription());

        if (dto.getCategory() != null){
            var category = new Category();
            category.setId(dto.getCategory().getId());
            category.setName(dto.getCategory().getName());
            profile.setCategory(category);
        }

        profile.setYearsExperience(dto.getYearsExperience());
        profile.setAvailability(dto.getAvailability() != null ? Availability.valueOf(dto.getAvailability()): null);
        profile.setHourlyRate(dto.getHourlyRate());
        profile.setRating(dto.getRating());
        profile.setNumProjects(dto.getNumProjects());
        profile.setProfilePictureUrl(dto.getProfilePictureUrl());
        profile.setGithubUrl(dto.getGithubUrl());
        profile.setLinkedinUrl(dto.getLinkedinUrl());
        profile.setPortfolioUrl(dto.getPortfolioUrl());
        profile.setUpdatedAt(dto.getUpdatedAt());

        if (dto.getSkills() != null){
            profile.setSkills(dto.getSkills().stream()
                    .map(skillDto -> {
                        var skill = new Skill();
                        skill.setId(skillDto.getId());
                        skill.setName(skillDto.getName());
                        return skill;
                    })
                    .collect(Collectors.toSet()));
        }
        return profile;
    }
}
