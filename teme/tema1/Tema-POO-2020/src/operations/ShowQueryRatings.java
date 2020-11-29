package operations;

import fileio.ActionInputData;
import fileio.SerialInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import utils.VideoRating;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShowQueryRatings extends VideoRating {

    static class Sortratingasc implements Comparator<VideoRating> {
        @Override
        public int compare(final VideoRating o1, final VideoRating o2) {
            Integer result = (int) (o1.getRating() - o2.getRating());
            if (result.equals(0)) {
                result = o1.getTitle().compareTo(o2.getTitle());
            }
            return result;
        }
    }

    static class Sortratingdesc implements Comparator<VideoRating> {
        @Override
        public int compare(final VideoRating o1, final VideoRating o2) {
            Integer result = (int) (o2.getRating() - o1.getRating());
            if (result.equals(0)) {
                result = o2.getTitle().compareTo(o1.getTitle());
            }
            return result;
        }
    }

    /**
     *
     * @param command command input
     * @param serials list of serials
     * @param arrayResult result
     * @param file file
     * @throws IOException exception
     */
    public void showQueryRatings(final ActionInputData command, final List<SerialInputData> serials,
                                 final JSONArray arrayResult,
                                 final Writer file) throws IOException {
        ArrayList<String> finalresult = new ArrayList<>();
        ArrayList<VideoRating> serialRatings = new ArrayList<>();
        Integer filteryear = 0;
        if (command.getFilters().get(0).get(0) != null) {
            filteryear = Integer.parseInt(command.getFilters().get(0).get(0));
        }
        for (SerialInputData serial : serials) {
            Integer year = serial.getYear();
            if (year.equals(filteryear) || filteryear.equals(0)) {
                if (serial.getGenres().contains(command.getFilters().get(1).get(0))) {
                    if (serial.getSerialRating() > 0) {
                        VideoRating serialRating = new VideoRating(serial.getTitle(),
                                serial.getSerialRating(), 0);
                        serialRatings.add(serialRating);
                    }
                }
            }
        }

        if (command.getSortType().equals("asc")) {
            serialRatings.sort(new Sortratingasc());
        } else {
            serialRatings.sort(new Sortratingdesc());
        }
        if (command.getNumber() < serialRatings.size()) {
            for (int i = 0; i < command.getNumber(); i++) {
                finalresult.add(serialRatings.get(i).getTitle());
            }
        } else if (command.getNumber() > serialRatings.size()) {
            for (VideoRating serialRating : serialRatings) {
                finalresult.add(serialRating.getTitle());
            }
        }

        arrayResult.add(file.writeFile(command.getActionId(), "Querry", "Query result: "
                + finalresult.toString()));
    }
}
