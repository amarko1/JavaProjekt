/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.dal.sql.SqlActorRepository;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antem_jizaagf
 */
public class RepositoryFactory {
    
    private static final String PATH = "/config/repository.properties";
    
    private static final Properties properties = new Properties();
    
    private static final String CLASS_USER= "CLASS_USER";
    private static final String CLASS_ACTOR = "CLASS_ACTOR";
    private static final String CLASS_DIRECTOR = "CLASS_DIRECTOR";
    private static final String CLASS_MOVIE = "CLASS_MOVIE";
    private static final String CLASS_MOVIE_DIRECTOR = "CLASS_MOVIE_DIRECTOR";
    private static final String CLASS_MOVIE_ACTOR = "CLASS_MOVIE_ACTOR";
    
    
    private static UserRepository userRepository;
    private static MovieRepository movieRepository;
    private static DirectorRepository directorRepository;
    private static ActorRepository actorRepository;
    private static MovieActorRepository movieActorRepository;
    private static MovieDirectorRepository movieDirectorRepository;

    private RepositoryFactory() {
    }
    
    static {
        try (InputStream is = DataSourceSingleton.class.getResourceAsStream(PATH)) {
            properties.load(is);
            userRepository = createInstance(properties.getProperty(CLASS_USER));
            movieRepository = createInstance(properties.getProperty(CLASS_MOVIE));
            directorRepository = createInstance(properties.getProperty(CLASS_DIRECTOR));
            actorRepository = createInstance(properties.getProperty(CLASS_ACTOR));
            movieActorRepository = createInstance(properties.getProperty(CLASS_MOVIE_ACTOR));
            movieDirectorRepository = createInstance(properties.getProperty(CLASS_MOVIE_DIRECTOR));
              
        } catch (Exception ex) {
            Logger.getLogger(DataSourceSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private static <T> T createInstance(String className) {
        try {
            return (T) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
            Logger.getLogger(DataSourceSingleton.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public static DirectorRepository getDirectorRepository() {
        return directorRepository;
    }

    public static ActorRepository getActorRepository() {
        return actorRepository;
    }
    
    public static MovieActorRepository getMovieActorRepository() {
        return movieActorRepository;
    }
    
    public static MovieDirectorRepository getMovieDirectorRepository() {
        return movieDirectorRepository;
    }
    
}
