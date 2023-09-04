/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.DirectorRepository;
import hr.algebra.model.Director;
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
public class SqlDirectorRepository implements DirectorRepository {

    private static final String ID_DIRECTOR = "IDDirector";
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";

    private static final String CREATE_DIRECTOR = "{ CALL CreateDirector (?,?,?) }";
    private static final String UPDATE_DIRECTOR = "{ CALL UpdateDirector (?,?,?) }";
    private static final String DELETE_DIRECTOR = "{ CALL DeleteDirector (?) }";
    private static final String SELECT_DIRECTOR = "{ CALL SelectDirector (?) }";
    private static final String SELECT_DIRECTORS = "{ CALL SelectDirectors }";

    @Override
    public int createDirector(Director director) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR);) {
            stmt.setString(FIRST_NAME, director.getFirstName());
            stmt.setString(LAST_NAME, director.getLastName());

            stmt.registerOutParameter(ID_DIRECTOR, Types.INTEGER);

            stmt.executeUpdate();

            return stmt.getInt(ID_DIRECTOR);
        }
    }

    @Override
    public void updateDirector(int id, Director director) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_DIRECTOR);) {
            stmt.setString(FIRST_NAME, director.getFirstName());
            stmt.setString(LAST_NAME, director.getLastName());
            stmt.setInt(ID_DIRECTOR, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_DIRECTOR);) {

            stmt.setInt(ID_DIRECTOR, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Director> selectDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_DIRECTOR);) {

            stmt.setInt(ID_DIRECTOR, id);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new Director(
                            rs.getInt(ID_DIRECTOR),
                            rs.getString(FIRST_NAME),
                            rs.getString(LAST_NAME)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Director> selectDirectors() throws Exception {
        List<Director> directors = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                directors.add(new Director(
                        rs.getInt(ID_DIRECTOR),
                        rs.getString(FIRST_NAME),
                        rs.getString(LAST_NAME)
                ));
            }
        }
        return directors;
    }

}
