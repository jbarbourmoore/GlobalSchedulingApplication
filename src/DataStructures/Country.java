package DataStructures;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created class Country.java
 * <p>
 * This class is the data structure for the country information from the database.
 *
 * @author Jamie Barbour-Moore
 */
public class Country extends DatabaseObjectClass {


    /**
     * A String containing the Country's name.
     */
    private final String Country;

    /**
     * This method initializes a Country.
     *
     * @param country_ID The Country's ID
     * @param country    The Country's name
     */
    public Country(int country_ID, String country) {
        super(country_ID);
        Country = country;
    }

    /**
     * This method initializes a Country.
     *
     * @param country_ID      The Country's ID
     * @param country         The Country's name
     * @param create_Date     the date and time when the Country was created
     * @param created_By      the person who created the Country
     * @param last_Update     the date and time when the Country was last updated
     * @param last_Updated_By the person who last updated the Country
     */
    public Country(int country_ID, String country, LocalDateTime create_Date, String created_By, Timestamp last_Update, String last_Updated_By) {
        super(country_ID, create_Date, created_By, last_Update, last_Updated_By);
        Country = country;
    }

    /**
     * This method gets the ID for the Country.
     *
     * @return an int holding the country's ID
     */
    public int getCountry_ID() {
        return ID;
    }


    /**
     * This method gets the name for the Country.
     *
     * @return the name of the Country
     */
    public String getCountry() {
        return Country;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        if (!super.equals(o)) return false;
        Country country = (Country) o;
        return Country.equals(country.Country);
    }
}
