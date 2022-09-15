package com.thesis.userplaylistmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thesis.userplaylistmicroservice.library.PlayList;

public interface PlayListRepository extends JpaRepository<PlayList, Integer> {

}
