package code_talent_gallery_back.com.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperProfileDTO {

    private Long id;
    private UserDTO user;
    private String location;
    private String professionalTitle;
    private String description;
    private CategoryDTO category;
    private String yearsExperience;
    private String availability;
    private Double hourlyRate;
    private Double rating;
    private Integer numProjects;
    private String profilePictureUrl;
    private String githubUrl;
    private String linkedinUrl;
    private String portfolioUrl;
    private Timestamp updatedAt;
    private List<SkillDTO> skills;
}
