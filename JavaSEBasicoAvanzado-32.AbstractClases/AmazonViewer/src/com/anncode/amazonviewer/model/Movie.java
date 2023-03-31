package com.anncode.amazonviewer.model;

import java.util.ArrayList;
import java.util.Date;

import com.anncode.amazonviewer.dao.MovieDAO;

public class Movie extends Film implements IVisualizable, MovieDAO {
	
	private int id;
	private int timeViewed;
	
	
	public Movie(String title, String genre, String creator, int duration, short year) {
		super(title, genre, creator, duration);
		setYear(year);
	}

	public Movie() {
	}

	public void setId(int id){
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	
	public int getTimeViewed() {
		return timeViewed;
	}
	public void setTimeViewed(int timeViewed) {
		this.timeViewed = timeViewed;
	}
	
	@Override
	public String toString() {
		return  "\n :: MOVIE ::" + 
				"\n Title: " + getTitle() +
				"\n Genero: " + getGenre() + 
				"\n Year: " + getYear() + 
				"\n Creator: " + getCreator() +
				"\n Duration: " + getDuration();
	}

	@Override
	public Date startToSee(Date dateI) {
		return dateI;
	}

	@Override
	public void stopToSee(Date dateI, Date dateF) {
		
		if (dateF.getTime() > dateI.getTime()) {
			setTimeViewed((int)(dateF.getTime() - dateI.getTime()));
		}else {
			setTimeViewed(0);
		}
		
		
	}
	
	public static ArrayList<Movie> makeMoviesList() {
		Movie movie = new Movie();	
		return movie.read();
	}


	@Override
	public void view() {
		setViewed(true);
		setMovieView(this);
		Date dateI = startToSee(new Date());
				
		for (int i = 0; i < 100; i++) {
			System.out.println("..........");
		}
				
		//Termine de verla
		stopToSee(dateI, new Date());
		System.out.println();
		System.out.println("Viste: " + toString());
		System.out.println("Por: " + getTimeViewed() + " milisegundos");

	}
}
