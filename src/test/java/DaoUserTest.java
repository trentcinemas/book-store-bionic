import

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
    public void testDaoFactory() throws Exception {
        /*
        list of users isn't empty
         */
        assertTrue(daoUser.selectAll().isEmpty());
        /*
        count of users
         */
        assertEquals(0, daoUser.selectAll().size());
    }

    @Test
    public void testUserInsertion() throws Exception {
        /*
        create new user
         */

        User testUser = new User();
        testUser.setName("eklierka");
        testUser.setPassword(DigestUtils.md5Hex("password"));
        testUser.setEmail("");
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

    @Ignore
    @Test
    public void testUserInfo() throws Exception {
        assertEquals(5, daoUser.selectById(5).getUserId());
        assertEquals("", daoUser.selectById(5).getEmail());
    }
}
