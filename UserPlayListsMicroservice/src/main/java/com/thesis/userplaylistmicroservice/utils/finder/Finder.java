package com.thesis.userplaylistmicroservice.utils.finder;

import java.util.Comparator;
import java.util.List;

public abstract class Finder<T> implements Comparator<T> {
	
public Finder() {
	super();
}

public abstract List<T> find(List<T> listToSearch, String wordToFind);
   

}
