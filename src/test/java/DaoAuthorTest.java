import dao.DaoAuthor;
import entities.Author;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by Eklerka on 7/24/2014.
 */
public class DaoAuthorTest {
    DaoAuthor daoAuthor = new DaoAuthor();

    @Test
    public void testSelectAll() throws Exception {

        assertFalse(daoAuthor.selectAll().isEmpty());
        assertEquals(5, daoAuthor.selectAll().size());
    }

    @Test
    public void testSelectById() {
        assertEquals(1, daoAuthor.selectById(1).getAuthorId());
    }

    @Test
    public void testSelectByFirstName() {

        assertNotNull(daoAuthor.selectByFirstName("Ден"));
        assertEquals("Ден", daoAuthor.selectByFirstName("Ден").get(0).getFirstname());
    }

    @Test
    public void testSelectByLastName() throws Exception {
        assertNotNull(daoAuthor.selectByLastName("Браун"));
        assertEquals("Браун", daoAuthor.selectByLastName("Браун").get(0).getLastname());
    }

    @Test
    public void testUpdate() {

        Author testAuthor = daoAuthor.selectById(6);
        testAuthor.setLastname("Black");

        daoAuthor.update(testAuthor);

        assertEquals("Black", daoAuthor.selectById(6).getLastname());
    }

    @Ignore
    @Test
    public void testInsert() throws Exception {

        Author testAuthor = new Author();
        testAuthor.setAuthorId(6);
        testAuthor.setFirstname("den");
        testAuthor.setLastname("brown");
        testAuthor.setDescription("bla-bla-bla");

        daoAuthor.insert(testAuthor);

        assertTrue(daoAuthor.selectAll().contains(testAuthor));
    }

    @Test
    public void testDelete() throws Exception{

        //testInsert();

        Author testAuthor = new Author();
        testAuthor.setAuthorId(6);
        testAuthor.setFirstname("den");
        testAuthor.setLastname("brown");
        testAuthor.setDescription("bla-bla-bla");

        daoAuthor.insert(testAuthor);

        daoAuthor.delete(testAuthor);
        assertEquals(null, daoAuthor.selectById(6));
    }

    @Test
    public void testDeleteById() throws Exception {
        testInsert();
        daoAuthor.delete(6);
        assertNull(daoAuthor.selectById(6));
    }

    @Test
    public void testAuthorInfo() throws Exception {

        assertEquals(1, daoAuthor.selectById(1).getAuthorId());
        assertEquals("Ден", daoAuthor.selectById(1).getFirstname());
        assertEquals("Браун", daoAuthor.selectById(1).getLastname());
//        assertEquals("Ден Браун — американський письменник, журналіст, музикант.\n" +
//                "Написав кілька відомих творів, серед яких «Код да Вінчі» та «Янголи і демони»,"+
//                        "що розповідають про таємниці в суспільстві, символіці, змовах. Ці книги "+
//                        "перекладені на більш ніж 40 мов та продані у кількості близько 90 мільйонів "+
//                        "екземплярів (на 2009 рік). Головний герой цих книг — Роберт Ленгдон, "+
//                        "головна тема — історія та християнство, і, як результат, до них відносяться "+
//                        "по-різному.", daoAuthor.selectById(1).getDescription());
        //assertEquals("http://www.livestory.com.ua/images/dan_brown_", daoAuthor.selectById(1).getPhoto());
    }
}
