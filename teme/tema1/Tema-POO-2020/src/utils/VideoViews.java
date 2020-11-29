package utils;

public class VideoViews {
    private String title;
    private Integer nrviews;

    public VideoViews(final String title, final Integer nrviews) {
        this.title = title;
        this.nrviews = nrviews;
    }

    public VideoViews() {
    }

    /**
     *
     * @return video title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title set video title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     *
     * @return number a video was viewed
     */
    public Integer getNrviews() {
        return nrviews;
    }

    /**
     *
     * @param nrviews set nr views
     */
    public void setNrviews(final Integer nrviews) {
        this.nrviews = nrviews;
    }

}

