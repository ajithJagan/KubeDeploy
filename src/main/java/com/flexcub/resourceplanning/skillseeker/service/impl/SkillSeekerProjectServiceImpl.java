package com.flexcub.resourceplanning.skillseeker.service.impl;

import com.flexcub.resourceplanning.exceptions.ServiceException;
import com.flexcub.resourceplanning.skillseeker.entity.SkillSeekerProjectEntity;
import com.flexcub.resourceplanning.skillseeker.repository.SkillSeekerProjectRepository;
import com.flexcub.resourceplanning.skillseeker.service.SkillSeekerProjectService;
import com.flexcub.resourceplanning.skillseeker.service.SkillSeekerTechnologyService;
import com.flexcub.resourceplanning.skillseekeradmin.dto.SkillSeekerProject;
import com.flexcub.resourceplanning.utils.NullPropertyName;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.flexcub.resourceplanning.utils.FlexcubErrorCodes.INVALID_PROJECT_ID;
import static com.flexcub.resourceplanning.utils.FlexcubErrorCodes.INVALID_SEEKER;

@Service
public class SkillSeekerProjectServiceImpl implements SkillSeekerProjectService {
    @Autowired
    SkillSeekerProjectRepository projectRepo;

    @Autowired
    SkillSeekerTechnologyService skillSeekerTechnologyService;
    Logger logger = LoggerFactory.getLogger(SkillSeekerProjectServiceImpl.class);

    @Autowired
    ModelMapper modelMapper;

    /**
     * This method is to insert skillSeeker project details.
     *
     * @return It returns inserted skillSeeker projects.
     * @paramskillSeekerProjectEntityList
     */


    @Override
    public List<SkillSeekerProject> insertData(List<SkillSeekerProject> skillSeekerProjectList) {
        List<SkillSeekerProjectEntity> skillSeekerProjectEntities = new ArrayList<>();

        try {
            for (SkillSeekerProject skillSeekerProject1 : skillSeekerProjectList) {
                SkillSeekerProjectEntity skillSeekerProjectEntity1 = modelMapper.map(skillSeekerProject1, SkillSeekerProjectEntity.class);
                skillSeekerProjectEntities.add(skillSeekerProjectEntity1);
                skillSeekerProjectEntities.forEach(skillSeekerProjectEntity -> {
                    if (skillSeekerProjectEntity.getSkillSeekerTechnologyData() == null) {
                        skillSeekerProjectEntity.setSkillSeekerTechnologyData(null);
                    } else {
                        skillSeekerProjectEntities.forEach(skillSeekerProject -> skillSeekerProject.setSkillSeekerTechnologyData(skillSeekerTechnologyService.insertMultipleData(skillSeekerProject.getSkillSeekerTechnologyData())));
                    }
                });
                projectRepo.saveAll(skillSeekerProjectEntities);
            }

            logger.info("SkillSeekerProjectServiceImpl || insertData || Inserting the SeekerProject list: {}", skillSeekerProjectList);

            List<SkillSeekerProject> skillSeekerProjectLists = new ArrayList<>();
            for (SkillSeekerProjectEntity skillSeekerProjectEntity : skillSeekerProjectEntities) {
                SkillSeekerProject skillSeekerProject = modelMapper.map(skillSeekerProjectEntity, SkillSeekerProject.class);
                skillSeekerProjectLists.add(skillSeekerProject);
            }
            return skillSeekerProjectLists;
        } catch (Exception e) {
            throw new ServiceException(INVALID_SEEKER.getErrorCode(), INVALID_SEEKER.getErrorDesc());
        }
    }


    /**
     * @return
     * @paramid
     */
    @Override
    public List<SkillSeekerProject> getProjectData(int skillSeekerId) {
        try {
            List<SkillSeekerProject> skillSeekerProjectList = new ArrayList<>();
            Optional<List<SkillSeekerProjectEntity>> skillSeekerProject = projectRepo.findBySkillSeekerId(skillSeekerId);

            if (!skillSeekerProject.get().isEmpty()) {
                for (SkillSeekerProjectEntity skillSeekerProjectEntity : skillSeekerProject.get()) {
                    SkillSeekerProject skillSeekerProject1 = modelMapper.map(skillSeekerProjectEntity, SkillSeekerProject.class);
                    skillSeekerProjectList.add(skillSeekerProject1);
                }

            }
            //adding a static project as default
            SkillSeekerProject staticProject = new SkillSeekerProject();
            staticProject.setId(0);
            staticProject.setTitle("Default");
            skillSeekerProjectList.add(staticProject);
            return skillSeekerProjectList;
        }
        catch(Exception e){
            throw new ServiceException(INVALID_SEEKER.getErrorCode(), INVALID_SEEKER.getErrorDesc());
        }
    }


    /**
     * This method is to delete skillSeeker project detail based on id.
     *
     * @paramid
     */
    @Override
    public void deleteData(int id) {

        Optional<SkillSeekerProjectEntity> skillSeekerProjectData = projectRepo.findById(id);


        if (skillSeekerProjectData.isPresent()) {
            logger.info("SkillSeekerProjectServiceImpl || deleteData || Deleting the SeekerProject id: {}", id);
            projectRepo.deleteById(id);
        } else {
            throw new ServiceException(INVALID_PROJECT_ID.getErrorCode(), INVALID_PROJECT_ID.getErrorDesc());
        }

    }

    /**
     * This method is to update skillSeeker project details.
     *
     * @return It returns updated data of skillSeeker projects.
     * @paramskillSeekerProjectEntity
     */

    @Override
    public SkillSeekerProject updateData(SkillSeekerProject skillSeekerProject) {
        SkillSeekerProjectEntity seekerProjectEntity = modelMapper.map(skillSeekerProject, SkillSeekerProjectEntity.class);
        try {
            Optional<SkillSeekerProjectEntity> skillSeekerProjectData = projectRepo.findById(seekerProjectEntity.getId());
            if (skillSeekerProjectData.isPresent()) {
                logger.info("SkillSeekerProjectServiceImpl || updateData || Updating the SkillSeeker Project");
                BeanUtils.copyProperties(seekerProjectEntity, skillSeekerProjectData.get(), NullPropertyName.getNullPropertyNames(seekerProjectEntity));
                projectRepo.save(skillSeekerProjectData.get());
                return modelMapper.map(skillSeekerProjectData, SkillSeekerProject.class);

            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            throw new ServiceException(INVALID_PROJECT_ID.getErrorCode(), INVALID_PROJECT_ID.getErrorDesc());
        } catch (Exception e) {
            throw new ServiceException(INVALID_SEEKER.getErrorCode(), INVALID_SEEKER.getErrorDesc());
        }
    }

}

