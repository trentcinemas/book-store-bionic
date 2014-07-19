import dao.DaoUser;
import org.junit.Test;
import util.DaoFactory;

import static org.junit.Assert.*;

/**
 * Created by Evgeniy Baranuk on 19.07.14.
 */
public class DaoUserTest {
    DaoUser daoUser = DaoFactory.getDaoUserInstance();

    @Test
    public void testDaoFactory() throws Exception {
        assertEquals(null, daoUser.selectById(-1));
    }
}
