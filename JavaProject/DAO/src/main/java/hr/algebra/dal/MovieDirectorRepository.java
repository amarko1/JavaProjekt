/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.MovieDirector;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author antem_jizaagf
 */
public interface MovieDirectorRepository {
    
    int createMovieDirector(MovieDirector movie) throws Exception;
    void updateMovieDirector(int id, MovieDirector data) throws Exception;
    void deleteMovieDirector(int id) throws Exception;
    Optional<MovieDirector> selectMovieDirector (int id) throws Exception;
    List<MovieDirector> selectMovieDirectors() throws Exception;
}
