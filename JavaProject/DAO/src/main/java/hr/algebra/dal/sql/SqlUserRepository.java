/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.UserRepository;
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
public class SqlUserRepository implements UserRepository{
    
    private static final String ID_USER = "IDUser";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String ROLE = "Role";
    
    private static final String CREATE_USER = "{ CALL CreateUser (?,?,?,?) }";
    private static final String UPDATE_USER = "{ CALL UpdateUser (?,?,?,?) }";
    private static final String DELETE_USER = "{ CALL DeleteUser (?) }";
    private static final String SELECT_USER = "{ CALL SelectUser (?) }";
    private static final String SELECT_USERS = "{ CALL SelectUsers }";
    private static final String AUTHENTICATE_USER = "{ CALL AuthenticateApplicationUser (?,?) }";

    @Override
    public int createUser(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_USER);) {
            stmt.setString(USERNAME, user.getUsername());
            stmt.setString(PASSWORD, user.getPassword());
            stmt.setString(ROLE, user.getRole());

            stmt.registerOutParameter(ID_USER, Types.INTEGER);

            stmt.executeUpdate();

            return stmt.getInt(ID_USER);
        }
    }

    @Override
    public void updateUser(int id, User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_USER);) {
            stmt.setString(USERNAME, user.getUsername());
            stmt.setString(PASSWORD, user.getPassword());
            stmt.setString(ROLE, user.getRole());
            
            stmt.setInt(ID_USER, id);
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteUser(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_USER);) {

            stmt.setInt(ID_USER, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<User> selectUser(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_USER);) {

            stmt.setInt(ID_USER, id);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new User(
                            rs.getInt(ID_USER),
                            rs.getString(USERNAME),
                            rs.getString(PASSWORD),
                            rs.getString(ROLE)
                    ));
                }
            } 
        }
        return Optional.empty();
    }

    @Override
    public List<User> selectUsers() throws Exception {
        List<User> users = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_USERS); 
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                users.add(new User(
                            rs.getInt(ID_USER),
                            rs.getString(USERNAME),
                            rs.getString(PASSWORD),
                            rs.getString(ROLE)
                    ));
            }
        }
        return users;
    }

    @Override
    public Optional<String> authenticateUser(String username, String password) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(AUTHENTICATE_USER);) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(
                            rs.getString(ROLE)
                    );
                }
            }
        }
        return Optional.empty();
    }
}
