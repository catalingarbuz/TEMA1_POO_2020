package operations;

import fileio.ActionInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import utils.VideoFavnr;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
@SuppressWarnings("unchecked")
public class FavoriteShowsQuery extends FavoriteMoviesQuery {

    /**
     *
     * @param command c
     * @param users u
     * @param serials s
     * @param arrayResult result
     * @param file file
     * @throws IOException exception
     */
    public void favoriteShowsQuery(final ActionInputData command, final List<UserInputData> users,
                                   final List<SerialInputData> serials,
                                   final JSONArray arrayResult,
                                   final Writer file) throws IOException {
        ArrayList<VideoFavnr> videoratings = new ArrayList<>();
        ArrayList<String> finalresult = new ArrayList<>();
        Integer year = 0;
        String years = command.getFilters().get(0).get(0);
        if (years != null) {
            year = Integer.parseInt(years);
        }
        String genre = command.getFilters().get(1).get(0);
        ArrayList<String> filtershows = new ArrayList<>();
        for (SerialInputData serial : serials) {
            if (year.equals(serial.getYear()) || year.equals(0)) {
                if (genre == null || serial.getGenres().contains(genre)) {
                    filtershows.add(serial.getTitle());
                }
            }
        }
        for (String fshow : filtershows) {
            int favnr = 0;
            for (UserInputData user : users) {
                if (user.getFavoriteMovies().contains(fshow)) {
                    favnr = favnr + 1;
                }
            }
            if (favnr > 0) {
                VideoFavnr videorating = new VideoFavnr(fshow, favnr, 0);
                videoratings.add(videorating);
            }
        }
        if (command.getSortType().equals("asc")) {
            videoratings.sort(new Sortratingasc());
        } else {
            videoratings.sort(new Sortratingdesc());
        }
        if (command.getNumber() < videoratings.size()) {
            for (int i = 0; i < command.getNumber(); i++) {
                finalresult.add(videoratings.get(i).getTitle());
            }
        } else if (command.getNumber() > videoratings.size()) {
            for (VideoFavnr videorating : videoratings) {
                finalresult.add(videorating.getTitle());
            }
        }

        arrayResult.add(file.writeFile(command.getActionId(), "Querry", "Query result: "
                + finalresult.toString()));
    }
}
