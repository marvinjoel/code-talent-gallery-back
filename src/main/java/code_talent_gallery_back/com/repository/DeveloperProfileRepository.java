package code_talent_gallery_back.com.repository;

import code_talent_gallery_back.com.model.DeveloperProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperProfileRepository extends JpaRepository<DeveloperProfile, Long> {
}
