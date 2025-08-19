package code_talent_gallery_back.com.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;


@Data
@Entity
@Table(name = "developer_profiles")
public class DeveloperProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String location;

    private String professionalTitle;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String yearsExperience;

    @Enumerated(EnumType.STRING)
    private Availability availability;

    private Double hourlyRate;

    private Double rating;

    private Integer numProjects;

    private String profilePictureUrl;

    private Boolean isFeatured;

    private String githubUrl;

    private String linkedinUrl;

    private String portfolioUrl;

    private Timestamp updatedAt;

    @ManyToMany
    @JoinTable(
            name = "developer_skills",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills;
}
