package com.support.schedular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.support.schedular.entities.EngineerEntity;

@Repository
public interface EngineerRepository extends JpaRepository<EngineerEntity, Long> {


}
