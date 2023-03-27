package com.anncode.amazonviewer.dao;

import java.util.ArrayList;

import com.anncode.amazonviewer.model.Movie;

public interface MovieDAO {
    default Movie setMovieView(Movie movie){
        return movie;
    }

    default ArrayList<Movie> read(){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        return movies;
    }

    private boolean getMovieViwed(){
        return false;
    }
}
