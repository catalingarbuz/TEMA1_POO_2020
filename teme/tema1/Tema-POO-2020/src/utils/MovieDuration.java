package utils;

public class MovieDuration {
    private String title;
    private Integer duration;

    public MovieDuration(final String title, final Integer duration) {
        this.title = title;
        this.duration = duration;
    }


    public MovieDuration() {
    }

    /**
     *
     * @return movie title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title movie title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     *
     * @return movie duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     *
     * @param duration duration
     */
    public void setDuration(final Integer duration) {
        this.duration = duration;
    }
}

