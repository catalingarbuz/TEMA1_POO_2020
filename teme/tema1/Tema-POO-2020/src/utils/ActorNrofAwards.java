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

    /**
     *
     * @return actor name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name set name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return nr of awards
     */
    public Integer getNrawards() {
        return nrawards;
    }

    /**
     *
     * @param nrawards set nr awards
     */
    public void setNrawards(final Integer nrawards) {
        this.nrawards = nrawards;
    }

    /**
     *
     * @param actor actor
     * @return actor nr of awards
     */
    public int getActorNrofAwards(final ActorInputData actor) {
        int nrofawards = 0;
        Set<ActorsAwards> awards = actor.getAwards().keySet();
        for (ActorsAwards award : awards) {
            nrofawards = nrofawards + actor.getAwards().get(award);
        }
        return nrofawards;
    }
}

