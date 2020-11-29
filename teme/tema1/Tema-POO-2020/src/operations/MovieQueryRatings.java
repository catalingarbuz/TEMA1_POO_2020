package operations;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import utils.VideoRating;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MovieQueryRatings extends VideoRating {

    static class Sortratingasc implements Comparator<VideoRating> {
        @Override
        public int compare(final VideoRating o1, final VideoRating o2) {
            Integer result = (int) (o1.getRating() - o2.getRating());
            if (result.equals(0)) {
                result = o1.getTitle().compareTo(o2.getTitle());
            }
            return result;

        }
    }

    static class Sortratingdesc implements Comparator<VideoRating> {
        @Override
        public int compare(final VideoRating o1, final VideoRating o2) {
            Integer result = (int) (o2.getRating() - o1.getRating());
            if (result.equals(0)) {
                result = o2.getTitle().compareTo(o1.getTitle());
            }
            return result;

        }
    }

    /**
     *
     * @param command input command
     * @param movies list of movies
     * @param arrayResult result
     * @param file file
     * @throws IOException exception
     */
    public void movieQueryRatings(final ActionInputData command, final List<MovieInputData> movies,
                                  final JSONArray arrayResult,
                                  final Writer file) throws IOException {
        ArrayList<String> finalresult = new ArrayList<>();
        ArrayList<VideoRating> movieRatings = new ArrayList<>();
        Integer filteryear = Integer.parseInt(command.getFilters().get(0).get(0));
        for (MovieInputData movie : movies) {
            Integer year = movie.getYear();
            if (year.equals(filteryear)) {
                if (movie.getGenres().contains(command.getFilters().get(1).get(0))) {
                    if (movie.getMovieRating() > 0) {
                        VideoRating movieRating = new VideoRating(movie.getTitle(),
                                movie.getMovieRating(), 0);
                        movieRatings.add(movieRating);
                    }
                }
            }
        }
        if (command.getSortType().equals("asc")) {
            movieRatings.sort(new Sortratingasc());
        } else {
            movieRatings.sort(new Sortratingdesc());
        }
        if (command.getNumber() < movieRatings.size()) {
            for (int i = 0; i < command.getNumber(); i++) {
                finalresult.add(movieRatings.get(i).getTitle());
            }
        } else {
            for (VideoRating movieRating : movieRatings) {
                finalresult.add(movieRating.getTitle());
            }
        }
        arrayResult.add(file.writeFile(command.getActionId(), "Querry", "Query result: "
                + finalresult.toString()));
    }

}

