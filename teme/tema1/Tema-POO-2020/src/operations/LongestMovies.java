package operations;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import utils.MovieDuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LongestMovies extends MovieDuration {

    static class Sortratingasc implements Comparator<MovieDuration> {
        @Override
        public int compare(final MovieDuration o1, final MovieDuration o2) {
            Integer result = (o1.getDuration() - o2.getDuration());
            if (result.equals(0)) {
                result = o1.getTitle().compareTo(o2.getTitle());
            }
            return result;

        }
    }

    static class Sortratingdesc implements Comparator<MovieDuration> {
        @Override
        public int compare(final MovieDuration o1, final MovieDuration o2) {
            Integer result = (o2.getDuration() - o1.getDuration());
            if (result.equals(0)) {
                result = o2.getTitle().compareTo(o1.getTitle());
            }
            return result;
        }
    }

    /**
     *
     * @param command command
     * @param movies movies
     * @param arrayResult result
     * @param file file
     * @throws IOException exception
     */
    public void longestMovies(final ActionInputData command, final List<MovieInputData> movies,
                              final JSONArray arrayResult, final Writer file) throws IOException {
        ArrayList<String> finalresult = new ArrayList<>();
        ArrayList<MovieDuration> movieDurations = new ArrayList<>();
        Integer year = 0;
        String years = command.getFilters().get(0).get(0);
        if (years != null) {
            year = Integer.parseInt(years);
        }
        String genre = command.getFilters().get(1).get(0);
        for (MovieInputData movie : movies) {
            if (year.equals(0) || year.equals(movie.getYear())) {
                if (genre == null || movie.getGenres().contains(genre)) {
                    MovieDuration movieDuration = new MovieDuration(movie.getTitle(),
                            movie.getDuration());
                    movieDurations.add(movieDuration);
                }
            }
        }
        if (command.getSortType().equals("asc")) {
            movieDurations.sort(new Sortratingasc());
        } else {
            movieDurations.sort(new Sortratingdesc());
        }
        if (command.getNumber() < movieDurations.size()) {
            for (int i = 0; i < command.getNumber(); i++) {
                finalresult.add(movieDurations.get(i).getTitle());
            }
        } else if (command.getNumber() > movieDurations.size()) {
            for (MovieDuration movieDuration : movieDurations) {
                finalresult.add(movieDuration.getTitle());
            }
        }

        arrayResult.add(file.writeFile(command.getActionId(), "Querry", "Query result: "
                + finalresult.toString()));

    }
}
