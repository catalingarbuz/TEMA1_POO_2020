package fileio;

import entertainment.Season;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Information about a tv show, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class SerialInputData extends ShowInput {
    /**
     * Number of seasons
     */
    private final int numberOfSeasons;
    /**
     * Season list
     */
    public Hashtable<String, Double> nrofusers;
    private final ArrayList<Season> seasons;

    public SerialInputData(final String title, final ArrayList<String> cast,
                           final ArrayList<String> genres,
                           final int numberOfSeasons, final ArrayList<Season> seasons,
                           final int year) {
        super(title, year, cast, genres);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
        this.nrofusers = new Hashtable<>();
    }

    public int getNumberSeason() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    /**
     *
     * @return Serial Rating
     */
    public double getSerialRating() {
        ArrayList<Season> seasons = this.getSeasons();
        double serialrating = 0;
        for (Season season : seasons) {
             // Iterating through Serial seasons
            serialrating = serialrating + season.getSeasonRating(this.getNrOfUsers());
        }
        return serialrating / this.getNumberSeason();
    }

    /**
     *
     * @return Number of distinct users who rated a Serial
     */
    public int getNrOfUsers() {
        Enumeration<String> enumeration = nrofusers.keys();
        int userssnr = 0;
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            userssnr = userssnr + 1;
        }
        return userssnr;
    }

    /**
     *
     * @return Total duration of a serial
     */
    public int getDuration() {
        int duration = 0;
        for (Season season : this.getSeasons()) {
            duration = duration + season.getDuration();
        }
        return duration;
    }

    @Override
    public String toString() {
        return "SerialInputData{" + " title= "
                + super.getTitle() + " " + " year= "
                + super.getYear() + " cast {"
                + super.getCast() + " }\n" + " genres {"
                + super.getGenres() + " }\n "
                + " numberSeason= " + numberOfSeasons
                + ", seasons=" + seasons + "\n\n" + '}';
    }
}
