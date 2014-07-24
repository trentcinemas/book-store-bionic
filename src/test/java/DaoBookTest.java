import dao.*;
import entities.Author;
import entities.Book;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Ignore;
import org.junit.Test;
import util.DaoFactory;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Eklerka on 7/24/2014.
 */
public class DaoBookTest {

    DaoBook daoBook = new DaoBook();

    @Test
    public void testSelectAll() throws Exception {
        /*
        list of users isn't empty
         */
        assertFalse(daoBook.selectAll().isEmpty());
        /*
        count of users
         */
        assertEquals(1, daoBook.selectAll().size());
    }

    @Test
    public void testSelectById() {

        assertEquals(2, daoBook.selectById(2).getBookId());
    }

    @Test
    public void testSelectByTitle() {

        assertNotNull(daoBook.selectByTitle("Ангели і Демони"));
        assertEquals(2, daoBook.selectByTitle("Ангели і Демони").get(0).getBookId());
    }

    @Test
    public void testSelectByUser(){

        assertNotNull(daoBook.selectByUser(new DaoUser().selectById(10)));
        assertEquals("Ангели і Демони", daoBook.selectByUser(new DaoUser().selectById(10)).get(0).getTitle());
        assertEquals(1, daoBook.selectByUser(new DaoUser().selectById(10)).size());

        assertNull(daoBook.selectByUser(new DaoUser().selectById(1)));
    }

    @Test
    public void testSelectByUserId() {
        assertNotNull(daoBook.selectByUserId(10));
        assertEquals("Ангели і Демони", daoBook.selectByUserId(10).get(0).getTitle());
        assertEquals(1,  daoBook.selectByUserId(10).size());

        assertNull(daoBook.selectByUserId(1));
    }

    @Test
    public void testSelectByDistributor() {

        assertNotNull(daoBook.selectByDistributor(new DaoDistributor().selectById(1)));
        assertEquals("Ангели і Демони", daoBook.selectByDistributor(new DaoDistributor().selectById(1)).get(0).getTitle());
        assertEquals(1, daoBook.selectByDistributor(new DaoDistributor().selectById(1)).size());

    }

    @Test
    public void testSelectByDistributorId() throws Exception {
        assertNotNull(daoBook.selectByDistributorId(1));
        assertEquals("Ангели і Демони", daoBook.selectByDistributorId(1).get(0).getTitle());
        assertEquals(1,  daoBook.selectByDistributorId(1).size());

    }

    @Ignore
    @Test
    public void testInsert() throws Exception {

        Book testBook = new Book();
        testBook.setBookId(3);
        testBook.setAuthorByAuthorId(new DaoAuthor().selectById(3));
        testBook.setUserByUserId(new DaoUser().selectById(17));
        testBook.setDescription("bla-bla-bla");
        testBook.setTitle("title");
        testBook.setGenreByGenreId(new DaoGenre().selectById(3));
        daoBook.insert(testBook);
        /*
        checking insertion
         */
        assertTrue(daoBook.selectAll().contains(testBook));
    }

    @Test
    public void testUpdate() {

        Book testBook = daoBook.selectById(2);
        testBook.setTitle("Ангели і Демони");

        daoBook.update(testBook);

        assertEquals("Ангели і Демони", daoBook.selectById(2).getTitle());
    }

    @Test
    public void testDelete() throws Exception{
        testInsert();

        Book testBook = new Book();
        testBook.setBookId(3);
        testBook.setAuthorByAuthorId(new DaoAuthor().selectById(3));
        testBook.setUserByUserId(new DaoUser().selectById(17));
        testBook.setDescription("bla-bla-bla");
        testBook.setTitle("title");
        testBook.setGenreByGenreId(new DaoGenre().selectById(3));

        daoBook.delete(testBook);
        assertEquals(null, daoBook.selectById(3));
    }

    @Test
    public void testDeleteById() throws Exception {
        testInsert();
        daoBook.delete(3);
        assertNull(daoBook.selectById(3));
    }

    @Test
    public void testBookInfo() throws Exception {

        assertEquals(2, daoBook.selectById(2).getBookId());
        assertEquals(new DaoAuthor().selectById(1), daoBook.selectById(2).getAuthorByAuthorId());
        assertEquals("", daoBook.selectById(2).getDescription());
        assertEquals("Ангели і Демони", daoBook.selectById(2).getTitle());
        assertEquals(new DaoGenre().selectById(2), daoBook.selectById(2).getGenreByGenreId());
    }
}
