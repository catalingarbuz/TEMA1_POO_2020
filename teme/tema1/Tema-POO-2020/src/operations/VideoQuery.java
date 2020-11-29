package operations;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.List;

public class VideoQuery {
    /**
     *
     * @param command input command
     * @param users list of users
     * @param movies list of movies
     * @param serials list of serials
     * @param arrayResult result
     * @param file file
     * @throws IOException excpetion
     */
    public void querryIterate(final ActionInputData command, final List<UserInputData> users,
                              final List<MovieInputData> movies,
                              final List<SerialInputData> serials,
                              final JSONArray arrayResult,
                              final Writer file) throws IOException {
        if (command.getCriteria().equals("ratings")) {
            if (command.getObjectType().equals("movies")) {
                MovieQueryRatings movieQueryRatings = new MovieQueryRatings();
                movieQueryRatings.movieQueryRatings(command, movies, arrayResult, file);
            }
            if (command.getObjectType().equals("shows")) {
                ShowQueryRatings showQueryRatings = new ShowQueryRatings();
                showQueryRatings.showQueryRatings(command, serials, arrayResult, file);
            }
        }
        if (command.getCriteria().equals("favorite")) {
            if (command.getObjectType().equals("movies")) {
                FavoriteMoviesQuery favoriteMoviesQuery = new FavoriteMoviesQuery();
                favoriteMoviesQuery.favoriteMoviesQuery(command, users, movies, arrayResult,
                        file);
            }
            if (command.getObjectType().equals("shows")) {
                FavoriteShowsQuery favoriteShowsQuery = new FavoriteShowsQuery();
                favoriteShowsQuery.favoriteShowsQuery(command, users, serials, arrayResult, file);
            }
        }
        if (command.getCriteria().equals("longest")) {
            if (command.getObjectType().equals("movies")) {
                LongestMovies longestMovies = new LongestMovies();
                longestMovies.longestMovies(command, movies, arrayResult, file);
            }
            if (command.getObjectType().equals("shows")) {
                LongestShows longestShows = new LongestShows();
                longestShows.longestShows(command, serials, arrayResult, file);
            }
        }
        if (command.getCriteria().equals("most_viewed")) {
            if (command.getObjectType().equals("movies")) {
                MostViewedM mostViewedM = new MostViewedM();
                mostViewedM.mostViewedM(command, movies, users, arrayResult, file);
            }
            if (command.getObjectType().equals("shows")) {
                MostViewedS mostViewedS = new MostViewedS();
                mostViewedS.mostViewedS(command, serials, users, arrayResult, file);
            }
        }
    }
}
