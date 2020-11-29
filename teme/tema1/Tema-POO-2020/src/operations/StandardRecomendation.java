package operations;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StandardRecomendation {
    /**
     *
     * @param command command
     * @param movies list of movies
     * @param users list of users
     * @param serials list of serials
     * @param arrayResult result
     * @param file file
     * @throws IOException exception
     */
    public void standardRecommendation(final ActionInputData command,
                                      final List<MovieInputData> movies,
                                      final List<UserInputData> users,
                                      final List<SerialInputData> serials,
                                      final JSONArray arrayResult,
                                      final Writer file) throws IOException {
        String username = command.getUsername();
        ArrayList<String> unseenmovies = new ArrayList<>();
        for (UserInputData user : users) {
            if (user.getUsername().equals(username)) {
                for (MovieInputData movie : movies) {
                    if (!user.getHistory().containsKey(movie.getTitle())) {
                        unseenmovies.add(movie.getTitle());
                    }
                }
                if (unseenmovies.size() == 0) {
                    for (SerialInputData serial : serials) {
                        if (!user.getHistory().containsKey(serial.getTitle())) {
                            unseenmovies.add(serial.getTitle());
                        }
                    }
                }
            }
        }
        if (unseenmovies.size() > 0) {
            arrayResult.add(file.writeFile(command.getActionId(), "Recommendation",
                    "StandardRecommendation result: " + unseenmovies.get(0)));
        } else {
            arrayResult.add(file.writeFile(command.getActionId(), "Recommendation",
                    "StandardRecommendation cannot be applied!"));
        }
    }
}
