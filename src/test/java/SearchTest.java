import dao.DaoBook;
import entities.Book;
import jsonClasses.BookJson;
import org.junit.Test;
import rest.Search;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static rest.Search.*;

/**
 * Created by Eklerka on 7/28/2014.
 */
public class SearchTest {

    @Test
    public void testSearchTest() throws Exception {
        Search search = new Search();
        List<BookJson> books = search.search("Ден Браун");
        assertEquals(2, books.size());
    }
}
