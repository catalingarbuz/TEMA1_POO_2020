package operations;

import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

@SuppressWarnings("unchecked")
public class FilterQuery {

    /**
     *
     * @param command command
     * @param actors list of actors
     * @param arrayResult result
     * @param file file
     * @throws IOException exception
     */
    public void filterActors(final ActionInputData command, final List<ActorInputData> actors,
                             final JSONArray arrayResult,
                             final Writer file) throws IOException {
        ArrayList<String> filterActors = new ArrayList<>();
        for (ActorInputData actor : actors) {
            List<String> words = new ArrayList<>(command.getFilters().get(2));
            Integer wordsfound = 0;
            StringTokenizer dwords = new StringTokenizer(actor.getCareerDescription(),
                    " '~_-[]$#%,.?!:/()0123456789\"\\\t\n–;");
            while (dwords.hasMoreTokens()) {
                String w = dwords.nextToken();
                for (int i = 0; i < words.size(); i++) {
                    if (words.get(i).equals(w.toLowerCase())) {
                        words.set(i, "0");
                        wordsfound = wordsfound + 1;
                    }
                }
            }
            if (wordsfound.equals(words.size())) {
                filterActors.add(actor.getName());
            }
        }
        if (filterActors.size() > 0) {
            if (command.getSortType().equals("asc")) {
                Collections.sort(filterActors);
            } else {
                filterActors.sort(Collections.reverseOrder());
            }
        }
        arrayResult.add(file.writeFile(command.getActionId(), "Query", "Query result: "
                + filterActors.toString()));
    }
}
