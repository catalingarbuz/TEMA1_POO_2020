package operations;

import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.List;

public final class ActorsQuery {
    private ActorsQuery() {
    }

    /**
     * @param command ActionInputData command
     * @param actors List of actors
     * @param movies List of movies
     * @param serials List of serials
     * @param arrayResult result
     * @param file file
     * @throws IOException IOE exception
     */
    public static void querryIterate(final ActionInputData command,
                                     final List<ActorInputData> actors,
                                     final List<MovieInputData> movies,
                                     final List<SerialInputData> serials,
                                     final JSONArray arrayResult,
                                     final Writer file) throws IOException {
        switch (command.getCriteria()) {
            case "average" -> {
                AverageQuerry averageQuerry = new AverageQuerry();
                averageQuerry.actorsAverage(actors, movies, serials, command.getNumber(),
                        arrayResult, file, command);
            }
            case "awards" -> {
                AwardsQuery awardsQuery = new AwardsQuery();
                awardsQuery.actorAwards(command, actors, arrayResult, file);
            }
            case "filter_description" -> {
                FilterQuery filterQuery = new FilterQuery();
                filterQuery.filterActors(command, actors, arrayResult, file);
            }
            default -> throw new IllegalStateException("Unexpected value: "
                    + command.getCriteria());
        }

    }
}
