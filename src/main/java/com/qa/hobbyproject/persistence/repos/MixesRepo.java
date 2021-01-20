package com.qa.hobbyproject.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.hobbyproject.persistence.domain.Mixes;

public interface MixesRepo extends JpaRepository<Mixes, Long> {

}
