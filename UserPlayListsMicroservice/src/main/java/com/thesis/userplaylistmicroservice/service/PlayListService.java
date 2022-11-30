package com.thesis.userplaylistmicroservice.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thesis.userplaylistmicroservice.library.PlayList;
import com.thesis.userplaylistmicroservice.library.audioBook.IdAudioBook;
import com.thesis.userplaylistmicroservice.repository.IdAudioBookRepository;
import com.thesis.userplaylistmicroservice.repository.PlayListRepository;
import com.thesis.userplaylistmicroservice.utils.SearchType;
import com.thesis.userplaylistmicroservice.utils.finder.impl.TitleFinder;
import com.thesis.userplaylistmicroservice.utils.finder.impl.UserFinder;

@Service
public class PlayListService {
	
	@Autowired
	private PlayListRepository playListRepository;
	
	@Autowired
	private IdAudioBookRepository audioBookRepository;
	
	public PlayList createPlayList(PlayList playlist) {
		List<PlayList> listAllPlayList = playListRepository.findAll();
		List<PlayList> resultList = searchPlayList(listAllPlayList, String.valueOf(playlist.getNamePlayList()),
				SearchType.SEARCH_PER_TITLE);
		if(resultList.size() == 0) {
			playListRepository.save(playlist);
			System.out.println("PlayList creada");
			return playlist;
		}else {
			System.out.println("Ya existe la playlist");
			return null;
		}
	}
	
	public List<PlayList> getAllPlayLists() {
		return playListRepository.findAll();
	}
	
	public Optional<PlayList> serchByIdPlayList(Integer idPlayList) {
		return playListRepository.findById(idPlayList);

	}

	public void deletePlayList(Integer idPlayList) {
		Optional<PlayList> resultList = playListRepository.findById(idPlayList);
		if(resultList.isEmpty()) {
			System.out.println("No se encontro la playlist");
		}else {
			playListRepository.deleteById(idPlayList);
			System.out.println("Playlist eliminada");
		}
	}
	
	public List<PlayList> searchPlayList(List<PlayList> playLists, String wordToSearch, SearchType searchType) {

		switch (searchType) {
	
		case SEARCH_PER_TITLE:

			TitleFinder namePlayList = new TitleFinder();
			return namePlayList.find(playLists, wordToSearch);
		
		case SERCH_PER_USER:
			
			UserFinder userPlayList = new UserFinder();
			return userPlayList.find(playLists, wordToSearch);

		default:
			return null;
		}

	}
	
	public List<PlayList> searchPlayLists(String searchType,String wordToSearch) {
		
		List<PlayList> listAllAudioBook = playListRepository.findAll();
		switch (searchType) {

		case "SEARCH_PER_TITLE":
			
			return this.searchPlayList(listAllAudioBook, wordToSearch, SearchType.SEARCH_PER_TITLE);
		
		case "SEARCH_PER_USER":
		
			return this.searchPlayList(listAllAudioBook, wordToSearch, SearchType.SERCH_PER_USER);

		default:
			return null;
		}
	}
	
	public IdAudioBook addAudioBook(Integer idPlayList, IdAudioBook audioBookList) {
		PlayList playList = this.serchByIdPlayList(idPlayList).orElse(null);
		Set<IdAudioBook> audioBooks = playList.getIdAudioBooks();
		audioBooks.add(audioBookList);
		audioBookRepository.save(audioBookList);
		playList.setIdAudioBooks(audioBooks);
		playListRepository.save(playList);
		return audioBookList;
	}
	
	public List<PlayList> getAllAudioBook() {
		return playListRepository.findAll();
	}
	
	
	public void deleteAudioBookToPlayList(Integer idPlayList, String idAudioBook) {
		PlayList playList = this.serchByIdPlayList(idPlayList).orElse(null);
		Set<IdAudioBook> audioBooks = playList.getIdAudioBooks();
		IdAudioBook resultIdAudioBook = null;
		for (IdAudioBook idAudioBookTarget : audioBooks) {
			if(idAudioBookTarget.getIdAudioBook().equals(idAudioBook)) {
				resultIdAudioBook = idAudioBookTarget;
			}	
		}
		audioBooks.remove(resultIdAudioBook);
		audioBookRepository.delete(resultIdAudioBook);
		playList.setIdAudioBooks(audioBooks);
		playListRepository.save(playList);
	}
}
