package com.anncode.amazonviewer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.anncode.amazonviewer.db.IDBConnection;
import com.anncode.amazonviewer.model.Movie;

public interface MovieDAO extends IDBConnection {
    default Movie setMovieView(Movie movie){
        try(Connection connection = connectToDB()) {
            Statement statement = connection.createStatement(); 
            // String query = "insert into viewed(id_material , id_element , id_user ) values (1,"+ movie.getId()+ ",1);";
            String query = "INSERT INTO viewed (id_material, id_element, id_user) "
            + "SELECT 1, "+ movie.getId()+ ", 1 "
            + "WHERE NOT EXISTS ("
            +     "SELECT 1 FROM viewed "
            +     "WHERE id_material = 1 AND id_element = "+ movie.getId() + " AND id_user = 1"
            + ");";

            if(statement.executeUpdate(query)>0){
                System.out.println("Se marco en visto dentro de la tabla viewed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                movie.setViewed(getMovieViewed(connection, Integer.valueOf(resultSet.getString("id"))));
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    private boolean getMovieViewed(Connection connection, int id_movie){
        boolean viewed = false;
        String query = "Select * from viewed where id_material =? and id_element=? and id_user=?;";
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1);// el primer uno represent el primer ? del query, en donde le vamos a poner el id 1 de la tabla material
            preparedStatement.setInt(2, id_movie);
            preparedStatement.setInt(3, 1);

            resultSet = preparedStatement.executeQuery();
            viewed = resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewed;
    }
}
