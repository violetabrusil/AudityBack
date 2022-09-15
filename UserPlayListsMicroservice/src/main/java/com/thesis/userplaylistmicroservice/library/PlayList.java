package com.thesis.userplaylistmicroservice.library;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.thesis.userplaylistmicroservice.library.audioBook.IdAudioBook;


@Entity
public class PlayList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPlayList;
	
	private String namePlayList;
	private String userId;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<IdAudioBook> idAudioBooks;

	public PlayList(Integer idPlayList, String namePlayList, String userId, Set<IdAudioBook> idAudioBooks) {
		super();
		this.idPlayList = idPlayList;
		this.namePlayList = namePlayList;
		this.userId = userId;
		this.idAudioBooks = idAudioBooks;
	}

	public PlayList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdPlayList() {
		return idPlayList;
	}

	public void setIdPlayList(Integer idPlayList) {
		this.idPlayList = idPlayList;
	}

	public String getNamePlayList() {
		return namePlayList;
	}

	public void setNamePlayList(String namePlayList) {
		this.namePlayList = namePlayList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Set<IdAudioBook> getIdAudioBooks() {
		return idAudioBooks;
	}

	public void setIdAudioBooks(Set<IdAudioBook> idAudioBooks) {
		this.idAudioBooks = idAudioBooks;
	}

}
