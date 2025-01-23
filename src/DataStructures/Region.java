package DataStructures;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created class Region.java
 * <p>
 * This class is the data structure for the region information from the database.
 *
 * @author Jamie Barbour-Moore
 */
public class Region extends DatabaseObjectClass {
    /**
     * A String containing the Region's name.
     */
    private final String Region_Name;
    /**
     * An int containing the Region's country ID.
     */
    private final int Country_ID;

    /**
     * This method initializes the Region.
     *
     * @param region_ID  the ID for the Region
     * @param regionName the name for the Region
     * @param country_ID the country ID for the Region
     */
    public Region(int region_ID, String regionName, int country_ID) {
        super(region_ID);
        Region_Name = regionName;
        Country_ID = country_ID;
    }

    /**
     * This method initializes the Region.
     *
     * @param region_ID       the ID for the Region
     * @param region_Name     the name for the Region
     * @param create_Date     the date and time when the Region was created
     * @param created_By      the person who created the Region
     * @param last_Update     the date and time when the Region was last updated
     * @param last_Updated_By the person who last updated the Region
     * @param country_ID      the country ID for the Region
     */
    public Region(int region_ID, String region_Name, LocalDateTime create_Date, String created_By, Timestamp last_Update, String last_Updated_By, int country_ID) {
        super(region_ID, create_Date, created_By, last_Update, last_Updated_By);
        Region_Name = region_Name;
        Country_ID = country_ID;
    }

    /**
     * This method gets the ID for the Region.
     *
     * @return the Region's ID
     */
    public int getRegion_ID() {
        return ID;
    }

    /**
     * This method gets the Region's name.
     *
     * @return the Region's name
     */
    public String getRegion_Name() {
        return Region_Name;
    }


    /**
     * This method gets the Region's country ID.
     *
     * @return the Region's country ID
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Region)) return false;
        if (!super.equals(o)) return false;
        Region region = (Region) o;
        return Country_ID == region.Country_ID && Region_Name.equals(region.Region_Name);
    }

}
