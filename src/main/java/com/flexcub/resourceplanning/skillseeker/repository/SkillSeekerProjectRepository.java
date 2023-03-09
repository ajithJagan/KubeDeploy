package com.flexcub.resourceplanning.skillseeker.repository;

import com.flexcub.resourceplanning.skillseeker.entity.SkillSeekerProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillSeekerProjectRepository extends JpaRepository<SkillSeekerProjectEntity, Integer> {

    Optional<List<SkillSeekerProjectEntity>> findBySkillSeekerId(int id);
}
