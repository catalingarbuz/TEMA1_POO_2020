package operations;

import actor.ActorsAwards;
import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import utils.ActorNrofAwards;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@SuppressWarnings("unchecked")
public class AwardsQuery extends ActorNrofAwards {

    static class Sortratingasc implements Comparator<ActorNrofAwards> {
        @Override
        public int compare(final ActorNrofAwards o1, final ActorNrofAwards o2) {
            Integer result = o1.getNrawards() - o2.getNrawards();
            if (result.equals(0)) {
                result = o1.getName().compareTo(o2.getName());
            }
            return result;

        }
    }

    static class Sortratingdesc implements Comparator<ActorNrofAwards> {
        @Override
        public int compare(final ActorNrofAwards o1, final ActorNrofAwards o2) {
            Integer result = o2.getNrawards() - o1.getNrawards();
            if (result.equals(0)) {
                result = o2.getName().compareTo(o1.getName());
            }
            return result;

        }
    }

    /**
     * @param command     Command from the input
     * @param actors      List of actors
     * @param arrayResult result
     * @param file        file
     * @throws IOException IOExeption
     */
    public void actorAwards(final ActionInputData command, final List<ActorInputData> actors,
                            final JSONArray arrayResult, final Writer file) throws IOException {
        final int awardsidx = 3;
        ArrayList<String> rezultatfinal = new ArrayList<>();
        ArrayList<ActorNrofAwards> actorList = new ArrayList<>();
        List<String> awards = command.getFilters().get(awardsidx);
        for (ActorInputData actor : actors) {
            Integer nrawards = 0;
            for (String award : awards) {
                if (actor.getAwards().containsKey(ActorsAwards.valueOf(award))) {
                    nrawards = nrawards + 1;
                }
                if (nrawards.equals(awards.size())) {
                    int nrofawards = getActorNrofAwards(actor);
                    ActorNrofAwards actorNrofAwards = new ActorNrofAwards(actor.getName(),
                            nrofawards);
                    actorList.add(actorNrofAwards);
                }
            }
        }
        if (command.getSortType().equals("asc")) {
            actorList.sort(new Sortratingasc());
        }
        if (command.getSortType().equals("desc")) {
            actorList.sort(new Sortratingdesc());
        }
        for (ActorNrofAwards actorNrofAwards : actorList) {
            rezultatfinal.add(actorNrofAwards.getName());
        }
        arrayResult.add(file.writeFile(command.getActionId(), "Querry", "Query result: "
                + rezultatfinal.toString()));


    }
}
