package com.flexcub.resourceplanning.skillseeker.service.impl;

import com.flexcub.resourceplanning.exceptions.ServiceException;
import com.flexcub.resourceplanning.skillseeker.entity.SkillSeekerTaskEntity;
import com.flexcub.resourceplanning.skillseeker.repository.SkillSeekerTaskRepository;
import com.flexcub.resourceplanning.skillseeker.service.SkillSeekerTaskService;
import com.flexcub.resourceplanning.skillseekeradmin.dto.SkillSeekerTask;
import com.flexcub.resourceplanning.utils.NullPropertyName;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.flexcub.resourceplanning.utils.FlexcubErrorCodes.INVALID_PROJECT_ID;
import static com.flexcub.resourceplanning.utils.FlexcubErrorCodes.INVALID_TASK_ID;

@Service
public class SkillSeekerTaskServiceImpl implements SkillSeekerTaskService {

    @Autowired
    SkillSeekerTaskRepository taskRepository;

    Logger logger = LoggerFactory.getLogger(SkillSeekerTaskServiceImpl.class);

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<SkillSeekerTask> insertData(List<SkillSeekerTask> skillSeekerTaskList) {
        List<SkillSeekerTaskEntity> skillSeekerTaskEntities = new ArrayList<>();
        try {
            for (SkillSeekerTask skillSeekerTask1 : skillSeekerTaskList) {
                SkillSeekerTaskEntity skillSeekerTaskEntity1 = modelMapper.map(skillSeekerTask1, SkillSeekerTaskEntity.class);

                if(null == skillSeekerTask1.getSkillSeekerProjectEntity() || skillSeekerTask1.getSkillSeekerProjectEntity().getId() == 0){
                    skillSeekerTaskEntity1.setSkillSeekerId(skillSeekerTask1.getSkillSeeker().getId());
                    skillSeekerTaskEntity1.setSkillSeekerProject(null);
                }
                skillSeekerTaskEntities.add(skillSeekerTaskEntity1);
//                skillSeekerTaskEntities.forEach(skillSeekerProject -> skillSeekerProject.setSkillSeekerTechnologyData(skillSeekerTechnologyService.insertMultipleData(skillSeekerProject.getSkillSeekerTechnologyData())));
                taskRepository.saveAll(skillSeekerTaskEntities);
            }

            logger.info("SkillSeekerTaskServiceImpl || insertData || Inserting the SeekerTask list: {}", skillSeekerTaskList);

            List<SkillSeekerTask> skillSeekerTaskLists = new ArrayList<>();
            for (SkillSeekerTaskEntity skillSeekerTaskEntity : skillSeekerTaskEntities) {
                SkillSeekerTask skillSeekerTask = modelMapper.map(skillSeekerTaskEntity, SkillSeekerTask.class);
                skillSeekerTaskLists.add(skillSeekerTask);
            }
            return skillSeekerTaskLists;
        } catch (Exception e) {
            throw new ServiceException(INVALID_PROJECT_ID.getErrorCode(), INVALID_PROJECT_ID.getErrorDesc());
        }
    }

    @Override
    public List<SkillSeekerTask> getTaskData(int id,int skillSeekerId) {
        List<SkillSeekerTask> skillSeekerTaskList = new ArrayList<>();
        Optional<List<SkillSeekerTaskEntity>> skillSeekerTask;
        if(id == 0){
            skillSeekerTask = taskRepository.findBySkillSeekerProjectIdAndSkillSeekerId(skillSeekerId);
        }
        else {
            skillSeekerTask = taskRepository.findBySkillSeekerProjectId(id);
        }
        if (!skillSeekerTask.get().isEmpty()) {
            for (SkillSeekerTaskEntity skillSeekerTaskEntity : skillSeekerTask.get()) {
                SkillSeekerTask skillSeekerTask1 = modelMapper.map(skillSeekerTaskEntity, SkillSeekerTask.class);
                skillSeekerTaskList.add(skillSeekerTask1);
            }
            return skillSeekerTaskList;
        } else {
            logger.info("SkillSeekerTaskServiceImpl || getTaskData || No tasks found for given project id");
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteData(int id) {

        Optional<SkillSeekerTaskEntity> skillSeekerTaskData = taskRepository.findById(id);

        if (skillSeekerTaskData.isPresent()) {
            logger.info("SkillSeekerTaskServiceImpl || deleteData || Deleted the SeekerTask id: {}", id);
            taskRepository.deleteById(id);
        } else {
            throw new ServiceException(INVALID_TASK_ID.getErrorCode(), INVALID_TASK_ID.getErrorDesc());
        }

    }

    @Override
    public SkillSeekerTask updateData(SkillSeekerTask skillSeekerTask) {
        SkillSeekerTaskEntity seekerTaskEntity = modelMapper.map(skillSeekerTask, SkillSeekerTaskEntity.class);
        try {
            Optional<SkillSeekerTaskEntity> skillSeekerTaskData = taskRepository.findById(seekerTaskEntity.getId());
            if (skillSeekerTaskData.isPresent()) {
                logger.info("SkillSeekerTaskServiceImpl || updateData || Updated the SkillSeeker Task");
                BeanUtils.copyProperties(seekerTaskEntity, skillSeekerTaskData.get(), NullPropertyName.getNullPropertyNames(seekerTaskEntity));
                taskRepository.save(skillSeekerTaskData.get());
                return modelMapper.map(skillSeekerTaskData, SkillSeekerTask.class);

            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            throw new ServiceException(INVALID_TASK_ID.getErrorCode(), INVALID_TASK_ID.getErrorDesc());
        } catch (Exception e) {
            throw new ServiceException(INVALID_PROJECT_ID.getErrorCode(), INVALID_PROJECT_ID.getErrorDesc());
        }
    }
}



