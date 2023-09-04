/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.MovieActor;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author antem_jizaagf
 */
public interface MovieActorRepository {
    
    int createMovieActor(MovieActor actor) throws Exception;
    void updateMovieActor(int id, MovieActor data) throws Exception;
    void deleteMovieActor(int id) throws Exception;
    Optional<MovieActor> selectMovieActor(int id) throws Exception;
    List<MovieActor> selectMovieActors() throws Exception;
}
