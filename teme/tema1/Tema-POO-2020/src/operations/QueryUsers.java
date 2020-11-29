package operations;

import fileio.ActionInputData;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import utils.UserNrofRatings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QueryUsers extends UserNrofRatings {
    static class Sortratingasc implements Comparator<UserNrofRatings> {
        @Override
        public int compare(final UserNrofRatings o1, final UserNrofRatings o2) {
            Integer result = o1.getNrofRatings() - o2.getNrofRatings();
            if (result.equals(0)) {
                result = o1.getName().compareTo(o2.getName());
            }
            return result;
        }
    }

    static class Sortratingdesc implements Comparator<UserNrofRatings> {
        @Override
        public int compare(final UserNrofRatings o1, final UserNrofRatings o2) {

            Integer result = o2.getNrofRatings() - o1.getNrofRatings();
            if (result.equals(0)) {
                result = o2.getName().compareTo(o1.getName());
            }
            return result;
        }
    }

    /**
     *
     * @param command command from input
     * @param users list of users
     * @param arrayResult result
     * @param file file
     * @throws IOException exception
     */
    public void queryUsers(final ActionInputData command, final List<UserInputData> users,
                           final JSONArray arrayResult, final Writer file) throws IOException {
        ArrayList<String> finalresult = new ArrayList<>();
        ArrayList<UserNrofRatings> usersRatings = new ArrayList<>();
        for (UserInputData user : users) {
            if (user.getRatingnr() > 0) {
                UserNrofRatings userratings = new UserNrofRatings(user.getUsername(),
                        user.getRatingnr());
                usersRatings.add(userratings);
            }
        }
        if (command.getSortType().equals("asc")) {
            usersRatings.sort(new Sortratingasc());
        } else {
            usersRatings.sort(new Sortratingdesc());
        }
        if (command.getNumber() < usersRatings.size()) {
            for (int i = 0; i < command.getNumber(); i++) {
                finalresult.add(usersRatings.get(i).getName());
            }
        } else {
            for (UserNrofRatings usersRating : usersRatings) {
                finalresult.add(usersRating.getName());
            }
        }

        arrayResult.add(file.writeFile(command.getActionId(), "Querry", "Query result: "
                + finalresult.toString()));

    }
}
