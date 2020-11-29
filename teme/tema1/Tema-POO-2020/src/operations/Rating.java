package operations;

import fileio.ActionInputData;
import fileio.Input;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("unchecked")
public class Rating {
    /**
     *
     * @param userInputData list of users
     * @param title video title
     * @param username username
     * @param arrayResult result
     * @param file file
     * @param command command from input
     * @param input input
     * @throws IOException exception
     */
    public void addRating(final List<UserInputData> userInputData, final String title,
                          final String username, final JSONArray arrayResult, final Writer file,
                          final ActionInputData command, final Input input) throws IOException {
        for (UserInputData userData : userInputData) {
            if (userData.getUsername().equals(username)) {
                if (userData.getHistory().containsKey(title)) {
                    if (command.getSeasonNumber() == 0) {
                        for (MovieInputData movie : input.getMovies()) {
                            if (title.equals(movie.getTitle())
                                    && !movie.raiting.containsKey(username)) {
                                movie.raiting.put(userData.getUsername(), command.getGrade());
                                userData.setRatingnr(userData.getRatingnr() + 1);
                                arrayResult.add(file.writeFile(command.getActionId(), "Success",
                                        "success -> " + title + " was rated with "
                                                + command.getGrade() + " by " + username));
                            } else if (title.equals(movie.getTitle())
                                    && movie.raiting.containsKey(username)) {
                                arrayResult.add(file.writeFile(command.getActionId(), "Error",
                                        "error -> " + movie.getTitle()
                                                + " has been already rated"));
                            }
                        }
                    }
                    if (command.getSeasonNumber() > 0) {
                        for (SerialInputData serial : input.getSerials()) {
                            if (title.equals(serial.getTitle())
                                    && !serial.getSeasons().get(command.getSeasonNumber() - 1)
                                            .ratings.containsKey(username)) {
                                serial.getSeasons().get(command.getSeasonNumber() - 1)
                                        .ratings.put(username, command.getGrade());
                                userData.setRatingnr(userData.getRatingnr() + 1);
                                arrayResult.add(file.writeFile(command.getActionId(), "Success",
                                        "success -> " + title + " was rated with "
                                                + command.getGrade() + " by " + username));
                                serial.nrofusers.put(username, 1d);
                            } else if (title.equals(serial.getTitle())
                                    && serial.getSeasons().get(command.getSeasonNumber() - 1)
                                    .ratings.containsKey(username)) {
                                arrayResult.add(file.writeFile(command.getActionId(), "Error",
                                        "error -> " + serial.getTitle()
                                                + " has been already rated"));
                            }
                        }
                    }
                } else {
                    arrayResult.add(file.writeFile(command.getActionId(), "Error", "error -> "
                            + title + " is not seen"));
                }
            }
        }
    }
}
