package amazin.service;

public interface SecurityService {
    String findLoggedInEmail();
    void autoLogin(String email, String password);
}
