package operations;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.List;

public class Recommandations {
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
    public void recommandationsIterate(final ActionInputData command,
                                       final List<UserInputData> users,
                                       final List<MovieInputData> movies,
                                       final List<SerialInputData> serials,
                                       final JSONArray arrayResult,
                                       final Writer file) throws IOException {
        if (command.getType().equals("standard")) {
            StandardRecomendation standardRecomendation = new StandardRecomendation();
            standardRecomendation.standardRecommendation(command, movies, users, serials,
                    arrayResult, file);
        }
        if (command.getType().equals("best_unseen")) {
            BestUnseen bestUnseen = new BestUnseen();
            bestUnseen.bestUnseen(command, movies, users, serials, arrayResult, file);
        }
        if (command.getType().equals("search")) {
            SearchRecommendation searchRecommendation = new SearchRecommendation();
            searchRecommendation.searchRecommendation(command, users, movies, serials,
                    arrayResult, file);
        }
        if (command.getType().equals("favorite")) {
            FavoriteRecom favoriteRecom = new FavoriteRecom();
            favoriteRecom.favoriteRecom(command, movies, serials, users, arrayResult, file);
        }
        if (command.getType().equals("popular")) {
            PopularRecom popularRecom = new PopularRecom();
            popularRecom.popularRecom(command, users, movies, serials, arrayResult, file);
        }

    }
}
