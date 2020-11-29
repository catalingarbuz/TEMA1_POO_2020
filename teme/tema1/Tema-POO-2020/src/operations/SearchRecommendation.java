package operations;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import utils.VideoRating;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@SuppressWarnings("unchecked")
public class SearchRecommendation extends VideoRating {

    static class Sortratingasc implements Comparator<VideoRating> {
        @Override
        public int compare(final VideoRating o1, final VideoRating o2) {
            Integer result;
            result = (int) (o1.getRating() - o2.getRating());
            if (result.equals(0)) {
                result = o1.getTitle().compareTo(o2.getTitle());
            }
            return result;
        }
    }

    /**
     *
     * @param command command
     * @param users list of users
     * @param movies list of movies
     * @param serials list of serials
     * @param arrayResult result
     * @param file file
     * @throws IOException exception
     */
    public void searchRecommendation(final ActionInputData command,
                                     final List<UserInputData> users,
                                     final List<MovieInputData> movies,
                                     final List<SerialInputData> serials,
                                     final JSONArray arrayResult,
                                     final Writer file) throws IOException {
        UserInputData user = null;
        for (UserInputData u : users) {
            if (command.getUsername().equals(u.getUsername())) {
                user = u;
            }
        }
        if (user == null || user.getSubscriptionType().equals("BASIC")) {
            arrayResult.add(file.writeFile(command.getActionId(), "Recommendation",
                    "SearchRecommendation cannot be applied!"));
        } else {
            ArrayList<VideoRating> videoRatings = new ArrayList<>();
            for (MovieInputData movie : movies) {
                if (movie.getGenres().contains(command.getGenre())
                        && !user.getHistory().containsKey(movie.getTitle())) {
                    VideoRating vr = new VideoRating(movie.getTitle(), movie.getMovieRating(), 0);
                    videoRatings.add(vr);
                }
            }
            for (SerialInputData serial : serials) {
                if (serial.getGenres().contains(command.getGenre())
                        && !user.getHistory().containsKey(serial.getTitle())) {
                    VideoRating vr = new VideoRating(serial.getTitle(), serial.getSerialRating(),
                            0);
                    videoRatings.add(vr);
                }
            }
            Integer size = videoRatings.size();
            if (size.equals(0)) {
                arrayResult.add(file.writeFile(command.getActionId(), "Recommendation",
                        "SearchRecommendation cannot be applied!"));
            } else {
                videoRatings.sort(new Sortratingasc());
                ArrayList<String> videos = new ArrayList<>();
                for (VideoRating video : videoRatings) {
                    videos.add(video.getTitle());
                }
                arrayResult.add(file.writeFile(command.getActionId(), "Recommendation",
                        "SearchRecommendation result: " + videos.toString()));
            }
        }

    }
}
