package com.ls_raheja.application_form.repository;

import com.ls_raheja.application_form.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant , Long> {
}
