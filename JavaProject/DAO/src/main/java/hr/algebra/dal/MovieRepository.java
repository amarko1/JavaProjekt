/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.Movie;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author antem_jizaagf
 */
public interface MovieRepository {
    
    int createMovie(Movie movie) throws Exception;
    void updateMovie(int id, Movie data) throws Exception;
    void deleteMovie(int id) throws Exception;
    Optional<Movie> selectMovie (int id) throws Exception;
    List<Movie> selectMovies() throws Exception;
}
