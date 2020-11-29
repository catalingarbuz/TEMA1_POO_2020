package operations;

import fileio.ActionInputData;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.List;

public class View {
    /**
     *
     * @param userInputData list of users
     * @param title video title
     * @param username username
     * @param arrayResult result
     * @param file file
     * @param command input command
     * @throws IOException exception
     */
    public void addView(final List<UserInputData> userInputData, final String title,
                        final String username, final JSONArray arrayResult,
                        final Writer file, final ActionInputData command) throws IOException {
        for (UserInputData userData : userInputData) {
            if (userData.getUsername().equals(username)) {
                if (userData.getHistory().containsKey(title)) {
                    Integer views = userData.getHistory().get(title);
                    views = views + 1;
                    userData.getHistory().put(title, views);
                    arrayResult.add(file.writeFile(command.getActionId(), "Success",
                            "success -> " + title + " was viewed with total views of " + views));
                } else {
                    userData.getHistory().put(title, 1);
                    arrayResult.add(file.writeFile(command.getActionId(), "Success", "success -> "
                            + title + " was viewed with total views of 1"));
                }
            }
        }
    }
}
