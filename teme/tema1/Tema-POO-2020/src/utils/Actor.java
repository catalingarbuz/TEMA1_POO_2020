package utils;

public class Actor {
    private String name;
    private Double rating;

    public Actor(final String name, final Double rating) {
        this.name = name;
        this.rating = rating;
    }

    public Actor() {
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
      * @return rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     *
     * @param rating set rating
     */
    public void setRating(final Double rating) {
        this.rating = rating;
    }
}
