package com.thesis.userplaylistmicroservice.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thesis.userplaylistmicroservice.library.PlayList;
import com.thesis.userplaylistmicroservice.library.audioBook.IdAudioBook;
import com.thesis.userplaylistmicroservice.service.PlayListService;

@RestController
@RequestMapping ("/api/playList/")
@CrossOrigin ("*")
public class PlayListREST {
	
	@Autowired
	private PlayListService playListService;
	
	@PostMapping ("/createPlayList")
	private ResponseEntity<PlayList> createPlayList (@RequestBody PlayList playList){
		PlayList temp = playListService.createPlayList(playList);
		
		try {
			if(temp == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			}else {
				return ResponseEntity.created(new URI("/api/playList"+ temp.getIdPlayList())).body(temp);
			}
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	
	}
	
	@PostMapping ("/addAudioBookToPlayList/{idPlayList}")
	private IdAudioBook addAudioBook (@PathVariable(name = "idPlayList") Integer idPlayList, 
			@RequestBody IdAudioBook audioBookList ){
		return playListService.addAudioBook(idPlayList, audioBookList);
	
	}

	@RequestMapping(value = {"/searchByNamePlayList/{typeSearch}/{namePlayList}"} , method = RequestMethod.GET)
	private ResponseEntity<List<PlayList>> searchAudioBookByNamePlayList (@PathVariable(name = "typeSearch") String typeSearch,@PathVariable(name = "namePlayList") String namePlayList) {
		return ResponseEntity.ok(playListService.searchPlayListsPerSearchType(typeSearch,namePlayList));
	}
	
	@DeleteMapping("/deletePlayList/{idPlayList}")
	private ResponseEntity<Void> deleteAudioBook (@PathVariable(name = "idPlayList") Integer idPlayList) {
		playListService.deletePlayList(idPlayList);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = {"/searchAudioBooks/{idPlayList}"} , method = RequestMethod.GET)
	private String searchAudioBooks (@PathVariable(name = "idPlayList") Integer idPlayList) {
		return (playListService.getAllAudioBooks(idPlayList));
	}
	
	@RequestMapping ("/getAllPlayLists")
	private List<PlayList> listAllPlayLists(){
		return playListService.getAllAudioBook();
	}
	
	@RequestMapping(value = {"/searchById/{idPlayList}"} , method = RequestMethod.GET)
	private ResponseEntity<Optional<PlayList>> searchPlayListById (@PathVariable(name = "idPlayList") Integer idPlayList ){
		return ResponseEntity.ok(playListService.searchByIdPlayList(idPlayList));
	}
	
	/*@DeleteMapping("/deleteAudioBookToPlayList/{idPlayList}/{idAudioBook}")
	private ResponseEntity<Void> deleteAudioBookToPlayList (@PathVariable(name = "idPlayList") Integer idPlayList, @PathVariable (name = "idAudioBook") String idAudioBook) {
		playListService.deleteAudioBookToPlayList(idPlayList, idAudioBook);
		return ResponseEntity.ok().build();
	}*/
	
	

}
