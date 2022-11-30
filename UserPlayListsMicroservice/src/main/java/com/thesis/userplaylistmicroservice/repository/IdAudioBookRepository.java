package com.thesis.userplaylistmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thesis.userplaylistmicroservice.library.audioBook.IdAudioBook;


public interface IdAudioBookRepository extends JpaRepository<IdAudioBook, Integer> {

}

