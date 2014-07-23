
import dao.DaoUser;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import util.DaoFactory;
import entities.User;

import static org.junit.Assert.*;

public class DaoUserTest {
    DaoUser daoUser = DaoFactory.getDaoUserInstance();

    @Test
    public void testSelectAll() throws Exception {
        /*
        list of users isn't empty
         */
        assertFalse(daoUser.selectAll().isEmpty());
        /*
        count of users
         */
        assertEquals(6, daoUser.selectAll().size());
    }

    @Test
    public void testSelectById() {
        assertEquals("Kristina", daoUser.selectById(9).getName());
    }

    @Test
    public void testSelectByEmail() {
        assertEquals("mail@mail.ru", daoUser.selectByEmail("mail@mail.ru").getEmail());
    }

    @Test
    public void testDeleteById() throws Exception {
        testInsert();
        daoUser.delete(5);
        assertNull(daoUser.selectById(5));
    }

    @Test
    public void testInsert() throws Exception {
        /*
        create new user
         */
        User testUser = new User();
        testUser.setName("eklierka");
        testUser.setPassword(DigestUtils.md5Hex("password"));
        testUser.setEmail("email@gmail.com");
        testUser.setUserId(5);
        /*
        insert user to the db
         */
        daoUser.insert(testUser);
        /*
        checking insertion
         */
        assertTrue(daoUser.selectAll().contains(testUser));
    }

    @Test
    public void testUserInfo() throws Exception {
        assertEquals(5, daoUser.selectById(5).getUserId());
        assertEquals("email@gmail.com", daoUser.selectById(5).getEmail());
        assertEquals(DigestUtils.md5Hex("password"), daoUser.selectById(5).getPassword());
    }

    @Test
    public void testDelete() throws Exception{
        testInsert();
        daoUser.delete(daoUser.selectById(5));
        assertEquals(null, daoUser.selectById(5));
    }

    @Test
    public void testUpdate() {
        User testUser = daoUser.selectById(17);
        testUser.setName("Eliot");

        daoUser.update(testUser);

        assertEquals("Eliot", daoUser.selectById(17).getName());
    }

    @Test
    public void testSelectByUserGroup() throws Exception {
        //TODO: Group parameter is passed
    }

    @Test
    public void testSelectByUserGroupType() throws Exception {
        //TODO: String parameter is passed
    }

    @Test
    public void testExist() throws Exception {
        //user exist if email is in the db
        assertTrue(daoUser.exist("email@gmail.com"));
    }
}
