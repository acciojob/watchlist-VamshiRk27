package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MovieRepository {
    HashMap<String,Movie> movieDb;
    HashMap<String,Director> directorDb;
    HashMap<String,String> movieDirectorPair;

    public MovieRepository() {
        this.movieDb=new HashMap<>();
        this.directorDb=new HashMap<>();
        this.movieDirectorPair=new HashMap<>();
    }
    //1 Add Movie
    public String addMovie(Movie movie){
        String key=movie.getName();
        if(!movieDb.containsKey(key)){
            movieDb.put(key,movie);
            return "Movie added Successfully";
        }
        return "Movie already exists";
    }
    //2 Add Director
    public String addDirector(Director director){
        String key=director.getName();
        if(!directorDb.containsKey(key)){
            directorDb.put(key,director);
            return "Director added Successfully";
        }
        return "Director already exists";
    }
    //3 Pairing movie and director
    public String addMovieDirectorPair(String movieName,String directorName){
        movieDirectorPair.put(movieName,directorName);
        return "Movie & Director pair added successfully";
    }
    public Movie getMovieByName(String name){
        if(movieDb.containsKey(name)){
            return movieDb.get(name);
        }
        return null;
    }
    public Director getDirectorByName(String name){
        if(directorDb.containsKey(name)){
            return directorDb.get(name);
        }
        return null;
    }
    public HashMap<String,Movie> getAllMovies(){
        return movieDb;
    }
    public HashMap<String,String> getAllMovieDirectorPairs(){
        return movieDirectorPair;
    }
    public String deleteDirectorByName(String name){
        directorDb.remove(name);
        for(Map.Entry<String,String> entry:movieDirectorPair.entrySet()) {
            if (entry.getValue().equals(name)) {
                String movieName = entry.getKey();
                movieDb.remove(movieName);
                movieDirectorPair.remove(movieName);
            }
        }
        return "Director removed Successfully";
    }
    public String deleteAllDirectors() {
        for(String director:directorDb.keySet()) {
//            for(Map.Entry<String,String> entry:movieDirectorPair.entrySet()) {
//                if (entry.getValue().equals(director)) {
//                    String movieName = entry.getKey();
//                    movieDb.remove(movieName);
//                    movieDirectorPair.remove(movieName);
//                }
//            }
            for(String movie:movieDirectorPair.keySet()) {
                if(movieDirectorPair.get(movie).equals(director)) {
                    movieDirectorPair.remove(movie);
                    movieDb.remove(movie);
                }
            }
            directorDb.remove(director);
        }
        return "Successfully removed Everything";
    }
}
