package amazin.service;

import amazin.model.User;
import amazin.repository.RoleRepository;
import amazin.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @InjectMocks
    UserService service = new UserServiceImpl();

    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private static final Long ID = 1L;
    private static String EMAIL = "test@gmail.com";

    @Test
    public void findByEmailTest() {
        service.findByEmail(EMAIL);
        verify(userRepository).findByEmail(EMAIL);
    }

    @Test
    public void saveTest() {
        User user = new User();
        user.setId(ID);
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setEmail("jsmith@mail.com");
        user.setPassword("12345678");
        user.setPasswordConfirmation("12345678");

        service.save(user);

        ArgumentCaptor<User> userArgument = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userArgument.capture());
        verifyNoMoreInteractions(userRepository);

        User model = userArgument.getValue();

        assertNotNull(model.getId());
        assertEquals(user.getFirstName(), model.getFirstName());
        assertEquals(user.getLastName(), model.getLastName());
        assertEquals(user.getEmail(), model.getEmail());
        /** TODO: figure out how to get BCRYPT password encoder working here, want to test to ensure the
         * prev password is not the one stored, it should be hashed
         * + the roles should be checked here to ensure it's added properly*/
    }
}