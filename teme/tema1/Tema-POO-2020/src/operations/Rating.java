package operations;

import fileio.ActionInputData;
import fileio.Input;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.Hashtable;
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
        Hashtable<String, Double> hashseason;
        Hashtable<String, Double> hashusers;
        for (UserInputData userData : userInputData) {
            if (userData.getUsername().equals(username)) {
                if (userData.getHistory().containsKey(title)) {
                    if (command.getSeasonNumber() == 0) {
                        for (MovieInputData movie : input.getMovies()) {
                            if (title.equals(movie.getTitle())
                                    && !movie.getRaiting().containsKey(username)) {
                                hashusers = new Hashtable<>();
                                hashusers.put(userData.getUsername(), command.getGrade());
                                movie.setRaiting(hashusers);
                                userData.setRatingnr(userData.getRatingnr() + 1);
                                arrayResult.add(file.writeFile(command.getActionId(), "Success",
                                        "success -> " + title + " was rated with "
                                                + command.getGrade() + " by " + username));
                            } else if (title.equals(movie.getTitle())
                                    && movie.getRaiting().containsKey(username)) {
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
                                            .getRatings().containsKey(username)) {
                                hashseason = new Hashtable<>();
                                hashusers = new Hashtable<>();
                                hashseason.put(username, command.getGrade());
                                serial.getSeasons().get(command.getSeasonNumber() - 1)
                                        .setRatings(hashseason);
                                userData.setRatingnr(userData.getRatingnr() + 1);
                                arrayResult.add(file.writeFile(command.getActionId(), "Success",
                                        "success -> " + title + " was rated with "
                                                + command.getGrade() + " by " + username));
                                hashusers.put(username, 1d);
                                serial.setNrofusers(hashusers);
                            } else if (title.equals(serial.getTitle())
                                    && serial.getSeasons().get(command.getSeasonNumber() - 1)
                                    .getRatings().containsKey(username)) {
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
