/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.MovieRepository;
import hr.algebra.model.Movie;
import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author antem_jizaagf
 */
public class SqlMovieRepository implements MovieRepository {

    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String PUBLISHED_DATE = "PublishedDate";
    private static final String DESCRIPTION = "Description";
    private static final String ORIGINAL_TITLE = "OriginalTitle";
    private static final String DURATION = "Duration";
    private static final String YEAR_OF_RELEASE = "YearOfRelease";
    private static final String GENRE = "Genre";
    private static final String POSTER = "Poster";
    private static final String LINK = "Link";
    private static final String RESERVATION = "Reservation";
    private static final String DISPLAY_DATE = "DisplayDate";
    private static final String TRAILER = "Trailer";

    private static final String CREATE_MOVIE = "{ CALL CreateMovie (?,?,?,?,?,?,?,?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL UpdateMovie (?,?,?,?,?,?,?,?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL DeleteMovie (?) }";
    private static final String SELECT_MOVIE = "{ CALL SelectMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL SelectMovies }";

    @Override
    public int createMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE);) {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(PUBLISHED_DATE, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setString(DESCRIPTION, movie.getDescription());
            stmt.setString(ORIGINAL_TITLE, movie.getOriginalTitle());
            stmt.setInt(DURATION, movie.getDuration());
            stmt.setInt(YEAR_OF_RELEASE, movie.getYearOfRelease());
            stmt.setString(GENRE, movie.getGenre());
            stmt.setString(POSTER, movie.getPoster());
            stmt.setString(LINK, movie.getLink());
            stmt.setString(RESERVATION, movie.getReservation());
            stmt.setString(DISPLAY_DATE, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setString(TRAILER, movie.getTrailer());

            stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);

            stmt.executeUpdate();

            return stmt.getInt(ID_MOVIE);
        }
    }

    @Override
    public void updateMovie(int id, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_MOVIE);) {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(PUBLISHED_DATE, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setString(DESCRIPTION, movie.getDescription());
            stmt.setString(ORIGINAL_TITLE, movie.getOriginalTitle());
            stmt.setInt(DURATION, movie.getDuration());
            stmt.setInt(YEAR_OF_RELEASE, movie.getYearOfRelease());
            stmt.setString(GENRE, movie.getGenre());
            stmt.setString(POSTER, movie.getPoster());
            stmt.setString(LINK, movie.getLink());
            stmt.setString(RESERVATION, movie.getReservation());
            stmt.setString(DISPLAY_DATE, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setString(TRAILER, movie.getTrailer());
            
            stmt.setInt(ID_MOVIE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_MOVIE);) {

            stmt.setInt(ID_MOVIE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Movie> selectMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MOVIE);) {

            stmt.setInt(ID_MOVIE, id);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            LocalDateTime.parse(
                                    rs.getString(PUBLISHED_DATE),
                                    Movie.DATE_FORMATTER),
                            rs.getString(DESCRIPTION),
                            rs.getString(ORIGINAL_TITLE),
                            rs.getInt(DURATION),
                            rs.getInt(YEAR_OF_RELEASE),
                            rs.getString(POSTER),
                            rs.getString(LINK),
                            rs.getString(RESERVATION),
                            LocalDateTime.parse(
                                    rs.getString(DISPLAY_DATE),
                                    Movie.DATE_FORMATTER),
                            rs.getString(TRAILER),
                            rs.getString(GENRE)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> selectMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MOVIES);) {

            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    movies.add(new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            LocalDateTime.parse(
                                    rs.getString(PUBLISHED_DATE),
                                    Movie.DATE_FORMATTER),
                            rs.getString(DESCRIPTION),
                            rs.getString(ORIGINAL_TITLE),
                            rs.getInt(DURATION),
                            rs.getInt(YEAR_OF_RELEASE),
                            rs.getString(POSTER),
                            rs.getString(LINK),
                            rs.getString(RESERVATION),
                            LocalDateTime.parse(
                                    rs.getString(DISPLAY_DATE),
                                    Movie.DATE_FORMATTER),
                            rs.getString(TRAILER),
                            rs.getString(GENRE)
                    ));
                }
            }
        }
        return movies;
    }

}
