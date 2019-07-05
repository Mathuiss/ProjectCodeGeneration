package ServiceTest;

import io.swagger.model.User;
import io.swagger.services.SessionService;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {
    private User user;
    private SessionService sessionService;

    @Before
    public void setup() {
        user = new User();
    }

    @Test
    public void IsEmployeeEmployee() {
        assertEquals(false, user.getIsEmployee());
    }

    @Test
    public void doesUserExist() {
        // assertEquals("test@gmail.com", user.getEmail());
        assertFalse(sessionService.userExist("patat@gmail.com"));
    }

    @Test
    public void doesEmailFindId() {
        sessionService.getUserIdByEmail("patat@gmail.com");
    }
}
