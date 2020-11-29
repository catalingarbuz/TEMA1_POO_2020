package operations;

import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import utils.Actor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unchecked")
public class AverageQuerry extends Actor {
     static final int DOUBLE_TO_INT = 10;
    /**
     * @param actors List of actors
     * @param movies List of movies
     * @param serials List of serials
     * @param number number from querry
     * @param arrayResult result
     * @param file file
     * @param command Input command
     * @throws IOException IOException
     */
    public void actorsAverage(final List<ActorInputData> actors, final List<MovieInputData> movies,
                              final List<SerialInputData> serials, final Integer number,
                              final JSONArray arrayResult, final Writer file,
                              final ActionInputData command) throws IOException {
        ArrayList<Actor> arating = new ArrayList<>();
        ArrayList<String> rezultatfinal = new ArrayList<>();
        double actorrating;
        int validrating;
        for (ActorInputData actor : actors) {
            actorrating = 0;
            validrating = 0;
            for (MovieInputData movie : movies) {
                for (String title : actor.getFilmography()) {
                    if (movie.getTitle().equals(title)) {
                        actorrating = actorrating + movie.getMovieRating();
                        if (movie.getMovieRating() > 0) {
                            validrating = validrating + 1;
                        }
                    }
                }
            }
            for (SerialInputData serial : serials) {
                for (String title : actor.getFilmography()) {
                    if (serial.getTitle().equals(title)) {
                        actorrating = actorrating + serial.getSerialRating();
                        if (serial.getSerialRating() > 0) {
                            validrating = validrating + 1;
                        }
                    }
                }

            }
            if ((actorrating / validrating) > 0) {
                Actor act = new Actor(actor.getName(), actorrating / validrating);
                arating.add(act);
            }

        }
        if (command.getSortType().equals("asc")) {
            arating.sort(new Sortratingasc());
        } else {
            arating.sort(new Sortratingdesc());
        }
        if (number < arating.size()) {
            for (int i = 0; i < number; i++) {
                rezultatfinal.add(arating.get(i).getName());
            }
        } else if (number > arating.size()) {
            for (Actor actor : arating) {
                rezultatfinal.add(actor.getName());
            }
        }

        arrayResult.add(file.writeFile(command.getActionId(), "Querry", "Query result: "
                + rezultatfinal.toString()));

    }

    static class Sortratingasc implements Comparator<Actor> {
        @Override
        public int compare(final Actor o1, final Actor o2) {
            Integer result = (int) ((o1.getRating() - o2.getRating()) * DOUBLE_TO_INT);
            if (result.equals(0)) {
                result = o1.getName().compareTo(o2.getName());
            }
            return result;

        }
    }

    static class Sortratingdesc implements Comparator<Actor> {

        @Override
        public int compare(final Actor o1, final Actor o2) {
            Integer result = (int) ((o2.getRating()  - o1.getRating()) * DOUBLE_TO_INT);
            if (result.equals(0)) {
                result = o2.getName().compareTo(o1.getName());
            }
            return result;

        }
    }
}
