/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author antem_jizaagf
 */
public class MovieActor {
    private int id;
    private int movieId;
    private int actorId;

    private MovieActor() {
    }

    public MovieActor(int movieId, int actorId) {
        this.movieId = movieId;
        this.actorId = actorId;
    }

    public MovieActor(int id, int movieId, int actorId) {
        this.id = id;
        this.movieId = movieId;
        this.actorId = actorId;
    }

    public int getId() {
        return id;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getActorId() {
        return actorId;
    }
}
