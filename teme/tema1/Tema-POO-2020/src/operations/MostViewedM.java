package operations;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import utils.VideoViews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unchecked")
public class MostViewedM extends VideoViews {

    static class Sortratingasc implements Comparator<VideoViews> {
        @Override
        public int compare(final VideoViews o1, final VideoViews o2) {
            Integer result;
            result = o1.getNrviews() - o2.getNrviews();
            if (result.equals(0)) {
                result = o1.getTitle().compareTo(o2.getTitle());
            }
            return result;
        }
    }

    static class Sortratingdesc implements Comparator<VideoViews> {
        @Override
        public int compare(final VideoViews o1, final VideoViews o2) {
            Integer result;
            result = o2.getNrviews() - o1.getNrviews();
            if (result.equals(0)) {
                result = o2.getTitle().compareTo(o1.getTitle());
            }
            return result;
        }
    }

    /**
     *
     * @param command command
     * @param movies movies
     * @param users users
     * @param arrayResult result
     * @param file file
     * @throws IOException exception
     */
    public void mostViewedM(final ActionInputData command, final List<MovieInputData> movies,
                            final List<UserInputData> users, final JSONArray arrayResult,
                            final Writer file) throws IOException {
        ArrayList<String> finalresult = new ArrayList<>();
        ArrayList<VideoViews> filterv = new ArrayList<>();
        Integer year = 0;
        String years = command.getFilters().get(0).get(0);
        if (years != null) {
            year = Integer.parseInt(years);
        }
        String genre = command.getFilters().get(1).get(0);
        for (MovieInputData video : movies) {
            int nrviews = 0;
            if (year.equals(0) || year.equals(video.getYear())) {
                if (genre == null || video.getGenres().contains(genre)) {
                    for (UserInputData user : users) {
                        if (user.getHistory().containsKey(video.getTitle())) {
                            nrviews = nrviews + user.getHistory().get(video.getTitle());
                        }
                    }
                }
            }
            if (nrviews > 0) {
                VideoViews videoViews = new VideoViews(video.getTitle(), nrviews);
                filterv.add(videoViews);
            }
        }
        if (command.getSortType().equals("asc")) {
            filterv.sort(new Sortratingasc());
        } else {
            filterv.sort(new Sortratingdesc());
        }
        if (command.getNumber() < filterv.size()) {
            for (int i = 0; i < command.getNumber(); i++) {
                finalresult.add(filterv.get(i).getTitle());
            }
        } else {
            for (VideoViews videoViews : filterv) {
                finalresult.add(videoViews.getTitle());
            }
        }
        arrayResult.add(file.writeFile(command.getActionId(), "Querry", "Query result: "
                + finalresult.toString()));

    }
}
