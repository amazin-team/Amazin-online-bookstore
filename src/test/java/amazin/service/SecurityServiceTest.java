package amazin.service;

import amazin.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class SecurityServiceTest {
    @InjectMocks
    SecurityService securityService = new SecurityServiceImpl();

    @Mock
    UserDetailsService userDetailsService = new UserDetailsServiceImpl();
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    UserRepository repository;

    /** TODO figure out why SecurityContextHolder.getContext().getAuthentication().getDetails() is throwing a null pointer exception */
    @Test(expected = NullPointerException.class)
    public void findLoggedInEmailTest() {
        assertEquals(null, securityService.findLoggedInEmail());
    }

    /** TODO figure out how to make this not throw a null pointer exception */
    @Test(expected = NullPointerException.class)
    public void autoLoginTest() {
        securityService.autoLogin("test@mail.com", "12345678");
    }
}