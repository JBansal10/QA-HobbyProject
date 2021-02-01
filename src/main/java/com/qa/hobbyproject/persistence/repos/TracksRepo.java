package com.qa.hobbyproject.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.hobbyproject.persistence.domain.Tracks;

@Repository
public interface TracksRepo extends JpaRepository<Tracks, Long> {

}
