package fileio;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Information about a movie, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class MovieInputData extends ShowInput {

    /**
     * Duration in minutes of a season
     */
    private final int duration;
    public Hashtable<String, Double> raiting;

    public MovieInputData(final String title, final ArrayList<String> cast,
                          final ArrayList<String> genres, final int year,
                          final int duration) {
        super(title, year, cast, genres);
        this.duration = duration;
        this.raiting = new Hashtable<>();
    }

    public int getDuration() {
        return duration;
    }

    /**
     *
     * @return a Movie Rating
     */
    public double getMovieRating() {
        Enumeration<String> enumeration = raiting.keys();
        double totalrating = 0;
        int ratingnumber = 0;
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            totalrating = totalrating + raiting.get(key);
            ratingnumber = ratingnumber + 1;
        }
        Integer verific = ratingnumber;
        if (verific.equals(0)) {
            return 0d;
        }
        return totalrating / ratingnumber;
    }


    @Override
    public String toString() {
        return "MovieInputData{" + "title= "
                + super.getTitle() + "year= "
                + super.getYear() + "duration= "
                + duration + "cast {"
                + super.getCast() + " }\n"
                + "genres {" + super.getGenres() + " }\n ";
    }
}
