package amazin.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import amazin.model.User;
import amazin.model.Tag;
import amazin.model.Book;
import amazin.repository.RoleRepository;
import amazin.repository.UserRepository;
import amazin.security.AuthoritiesConstants;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setRoles(new HashSet<>(roleRepository.findByName(AuthoritiesConstants.USER)));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void recordUserTags(User user, List<Book> books) {
        for (Book book: books) {
            for (Tag tag: book.getTags()) {
                user.addTag(tag);
            }
        }
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
