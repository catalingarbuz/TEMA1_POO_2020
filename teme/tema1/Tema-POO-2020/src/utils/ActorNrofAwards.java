package utils;

import actor.ActorsAwards;
import fileio.ActorInputData;

import java.util.Set;

public class ActorNrofAwards {
    private String name;
    private Integer nrawards;

    public ActorNrofAwards(final String name, final Integer nrawards) {
        this.name = name;
        this.nrawards = nrawards;
    }

    public ActorNrofAwards() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNrawards() {
        return nrawards;
    }

    public void setNrawards(Integer nrawards) {
        this.nrawards = nrawards;
    }

    public int getActorNrofAwards(ActorInputData actor) {
        int nrofawards = 0;
        Set<ActorsAwards> awards = actor.getAwards().keySet();
        for (ActorsAwards award : awards) {
            nrofawards = nrofawards + actor.getAwards().get(award);
        }
        return nrofawards;
    }
}

