package com.anncode.amazonviewer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.anncode.amazonviewer.db.IDBConnection;
import com.anncode.amazonviewer.model.Movie;

public interface MovieDAO extends IDBConnection {
    default Movie setMovieView(Movie movie){
        return movie;
    }

    default ArrayList<Movie> read(){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        try(Connection connection = connectToDB()){
            String query = "SELECT * FROM movie";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Movie movie = new Movie(
                    resultSet.getString("title"), 
                    resultSet.getString("genre"), 
                    resultSet.getString("creator"), 
                    Integer.valueOf(resultSet.getString("duration")),
                    Short.valueOf(resultSet.getString("year"))
                );
                movie.setId(Integer.valueOf(resultSet.getString("id")));
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    private boolean getMovieViewed(){
        return false;
    }
}
