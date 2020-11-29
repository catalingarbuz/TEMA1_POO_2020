package operations;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import utils.VideoFavnr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unchecked")
public class FavoriteMoviesQuery extends VideoFavnr {

    static class Sortratingasc implements Comparator<VideoFavnr> {
        @Override
        public int compare(final VideoFavnr o1, final VideoFavnr o2) {
            Integer result = (o1.getFavnr() - o2.getFavnr());
            if (result.equals(0)) {
                result = o1.getTitle().compareTo(o2.getTitle());
            }
            return result;

        }
    }

    static class Sortratingdesc implements Comparator<VideoFavnr> {
        @Override
        public int compare(final VideoFavnr o1, final VideoFavnr o2) {
            Integer result = (o2.getFavnr() - o1.getFavnr());
            if (result.equals(0)) {
                result = o2.getTitle().compareTo(o1.getTitle());
            }
            return result;

        }
    }

    /**
     *
     * @param command command
     * @param users users
     * @param movies movies
     * @param arrayResult result
     * @param file file
     * @throws IOException the method may 'throw' an error
     */
    public void favoriteMoviesQuery(final ActionInputData command, final List<UserInputData> users,
                                    final List<MovieInputData> movies,
                                    final JSONArray arrayResult,
                                    final Writer file) throws IOException {
        ArrayList<VideoFavnr> videoratings = new ArrayList<>();
        ArrayList<String> finalresult = new ArrayList<>();
        Integer year = 0;
        String years = command.getFilters().get(0).get(0);
        if (years != null) {
            year = Integer.parseInt(years);
        }
        String genre = command.getFilters().get(1).get(0);
        ArrayList<String> filtermovies = new ArrayList<>();
        for (MovieInputData movie : movies) {
            if (year.equals(movie.getYear()) || year.equals(0)) {
                if (genre == null || movie.getGenres().contains(genre)) {
                    filtermovies.add(movie.getTitle());
                }
            }
        }
        for (String fmovie : filtermovies) {
            int favnr = 0;
            for (UserInputData user : users) {
                if (user.getFavoriteMovies().contains(fmovie)) {
                    favnr = favnr + 1;
                }
            }
            if (favnr > 0) {
                VideoFavnr videorating = new VideoFavnr(fmovie, favnr, 0);
                videoratings.add(videorating);
            }
        }
        if (command.getSortType().equals("asc")) {
            videoratings.sort(new Sortratingasc());
        } else {
            videoratings.sort(new Sortratingdesc());
        }
        if (command.getNumber() < videoratings.size()) {
            for (int i = 0; i < command.getNumber(); i++) {
                finalresult.add(videoratings.get(i).getTitle());
            }
        } else if (command.getNumber() > videoratings.size()) {
            for (VideoFavnr videorating : videoratings) {
                finalresult.add(videorating.getTitle());
            }
        }

        arrayResult.add(file.writeFile(command.getActionId(), "Querry", "Query result: "
                + finalresult.toString()));
    }
}
