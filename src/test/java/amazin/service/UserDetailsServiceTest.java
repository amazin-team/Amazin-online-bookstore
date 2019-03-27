package amazin.service;

import amazin.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDetailsServiceTest {
    @InjectMocks
    UserDetailsService service = new UserDetailsServiceImpl();

    @Mock
    UserRepository repository;

    private static String username = "test@gmail.com";

    @Test(expected = UsernameNotFoundException.class)
    public void loadByUsernameNotFoundTest() {
        service.loadUserByUsername(username);
    }
}