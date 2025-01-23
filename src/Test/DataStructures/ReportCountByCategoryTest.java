package DataStructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportCountByCategoryTest {

    private final static String category = "category";
    private final static int count = 5;
    private static ReportCountByCategory reportCount;
    private static ReportCountByCategory reportCountExisting;

    @BeforeEach
    void setUp() {
        reportCount = new ReportCountByCategory(category);
        reportCountExisting = new ReportCountByCategory(category, count);
    }

    @Test
    void getCategory() {
        assertEquals(category, reportCount.getCategory());
    }

    @Test
    void setCategory() {
        String categoryNew = "new category";
        reportCount.setCategory(categoryNew);
        assertEquals(categoryNew, reportCount.getCategory());
    }

    @Test
    void getCount() {
        assertEquals(count, reportCountExisting.getCount());
    }

    @Test
    void setCount() {
        int newCount = 10;
        reportCountExisting.setCount(newCount);
        assertEquals(newCount, reportCountExisting.getCount());
    }

    @Test
    void incrementCount() {
        int incCount = count + 1;
        reportCountExisting.incrementCount();
        assertEquals(incCount, reportCountExisting.getCount());
    }
}