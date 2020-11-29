package utils;

public class VideoFavnr {
    private String title;
    private Integer favnr;
    private int pos;

    public VideoFavnr(final String title, final Integer favnr, final int pos) {
        this.title = title;
        this.favnr = favnr;
        this.pos = pos;
    }

    public VideoFavnr() {
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
     * @param title title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     *
     * @return fav nr
     */
    public Integer getFavnr() {
        return favnr;
    }

    /**
     *
     * @param favnr set fav nr
     */
    public void setFavnr(final Integer favnr) {
        this.favnr = favnr;
    }

    /**
     *
     * @return get position in database
     */
    public int getPos() {
        return pos;
    }

    /**
     *
     * @param pos set video position
     */
    public void setPos(final int pos) {
        this.pos = pos;
    }
}
