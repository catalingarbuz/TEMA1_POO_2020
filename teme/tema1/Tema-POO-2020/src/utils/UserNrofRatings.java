package utils;

public class UserNrofRatings {

       private String name;
       private Integer nrofRatings;

        public UserNrofRatings(final String name, final Integer nrofRatings) {
            this.name = name;
            this.nrofRatings = nrofRatings;
        }

    public UserNrofRatings() {
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return nr of ratings
     */
    public Integer getNrofRatings() {
        return nrofRatings;
    }

    /**
     *
     * @param nrofRatings nr of ratings
     */
    public void setNrofRatings(final Integer nrofRatings) {
        this.nrofRatings = nrofRatings;
    }
}
