package com.ls_raheja.application_form.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ls_raheja.application_form.entity.degree.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant , Long> {
}
