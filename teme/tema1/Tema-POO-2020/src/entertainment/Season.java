package entertainment;

import java.util.Enumeration;
import java.util.Hashtable;


/**
 * Information about a season of a tv show
 * <p>
 * DO NOT MODIFY
 */
public final class Season {
    /**
     * Number of current season
     */
    private final int currentSeason;
    /**
     * Duration in minutes of a season
     */
    private int duration;
    /**
     * List of ratings for each season
     */
    public Hashtable<String, Double> ratings;

    public Season(final int currentSeason, final int duration) {
        this.currentSeason = currentSeason;
        this.duration = duration;
        this.ratings = new Hashtable<>();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     *
     * @param nrofusers distinct users who rated a season
     * @return Season rating
     */
    public double getSeasonRating(final int nrofusers) {
        Enumeration<String> enumeration = ratings.keys();
        double totalrating = 0;
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            totalrating = totalrating + ratings.get(key);
        }
        return totalrating / nrofusers;
    }

    @Override
    public String toString() {
        return "Episode{"
                + "currentSeason="
                + currentSeason
                + ", duration="
                + duration
                + '}';
    }
}

