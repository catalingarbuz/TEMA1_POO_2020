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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}