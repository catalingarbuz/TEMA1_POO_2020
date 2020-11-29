package operations;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PopularRecom {

    public static class GenresNr {
       private String genre;
       private Integer nr;

        public GenresNr(final String genre, final Integer nr) {
            this.genre = genre;
            this.nr = nr;
        }

        /**
         *
         * @return genre name
         */
        public String getGenre() {
            return genre;
        }

        /**
         *
         * @param genre set genre
         */
        public void setGenre(final String genre) {
            this.genre = genre;
        }

        /**
         *
         * @return nr of movies which contains genre
         */
        public Integer getNr() {
            return nr;
        }

        /**
         *
         * @param nr set nr of movies which contains genre
         */
        public void setNr(final Integer nr) {
            this.nr = nr;
        }
    }

    static class Sortratingdesc implements Comparator<GenresNr> {
        @Override
        public int compare(final GenresNr o1, final GenresNr o2) {
            return (o2.nr - o1.nr);
        }
    }

    /**
     *
     * @param movies movies
     * @param serials serials
     * @return array list of popular genres
     */
    public ArrayList<String> getGenres(final List<MovieInputData> movies,
                                       final List<SerialInputData> serials) {
        ArrayList<String> genres = new ArrayList<>();
        for (MovieInputData video : movies) {
            ArrayList<String> vgenres = video.getGenres();
            for (String gen : vgenres) {
                if (!genres.contains(gen)) {
                    genres.add(gen);
                }
            }
        }
        for (SerialInputData video : serials) {
            ArrayList<String> vgenres = video.getGenres();
            for (String gen : vgenres) {
                if (!genres.contains(gen)) {
                    genres.add(gen);
                }
            }
        }
        return genres;
    }

    /**
     *
     * @param username username from input
     * @param users list of users
     * @return user's from input, UserIputData
     */
    public UserInputData getUser(final String username, final List<UserInputData> users) {
        UserInputData user = null;
        for (UserInputData u : users) {
            if (username.equals(u.getUsername())) {
                user = u;
            }
        }
        return user;
    }

    /**
     *
     * @param command command
     * @param users list of users
     * @param movies list of movies
     * @param serials list of serials
     * @param arrayResult result
     * @param file file
     * @throws IOException exception
     */
    public void popularRecom(final ActionInputData command, final List<UserInputData> users,
                             final List<MovieInputData> movies,
                             final List<SerialInputData> serials,
                             final JSONArray arrayResult, final Writer file) throws IOException {
        UserInputData usr = getUser(command.getUsername(), users);
        if (usr == null || usr.getSubscriptionType().equals("BASIC")) {
            arrayResult.add(file.writeFile(command.getActionId(), "Recommendation",
                    "PopularRecommendation cannot be applied!"));
        } else {
            ArrayList<GenresNr> genresNrs = new ArrayList<>();
            ArrayList<String> genres = getGenres(movies, serials);
            for (String gen : genres) {
                int gennr = 0;
                for (UserInputData user : users) {
                    for (MovieInputData video : movies) {
                        if (video.getGenres().contains(gen)
                                && user.getHistory().containsKey(video.getTitle())) {
                            gennr = gennr + user.getHistory().get(video.getTitle());
                        }
                    }
                    for (SerialInputData video : serials) {
                        if (video.getGenres().contains(gen)
                                && user.getHistory().containsKey(video.getTitle())) {
                            gennr = gennr + user.getHistory().get(video.getTitle());
                        }
                    }
                }
                GenresNr genresNr = new GenresNr(gen, gennr);
                genresNrs.add(genresNr);
            }
            genresNrs.sort(new Sortratingdesc());
            boolean t = true;
            int idx = 0;
            String result = null;
            while (t) {
                String genre = genresNrs.get(idx).genre;
                for (MovieInputData video : movies) {
                    if (video.getGenres().contains(genre)
                            && !(usr.getHistory().containsKey(video.getTitle()))
                            && result == null) {
                        result = video.getTitle();
                    }
                }
                for (SerialInputData video : serials) {
                    if (video.getGenres().contains(genre)
                            && !(usr.getHistory().containsKey(video.getTitle()))
                            && result == null) {
                        result = video.getTitle();
                    }
                }
                if (result != null) {
                    t = false;
                }
                idx = idx + 1;
                if (idx >= genresNrs.size()) {
                    t = false;
                }
            }
            if (result != null) {
                arrayResult.add(file.writeFile(command.getActionId(), "Recommendation",
                        "PopularRecommendation result: " + result));
            } else {
                arrayResult.add(file.writeFile(command.getActionId(), "Recommendation",
                        "PopularRecommendation cannot be applied!"));
            }
        }
    }

}
