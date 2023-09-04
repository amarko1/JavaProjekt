/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author antem_jizaagf
 */
public interface UserRepository {
    
    int createUser(User user) throws Exception;
    void updateUser(int id, User data) throws Exception;
    void deleteUser(int id) throws Exception;
    Optional<User> selectUser (int id) throws Exception;
    List<User> selectUsers() throws Exception;
    Optional<String> authenticateUser (String username, String password) throws Exception;
}
