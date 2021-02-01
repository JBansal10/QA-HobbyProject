package com.qa.hobbyproject.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.hobbyproject.persistence.domain.Mixes;

@Repository
public interface MixesRepo extends JpaRepository<Mixes, Long> {

}
