/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author antem_jizaagf
 */
public class MovieDirector {
    private int id;
    private int movieId;
    private int directorId;

    public MovieDirector() {
    }

    public MovieDirector(int id, int movieId, int directorId) {
        this.id = id;
        this.movieId = movieId;
        this.directorId = directorId;
    }

    public MovieDirector(int movieId, int directorId) {
        this.movieId = movieId;
        this.directorId = directorId;
    }

    public int getId() {
        return id;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getDirectorId() {
        return directorId;
    }
}
