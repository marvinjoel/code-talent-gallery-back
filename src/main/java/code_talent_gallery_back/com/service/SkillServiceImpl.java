package code_talent_gallery_back.com.service;

import code_talent_gallery_back.com.model.Skill;
import code_talent_gallery_back.com.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Override
    public List<Skill> findAllSkill() {
        return skillRepository.findAll();
    }
}
