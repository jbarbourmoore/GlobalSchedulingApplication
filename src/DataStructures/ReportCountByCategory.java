package DataStructures;

/**
 * Created class ReportCountByCategory.java
 * <p>
 * This class is the data structure for the report count tables.
 *
 * @author Jamie Barbour-Moore
 */
public class ReportCountByCategory {
    /**
     * The String which holds the category name.
     */
    private String Category;

    /**
     * The int which keeps track of how many of the category have been found.
     */
    private int Count;

    /**
     * This method initializes the ReportCountByCategory, assuming the first instance of the category
     * has just been found.
     *
     * @param category the name of the category being counted
     */
    public ReportCountByCategory(String category) {
        Category = category;
        Count = 1;
    }

    /**
     * This method initializes the ReportCountByCategory, assuming it is already counted.
     *
     * @param category the name of the category being counted
     * @param count    the number of the category found
     */
    public ReportCountByCategory(String category, int count) {
        Category = category;
        Count = count;
    }

    /**
     * This method gets the category.
     *
     * @return a string of the category name
     */
    public String getCategory() {
        return Category;
    }

    /**
     * This method sets the category.
     *
     * @param category a string of the new category name
     */
    public void setCategory(String category) {
        Category = category;
    }

    /**
     * This method gets the count.
     *
     * @return an int containing the count
     */
    public int getCount() {
        return Count;
    }

    /**
     * This method sets the count
     *
     * @param count the int holding the count
     */
    public void setCount(int count) {
        this.Count = count;
    }

    /**
     * This method increments the count by one.
     */
    public void incrementCount() {
        Count++;
    }
}
