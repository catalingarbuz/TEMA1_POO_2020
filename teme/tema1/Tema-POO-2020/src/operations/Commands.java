package operations;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.List;

public class Commands  {
    private final Input input;
    private final JSONArray arrayResult;
    private final Writer file;

    public Commands(final Input input, final JSONArray arrayResult, final Writer file) {
        this.input = input;
        this.arrayResult = arrayResult;
        this.file = file;
    }

    /**
     *
     * @throws IOException the method may 'throw' an error
     */
    public void iterateCommands() throws IOException {
        List<UserInputData> userInputData;
        userInputData = input.getUsers();
        List<ActionInputData> commandslist;
        commandslist = input.getCommands();
        for (ActionInputData command : commandslist) {

            if (command.getActionType().equals("command")) {
                switch (command.getType()) {
                    case "favorite" -> {
                        Favorite favorite = new Favorite();
                        favorite.addFavorite(userInputData, command.getTitle(),
                                command.getUsername(), arrayResult, file, command);
                    }
                    case "view" -> {
                        View view = new View();
                        view.addView(userInputData, command.getTitle(), command.getUsername(),
                                arrayResult, file, command);
                    }
                    case "rating" -> {
                        Rating rating = new Rating();
                        rating.addRating(userInputData, command.getTitle(), command.getUsername(),
                                arrayResult, file, command, input);
                    }
                    default -> throw new IllegalStateException("Unexpected value: "
                            + command.getType());
                }
            }
            if (command.getActionType().equals("query")) {
                switch (command.getObjectType()) {
                    case "actors" -> ActorsQuery.querryIterate(command, input.getActors(),
                            input.getMovies(), input.getSerials(), arrayResult, file);
                    case "movies", "shows" -> {
                        VideoQuery videoQuery = new VideoQuery();
                        videoQuery.querryIterate(command, input.getUsers(), input.getMovies(),
                                input.getSerials(),
                                arrayResult, file);
                    }
                    case "users" -> {
                        QueryUsers queryUsers = new QueryUsers();
                        queryUsers.queryUsers(command, userInputData, arrayResult, file);
                    }
                    default -> throw new IllegalStateException("Unexpected value: "
                            + command.getObjectType());
                }
            }
            if (command.getActionType().equals("recommendation")) {
                Recommandations recommandations = new Recommandations();
                recommandations.recommandationsIterate(command, userInputData, input.getMovies(),
                        input.getSerials(), arrayResult, file);
            }
        }

    }

}
