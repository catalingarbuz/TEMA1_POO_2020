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
public class BestUnseen extends VideoRating {
    static final int MAGICTOINT = 10;

    static class Sortratingdesc implements Comparator<VideoRating> {
        @Override
        public int compare(final VideoRating o1, final VideoRating o2) {
            Integer result = (int) ((o2.getRating() - o1.getRating()) * MAGICTOINT);
            if (result.equals(0)) {
                result = o2.getPosition() - o1.getPosition();
            }
            return result;
        }
    }

    /**
     * @param command     Command from the imput
     * @param movies      movies
     * @param users       users
     * @param serials     serials
     * @param arrayResult in this param we store the result
     * @param file        file
     * @throws IOException IOException
     */
    public void bestUnseen(final ActionInputData command, final List<MovieInputData> movies,
                           final List<UserInputData> users, final List<SerialInputData> serials,
                           final JSONArray arrayResult, final Writer file) throws IOException {
        ArrayList<VideoRating> videoRatings = new ArrayList<>();
        String username = command.getUsername();
        UserInputData user = null;
        int pos = 1;
        for (UserInputData userInputData : users) {
            if (userInputData.getUsername().equals(username)) {
                user = userInputData;
            }
        }
        if (user != null) {
            for (MovieInputData movie : movies) {
                if (!(user.getHistory().containsKey(movie.getTitle()))
                        && (movie.getMovieRating() > 0)) {
                    VideoRating vr = new VideoRating(movie.getTitle(),
                            movie.getMovieRating(), pos);
                    videoRatings.add(vr);
                    pos = pos + 1;
                }
            }
            for (SerialInputData serial : serials) {
                if (!(user.getHistory().containsKey(serial.getTitle()))
                        && (serial.getSerialRating() > 0)) {
                    VideoRating vr = new VideoRating(serial.getTitle(),
                            serial.getSerialRating(), pos);
                    videoRatings.add(vr);
                    pos = pos + 1;
                }
            }
        }

        Integer size = videoRatings.size();
        if (size.equals(0)) {
            ArrayList<String> unseen = new ArrayList<>();
            for (MovieInputData movie : movies) {
                assert user != null;
                if (!user.getHistory().containsKey(movie.getTitle())) {
                    unseen.add(movie.getTitle());
                }
            }
            if (unseen.size() == 0) {
                for (SerialInputData serial : serials) {
                    assert user != null;
                    if (!user.getHistory().containsKey(serial.getTitle())) {
                        unseen.add(serial.getTitle());
                    }
                }
            }
            if (unseen.size() > 0) {
                arrayResult.add(file.writeFile(command.getActionId(), "Recommendation",
                        "BestRatedUnseenRecommendation result: " + unseen.get(0)));
            } else {
                arrayResult.add(file.writeFile(command.getActionId(), "Recommendation",
                        "BestRatedUnseenRecommendation cannot be applied!"));
            }
        } else if (size > 0) {
            videoRatings.sort(new Sortratingdesc());
            arrayResult.add(file.writeFile(command.getActionId(), "Recommendation",
                    "BestRatedUnseenRecommendation result: " + videoRatings.get(0).getTitle()));
        }

    }
}
