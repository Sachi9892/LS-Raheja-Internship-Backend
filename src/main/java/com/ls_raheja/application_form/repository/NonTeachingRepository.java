package com.ls_raheja.application_form.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ls_raheja.application_form.entity.non_teaching.NonTeachingApplicant;

public interface NonTeachingRepository extends JpaRepository<NonTeachingApplicant , Long> {
    
    
}
