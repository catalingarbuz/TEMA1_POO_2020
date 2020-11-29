package operations;

import fileio.ActionInputData;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.List;

public class Favorite {

    /**
     *
     * @param userInputData List of user input data
     * @param title Video title to add to favorite
     * @param username username
     * @param arrayResult Here we store result
     * @param file file
     * @param command Command from the input
     * @throws IOException the method may 'throw' an error
     */
    @SuppressWarnings("unchecked")
    public void addFavorite(final List<UserInputData> userInputData, final String title,
                            final String username, final JSONArray arrayResult,
                            final Writer file,
                            final ActionInputData command) throws IOException {

        for (UserInputData userData : userInputData) {
            if (userData.getUsername().equals(username)) {
                if (userData.getFavoriteMovies().contains(title)) {
                    arrayResult.add(file.writeFile(command.getActionId(), "Error", "error -> "
                            + title + " is already in favourite list"));
                } else if (userData.getHistory().containsKey(title)) {
                    userData.getFavoriteMovies().add(userData.getFavoriteMovies().size(), title);
                    arrayResult.add(file.writeFile(command.getActionId(), "Success", "success -> "
                            + title + " was added as favourite"));
                } else {
                    arrayResult.add(file.writeFile(command.getActionId(), "Error", "error -> "
                            + title + " is not seen"));
                }
            }
        }
    }
}
