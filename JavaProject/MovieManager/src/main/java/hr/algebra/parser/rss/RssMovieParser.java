/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.parser.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.model.Movie;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.Actor;
import hr.algebra.model.Director;
import hr.algebra.utilities.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.UUID;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author antem_jizaagf
 */
public class RssMovieParser {

    private static final String RSS_URL = "https://www.blitz-cinestar-bh.ba/rss.aspx?id=2682";
    private static final String ATTRIBUTE_URL = "url";
    private static final String EXT = ".jpg";
    private static final String DIR = "data";

    private RssMovieParser() {
    }

    public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> movies = new ArrayList<>();

        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);

        try (InputStream is = con.getInputStream()) {
            XMLEventReader reader = ParserFactory.createStaxParser(is);

            Optional<TagType> tagType = Optional.empty();
            Movie movie = null;
            StartElement startElement = null;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);
                        if (tagType.isPresent() && tagType.get().equals(TagType.ITEM)) {
                            movie = new Movie();
                            movies.add(movie);
                        }
                    }

                    case XMLStreamConstants.CHARACTERS -> {
                        if (tagType.isPresent()) {
                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();
                            switch (tagType.get()) {
                                case TITLE -> {
                                    if (!data.isEmpty()) {
                                        movie.setTitle(data);
                                    }
                                }
                                case PUBLISHED_DATE -> {
                                    if (!data.isEmpty()) {
                                        LocalDateTime publishedDate = LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME);
                                        movie.setPublishedDate(publishedDate);
                                    }
                                }
                                case DESCRIPTION -> {
                                    if (!data.isEmpty()) {
                                        movie.setDescription(data);
                                    }
                                }
                                case ORIGINAL_NAME -> {
                                    if (!data.isEmpty()) {
                                        movie.setOriginalTitle(data);
                                    }
                                }
                                case REDATELJ -> {
                                    if (!data.isEmpty()) {
                                        List<Director> directors = createObjectList(data, Director.class);
                                        for (Director director : directors) {
                                            movie.getDirectors().add(director);
                                        }
                                    }
                                }

                                case GLUMCI -> {
                                    if (!data.isEmpty()) {
                                        List<Actor> actors = createObjectList(data, Actor.class);
                                        for (Actor actor : actors) {
                                            movie.getActors().add(actor);
                                        }
                                    }
                                }
                                case TRAJANJE -> {
                                    if (!data.isEmpty()) {
                                        movie.setDuration(Integer.parseInt(data));
                                    }
                                }
                                case GODINA -> {
                                    if (!data.isEmpty()) {
                                        movie.setYearOfRelease(Integer.parseInt(data));
                                    }
                                }

                                case ZANR -> {
                                    if (!data.isEmpty()) {
                                        movie.setGenre(data);
                                    }
                                }
                                case PLAKAT -> {
                                    if (!data.isEmpty() && startElement != null) {
                                        handlePicture(movie, data);
                                    }
                                }
                                case LINK -> {
                                    if (!data.isEmpty()) {
                                        movie.setLink(data);
                                    }
                                }
                                case REZERVACIJA -> {
                                    if (!data.isEmpty()) {
                                        movie.setReservation(data);
                                    }
                                }
                                case TRAILER -> {
                                    if (!data.isEmpty()) {
                                        movie.setTrailer(data);
                                    }
                                }

                            }
                        }
                    }
                }
            }

        }

        return movies;
    }

    private static void handlePicture(Movie movie, String data) {
        try {
            String ext = data.substring(data.lastIndexOf("."));

            if (ext.length() > 4) {
                ext = EXT;
            }

            String pictureName = UUID.randomUUID() + ext;
            String localPicturePath = DIR + File.separator + pictureName;

            FileUtils.copyFromUrl(data, localPicturePath);

            movie.setPoster(localPicturePath);
        } catch (IOException ex) {
            Logger.getLogger(RssMovieParser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private enum TagType {

        ITEM("item"),
        TITLE("title"),
        PUBLISHED_DATE("pubDate"),
        DESCRIPTION("description"),
        ORIGINAL_NAME("orignaziv"),
        REDATELJ("redatelj"),
        GLUMCI("glumci"),
        TRAJANJE("trajanje"),
        GODINA("godina"),
        ZANR("zanr"),
        PLAKAT("plakat"),
        LINK("link"),
        REZERVACIJA("rezervacija"),
        TRAILER("trailer");

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }

    private static <T> List<T> createObjectList(String data, Class<T> objectClass) {
        List<T> objects = new ArrayList<>();

        String[] objectDataArray = data.split(",");
        for (String objectData : objectDataArray) {
            String[] fields = objectData.trim().split(" ", 2);

            try {
                T object = objectClass.getConstructor(String.class, String.class)
                        .newInstance(fields[0], fields[1]);
                objects.add(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return objects;
    }

}
