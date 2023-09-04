/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author antem_jizaagf
 */
public class Movie implements Comparable<Movie>{
    
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    
    private int id;
    private String title;
    private LocalDateTime publishedDate;
    private String Description;
    private String originalTitle;
    private int duration;
    private int yearOfRelease;
    private String poster;
    private String link;
    private String reservation;
    private LocalDateTime displayDate;
    private String trailer;
    private String genre;
    private List<Director> directors = new ArrayList<>();
    private List<Actor> actors = new ArrayList<>();

    public Movie() {
    }

    public Movie(String title, LocalDateTime publishedDate, String Description, String originalTitle, int duration, int yearOfRelease, String poster, String link, String reservation, LocalDateTime displayDate, String trailer, String genre) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.Description = Description;
        this.originalTitle = originalTitle;
        this.duration = duration;
        this.yearOfRelease = yearOfRelease;
        this.poster = poster;
        this.link = link;
        this.reservation = reservation;
        this.displayDate = displayDate;
        this.trailer = trailer;
        this.genre = genre;
    }

    public Movie(int id, String title, LocalDateTime publishedDate, String Description, String originalTitle, int duration, int yearOfRelease, String poster, String link, String reservation, LocalDateTime displayDate, String trailer, String genre) {
        this(title, publishedDate, Description, originalTitle, duration, yearOfRelease, poster, link, reservation, displayDate, trailer, genre);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    public LocalDateTime getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(LocalDateTime displayDate) {
        this.displayDate = displayDate;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.title);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        return Objects.equals(this.title, other.title);
    }

    @Override
    public int compareTo(Movie o) {
        return title.compareTo(o.title);
    }
    
}
