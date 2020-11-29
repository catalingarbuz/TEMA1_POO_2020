package utils;

public class VideoRating {
        private String title;
        private Double rating;
        private Integer position;

        public VideoRating(final String title, final Double rating, final Integer position) {
            this.title = title;
            this.rating = rating;
            this.position = position;
        }

    public VideoRating() {
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
     * @return video rating
     */
    public Double getRating() {
            return rating;
        }

    /**
     *
     * @param rating video rating
     */
    public void setRating(final Double rating) {
            this.rating = rating;
        }

    /**
     *
     * @return position in database
     */
    public Integer getPosition() {
            return position;
        }

    /**
     *
     * @param position position in database
     */
    public void setPosition(final Integer position) {
            this.position = position;
        }
    }

