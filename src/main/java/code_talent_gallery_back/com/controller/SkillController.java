package code_talent_gallery_back.com.controller;

import code_talent_gallery_back.com.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/skill-list")
public class SkillController {

    private final SkillService skillService;

    @GetMapping
    public ResponseEntity<?> getAllSkill(){
        return ResponseEntity.ok(skillService.findAllSkill());
    }
}
