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
		Movie movie = new Movie();
		movie.setMovieView(this);/*Este this no se confunde con el objeto de aquí pq el 
		objeto y las características del objeto de this, se relacionan con el método padre 
		(osea el que llama a la función que encápsula otras funciones y líneas de código) que 
		se invoca cuando esta fuera de la clase. Si te confundes recuerda que el objeto this 
		se relaciona con el objeto que se usa para llamar las funciones (más  externas) 
		de la clase.  Si este metodo tiene mas metodos de la clase, aunque tengan
		un objeto de la misma clase, esos metodos no pasan el this pq no fueron llamados 
		fuera de la clase. 

		Recordar como hacen los métodos en una clase en python. Dentro de los métodos de 
		la clase siempre se pasa como argumento el argumento self (en Java this).  
		En Java pasa lo mismo, solo que no se ve.*/
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
