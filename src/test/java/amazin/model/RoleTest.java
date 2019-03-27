package amazin.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RoleTest {

    private Role role;

    @Before
    public void setUp() throws Exception {
        this.role = new Role();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() {
        role.setName("admin");

        assertEquals("admin", role.getName());
    }

    @Test
    public void setName() {
        role.setName("admin");

        assertEquals("admin", role.getName());
    }

    @Test
    public void getUsers() {
        Set<User> users = new HashSet<>();
        users.add(new User());
        role.setUsers(users);

        assertEquals(1, role.getUsers().size());
    }

    @Test
    public void setUsers() {
        Set<User> users = new HashSet<>();
        users.add(new User());
        role.setUsers(users);

        assertEquals(1, role.getUsers().size());
    }
}