package com.driver;

import com.driver.models.Director;
import com.driver.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    //1 Add Movie
    public String addMovie(Movie movie){
        return movieRepository.addMovie(movie);
    }
    //2 Add Director
    public String addDirector(Director director){
        return movieRepository.addDirector(director);
    }
    //3 Add Movie-Director pair
    public String addMovieDirectorPair(String movieName,String directorName){
        return movieRepository.addMovieDirectorPair(movieName,directorName);
    }
    //4 Get movie by Name
    public Movie getMovieByName(String name){
        return movieRepository.getMovieByName(name);
    }
    //5 Get director by Name
    public Director getDirectorByName(String name){
        return movieRepository.getDirectorByName(name);
    }
    //6 Get movies By Director
    public List<String> getMoviesByDirectorName(String directorName){
        HashMap<String,String> list =movieRepository.getAllMovieDirectorPairs();
        List<String> movies = new ArrayList<>();
        for(Map.Entry<String,String> entry : list.entrySet()){
            if(entry.getValue().equals(directorName)){
                movies.add(entry.getKey());
            }
        }
        return movies;
    }
    //7 Get all movies
    public List<String> findAllMovies(){
        HashMap<String,Movie> movies =movieRepository.getAllMovies();
        // here an ArrayList is returned by using collection call
        return new ArrayList<>(movies.keySet());
    }
    //8 Delete director by name
    public String deleteDirectorByName(String directorName){
        return movieRepository.deleteDirectorByName(directorName);
    }
    //9 delete all directors
    public String deleteAllDirectors(){
        return movieRepository.deleteAllDirectors();
    }
}
