/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.MovieDirectorRepository;
import hr.algebra.model.MovieDirector;
import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author antem_jizaagf
 */
public class SqlMovieDirectorRepository implements MovieDirectorRepository {
    
    private static final String ID_MOVIE_DIRECTOR = "IDMovieDirector";
    private static final String MOVIE_ID = "MovieId";
    private static final String DIRECTOR_ID = "DirectorId";
    
    private static final String CREATE_MOVIE_DIRECTOR = "{ CALL CreateMovieDirector (?,?,?) }";
    private static final String UPDATE_MOVIE_DIRECTOR = "{ CALL UpdateMovieDirector (?,?,?) }";
    private static final String DELETE_MOVIE_DIRECTOR = "{ CALL DeleteMovieDirector (?) }";
    private static final String SELECT_MOVIE_DIRECTOR = "{ CALL SelectMovieDirector (?) }";
    private static final String SELECT_MOVIE_DIRECTORS = "{ CALL SelectMovieDirectors }";

    @Override
    public int createMovieDirector(MovieDirector movieDirector) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE_DIRECTOR);) {
            stmt.setInt(MOVIE_ID, movieDirector.getMovieId());
            stmt.setInt(DIRECTOR_ID, movieDirector.getDirectorId());

            stmt.registerOutParameter(ID_MOVIE_DIRECTOR, Types.INTEGER);

            stmt.executeUpdate();

            return stmt.getInt(ID_MOVIE_DIRECTOR);
        }
    }

    @Override
    public void updateMovieDirector(int id, MovieDirector movieDirector) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_MOVIE_DIRECTOR);) {
            stmt.setInt(MOVIE_ID, movieDirector.getMovieId());
            stmt.setInt(DIRECTOR_ID, movieDirector.getDirectorId());
            stmt.setInt(ID_MOVIE_DIRECTOR, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteMovieDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_MOVIE_DIRECTOR);) {

            stmt.setInt(ID_MOVIE_DIRECTOR, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<MovieDirector> selectMovieDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MOVIE_DIRECTOR);) {

            stmt.setInt(ID_MOVIE_DIRECTOR, id);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new MovieDirector(
                            rs.getInt(ID_MOVIE_DIRECTOR),
                            rs.getInt(MOVIE_ID),
                            rs.getInt(DIRECTOR_ID)
                    ));
                }
            } 
        }
        return Optional.empty();
    }

    @Override
    public List<MovieDirector> selectMovieDirectors() throws Exception {
        List<MovieDirector> movieDirectors = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE_DIRECTORS); 
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                movieDirectors.add(new MovieDirector(
                            rs.getInt(ID_MOVIE_DIRECTOR),
                            rs.getInt(MOVIE_ID),
                            rs.getInt(DIRECTOR_ID)
                    ));
            }
        }
        return movieDirectors;
    }
    
}
