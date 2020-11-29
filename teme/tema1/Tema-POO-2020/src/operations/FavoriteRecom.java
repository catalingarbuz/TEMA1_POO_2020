package operations;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import utils.VideoFavnr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unchecked")
public class FavoriteRecom extends VideoFavnr {

    static class Sortratingasc implements Comparator<VideoFavnr> {
        @Override
        public int compare(final VideoFavnr o1, final VideoFavnr o2) {
            Integer result = o1.getFavnr() - o2.getFavnr();
            if (result.equals(0)) {
                result = o2.getPos() - o1.getPos();
            }
            return result;
        }
    }

    /**
     *
     * @param command command
     * @param movies movies
     * @param serials serials
     * @param users users
     * @param arrayResult result
     * @param file file
     * @throws IOException exception
     */
    public void favoriteRecom(final ActionInputData command, final List<MovieInputData> movies,
                              final List<SerialInputData> serials,
                              final List<UserInputData> users,
                              final JSONArray arrayResult,
                              final Writer file) throws IOException {
        ArrayList<VideoFavnr> videoFavnrs = new ArrayList<>();
        UserInputData u = null;
        int pos = 1;
        for (UserInputData user : users) {
            if (user.getUsername().equals(command.getUsername())) {
                u = user;
            }
        }
        if (u == null || u.getSubscriptionType().equals("BASIC")) {
            arrayResult.add(file.writeFile(command.getActionId(), "Recommendation",
                    "FavoriteRecommendation cannot be applied!"));
        } else {
            for (MovieInputData movie : movies) {
                int favnr = 0;
                for (UserInputData user : users) {
                    if (user.getFavoriteMovies().contains(movie.getTitle())) {
                        favnr = favnr + 1;
                    }
                }
                if (favnr > 0) {
                    VideoFavnr videoFavnr = new VideoFavnr(movie.getTitle(), favnr, pos);
                    videoFavnrs.add(videoFavnr);
                    pos = pos + 1;
                }
            }
            for (SerialInputData serial : serials) {
                int favnr = 0;
                for (UserInputData user : users) {
                    if (user.getFavoriteMovies().contains(serial.getTitle())) {
                        favnr = favnr + 1;
                    }
                }
                if (favnr > 0) {
                    VideoFavnr videoFavnr = new VideoFavnr(serial.getTitle(), favnr, pos);
                    videoFavnrs.add(videoFavnr);
                    pos = pos + 1;
                }
            }
            Integer size = videoFavnrs.size();
            if (size.equals(0)) {
                arrayResult.add(file.writeFile(command.getActionId(), "Recommendation",
                        "FavoriteRecommendation cannot be applied!"));
            } else {
                videoFavnrs.sort(new Sortratingasc());
                String video = null;
                for (VideoFavnr v : videoFavnrs) {
                    if (!(u.getHistory().containsKey(v.getTitle()))) {
                        video = v.getTitle();
                    }
                }
                if (video != null) {
                    arrayResult.add(file.writeFile(command.getActionId(), "Recommendation",
                            "FavoriteRecommendation result: " + video));
                } else {
                    arrayResult.add(file.writeFile(command.getActionId(), "Recommendation",
                            "FavoriteRecommendation cannot be applied!"));
                }
            }
        }
    }

}
