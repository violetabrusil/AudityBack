package com.thesis.userplaylistmicroservice.library.audioBook;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.thesis.userplaylistmicroservice.library.PlayList;

@Entity 
public class IdAudioBook {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idAudioBookPlayList;

	private String idAudioBook;

	public IdAudioBook(Integer idAudioBookPlayList, String idAudioBook) {
		super();
		this.idAudioBookPlayList = idAudioBookPlayList;
		this.idAudioBook = idAudioBook;
	}

	public IdAudioBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdAudioBookPlayList() {
		return idAudioBookPlayList;
	}

	public void setIdAudioBookPlayList(Integer idAudioBookPlayList) {
		this.idAudioBookPlayList = idAudioBookPlayList;
	}

	public String getIdAudioBook() {
		return idAudioBook;
	}

	public void setIdAudioBook(String idAudioBook) {
		this.idAudioBook = idAudioBook;
	}

	@Override
	public String toString() {
		return "IdAudioBook [idAudioBookPlayList=" + idAudioBookPlayList + ", idAudioBook=" + idAudioBook + "]";
	}
	
	

	

	

	

}
