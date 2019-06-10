package ServiceTest;

import io.swagger.model.User;
import io.swagger.services.SessionService;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class LoginTest {
    private User user;
    private SessionService sessionService;
    @Before
    public void setup(){
        user = new User(1, "naam", "test@gmail.com", "wachtwoord", "straatnaam", "1060PC", 33, " ", "0600000000", null, null, false);
    }

    @Test
    public void IsEmployeeEmployee(){
        assertEquals(false, user.isIsEmployee());
    }

    @Test
    public void doesUserExist(){
        //assertEquals("test@gmail.com", user.getEmail());
        assertFalse(sessionService.userExist("test@gmail.com"));
    }

    @Test
    public void doesEmailFindId(){
        sessionService.getUserIdByEmail("test@gmail.com");
    }
    @Test
    public void doesCollectionServiceWork(){
        sessionService.getAllUsers();
    }
}
