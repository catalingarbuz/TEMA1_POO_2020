package operations;

import fileio.ActionInputData;
import fileio.SerialInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import utils.MovieDuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LongestShows extends LongestMovies {
    /**
     *
     * @param command command
     * @param serials serials
     * @param arrayResult result
     * @param file file
     * @throws IOException exception
     */
    public void longestShows(final ActionInputData command, final List<SerialInputData> serials,
                             final JSONArray arrayResult, final Writer file) throws IOException {
        ArrayList<String> finalresult = new ArrayList<>();
        ArrayList<MovieDuration> serialDurations = new ArrayList<>();
        Integer year = 0;
        String years = command.getFilters().get(0).get(0);
        if (years != null) {
            year = Integer.parseInt(years);
        }
        String genre = command.getFilters().get(1).get(0);
        for (SerialInputData serial : serials) {
            if (year.equals(0) || year.equals(serial.getYear())) {
                if (genre == null || serial.getGenres().contains(genre)) {
                    MovieDuration serialDuration = new MovieDuration(serial.getTitle(),
                            serial.getDuration());
                    serialDurations.add(serialDuration);
                }
            }
        }
        if (command.getSortType().equals("asc")) {
            serialDurations.sort(new Sortratingasc());
        } else {
            serialDurations.sort(new Sortratingdesc());
        }
        if (command.getNumber() < serialDurations.size()) {
            for (int i = 0; i < command.getNumber(); i++) {
                finalresult.add(serialDurations.get(i).getTitle());
            }
        } else if (command.getNumber() > serialDurations.size()) {
            for (MovieDuration serialDuration : serialDurations) {

                finalresult.add(serialDuration.getTitle());
            }
        }

        arrayResult.add(file.writeFile(command.getActionId(), "Querry", "Query result: "
                + finalresult.toString()));

    }
}
