package amazin.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void setUp() throws Exception {
        this.user = new User();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getId() {
        user.setId(1L);

        assertEquals(1L, user.getId().longValue());
    }

    @Test
    public void setId() {
        user.setId(new Long(4));

        assertEquals(4L, user.getId().longValue());
    }

    @Test
    public void getFirstName() {
        user.setFirstName("John");

        assertEquals("John", user.getFirstName());
    }

    @Test
    public void setFirstName() {
        user.setFirstName("John");

        assertEquals("John", user.getFirstName());
    }

    @Test
    public void getLastName() {
        user.setLastName("Smith");

        assertEquals("Smith", user.getLastName());
    }

    @Test
    public void setLastName() {
        user.setLastName("Smith");

        assertEquals("Smith", user.getLastName());
    }

    @Test
    public void getEmail() {
        user.setEmail("abc@gmail.com");

        assertEquals("abc@gmail.com", user.getEmail());
    }

    @Test
    public void setEmail() {
        user.setEmail("abc@gmail.com");

        assertEquals("abc@gmail.com", user.getEmail());
    }

    @Test
    public void getPassword() {
        user.setPassword("123");

        assertEquals("123", user.getPassword());
    }

    @Test
    public void setPassword() {
        user.setPassword("123");

        assertEquals("123", user.getPassword());
    }

    @Test
    public void getPasswordConfirmation() {
        user.setPasswordConfirmation("1234");

        assertEquals("1234", user.getPasswordConfirmation());
    }

    @Test
    public void setPasswordConfirmation() {
        user.setPasswordConfirmation("1234");

        assertEquals("1234", user.getPasswordConfirmation());
    }

    @Test
    public void getRoles() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role());
        user.setRoles(roles);

        assertEquals(1, user.getRoles().size());
    }

    @Test
    public void setRoles() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role());
        user.setRoles(roles);

        assertEquals(1, user.getRoles().size());
    }
}