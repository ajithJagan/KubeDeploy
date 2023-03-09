package com.flexcub.resourceplanning.skillseeker.service;


import com.flexcub.resourceplanning.skillowner.entity.OwnerSkillDomainEntity;
import com.flexcub.resourceplanning.skillseeker.entity.SkillSeekerEntity;
import com.flexcub.resourceplanning.skillseeker.entity.SkillSeekerProjectEntity;
import com.flexcub.resourceplanning.skillseeker.repository.SkillSeekerProjectRepository;
import com.flexcub.resourceplanning.skillseeker.service.impl.SkillSeekerProjectServiceImpl;
import com.flexcub.resourceplanning.skillseeker.service.impl.SkillSeekerTechnologyServiceImpl;
import com.flexcub.resourceplanning.skillseekeradmin.dto.SkillSeekerProject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SkillSeekerProjectServiceImpl.class)
class SkillSeekerProjectServiceTest {

    @MockBean
    SkillSeekerProjectRepository repository;

    @MockBean
    SkillSeekerTechnologyServiceImpl skillSeekerTechnologyService;

    @MockBean
    ModelMapper modelMapper;

    @Autowired
    SkillSeekerProjectServiceImpl service;

    SkillSeekerProject skillSeekerProject = new SkillSeekerProject();

    SkillSeekerEntity skillSeekerEntity = new SkillSeekerEntity();

    SkillSeekerProjectEntity skillSeekerProjectEntity = new SkillSeekerProjectEntity();
    List<SkillSeekerProjectEntity> skillSeekerProjectEntities = new ArrayList<>();
    OwnerSkillDomainEntity ownerSkillDomainEntity = new OwnerSkillDomainEntity();

    List<SkillSeekerProject> skillSeekerProjectList = new ArrayList<>();


    @BeforeEach
    void beforeTest() {
        skillSeekerEntity.setId(1);
        skillSeekerProject.setId(1);
        skillSeekerProject.setTitle("vicky");
        skillSeekerProject.setPrimaryContactEmail("vicky.j@qbrainx.com");
        skillSeekerProject.setSecondaryContactPhone("9890909090");
        skillSeekerProject.setSummary("proficient in backend");
        skillSeekerProject.setSecondaryContactEmail("vicky.r@qbrainx.com");
        skillSeekerProject.setSecondaryContactPhone("9898989898");
        skillSeekerProject.setOwnerSkillDomainEntity(ownerSkillDomainEntity);
        skillSeekerProjectList.add(skillSeekerProject);
        skillSeekerProjectEntities.add(skillSeekerProjectEntity);

        skillSeekerProjectEntity.setId(1);
    }

    @Test
    void insertDataTest() {
        Mockito.when(modelMapper.map(skillSeekerProject, SkillSeekerProjectEntity.class)).thenReturn(skillSeekerProjectEntity);
        Mockito.when(repository.save(skillSeekerProjectEntity)).thenReturn(skillSeekerProjectEntity);
        assertEquals(skillSeekerProject.getId(), service.insertData(skillSeekerProjectList).size());

    }


    @Test
    void updateDataTest() {
        Optional<SkillSeekerProjectEntity> skillSeekerProjectEntity1 = Optional.of(new SkillSeekerProjectEntity());
        skillSeekerProjectEntity1.get().setId(1);
        Mockito.when(modelMapper.map(skillSeekerProject, SkillSeekerProjectEntity.class)).thenReturn(skillSeekerProjectEntity1.get());
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(skillSeekerProjectEntity1);
        Mockito.when(modelMapper.map(skillSeekerProjectEntity1, SkillSeekerProject.class)).thenReturn(skillSeekerProject);
        assertEquals(skillSeekerProject.getId(), service.updateData(skillSeekerProject).getId());
    }

    @Test
    void deleteSkillSeekerProjectDataTest() {
        repository.deleteById(skillSeekerProject.getId());
        Mockito.verify(repository, times(1)).deleteById(1);

    }

    @Test
    void getProjectTest() {
        Mockito.when(repository.findBySkillSeekerId(Mockito.anyInt())).thenReturn(Optional.of(skillSeekerProjectEntities));
        when(modelMapper.map(skillSeekerProjectEntity, SkillSeekerProject.class)).thenReturn(skillSeekerProject);
        assertEquals(2, service.getProjectData(1).size());
    }
}










