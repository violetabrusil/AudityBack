package com.thesis.userplaylistmicroservice.utils.finder.impl;

import java.util.ArrayList;
import java.util.List;

import com.thesis.userplaylistmicroservice.library.PlayList;
import com.thesis.userplaylistmicroservice.utils.finder.Finder;

public class TitleFinder extends Finder<PlayList> {
	

	public TitleFinder() {
		super();
	}

	@Override
	public int compare(PlayList o1, PlayList o2) {
		
		if(o1.getNamePlayList().contains(o2.getNamePlayList())) {
			return 0;
		}
		else{
			return -1;
		}
	}


	@Override
	public List<PlayList> find(List<PlayList> playLists ,String wordToFind) {
		List<PlayList> resultList = new ArrayList<PlayList>();
		PlayList temp = new PlayList();
		temp.setNamePlayList(wordToFind);
		for (PlayList playlist : playLists) {
			
			if(this.compare(playlist, temp) == 0) {
				resultList.add(playlist);
			}
			
		}
		return resultList;
	}

}
