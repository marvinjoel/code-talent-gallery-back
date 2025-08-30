package code_talent_gallery_back.com.service;

import code_talent_gallery_back.com.DTO.DeveloperProfileDTO;
import code_talent_gallery_back.com.exception.ResourceNotFoundException;
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

}
