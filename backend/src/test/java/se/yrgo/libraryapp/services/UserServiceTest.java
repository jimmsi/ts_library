package se.yrgo.libraryapp.services;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.yrgo.libraryapp.dao.UserDao;
import se.yrgo.libraryapp.entities.LoginInfo;
import se.yrgo.libraryapp.entities.UserId;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class UserServiceTest {
    @Mock
    private UserDao userDao;
    @Mock
    private PasswordEncoder encoder;
    @Test
    @SuppressWarnings("deprecation")
    void correctLogin() {
        final String userId = "1";
        final UserId id = UserId.of(userId);
        final String username = "testuser";
        final String password = "password";
        final String passwordHash = "password";
        final LoginInfo info = new LoginInfo(id, passwordHash);
        final PasswordEncoder encoder = org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
        when(userDao.getLoginInfo(username)).thenReturn(Optional.of(info));
        UserService userService = new UserService(userDao, encoder);
        assertThat(userService.validate(username, password)).isEqualTo(Optional.of(id));
    }
    @Test
    @SuppressWarnings("deprecation")
    void userExistsIncorrectPassword() {
        final String userId = "1";
        final UserId id = UserId.of(userId);
        final String username = "testuser";
        final String password = "password";
        final String passwordHash = "password";
        final LoginInfo info = new LoginInfo(id, passwordHash);
        when(userDao.getLoginInfo(username)).thenReturn(Optional.of(info));
        when(encoder.matches(password, passwordHash)).thenReturn(false);
        UserService userService = new UserService(userDao, encoder);
        assertThat(userService.validate(username, password)).isEqualTo(Optional.empty());
    }
    @Test
    @SuppressWarnings("deprecation")
    void userDoesNotExist() {
        final String username = "testuser";
        final String password = "password";
        final PasswordEncoder encoder = org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
        when(userDao.getLoginInfo(username)).thenReturn(Optional.empty());
        UserService userService = new UserService(userDao, encoder);
        assertThat(userService.validate(username, password)).isEqualTo(Optional.empty());
    }
    @Test
    @SuppressWarnings("deprecation")
    void registerEscapesSingleQuoteInRealname() {
        final String name = "testname";
        final String realname = "O'Connor";
        final String expectedProcessedRealname = "O\\'Connor";
        final String password = "password";
        final String passwordHash = "hashedPassword";

        when(encoder.encode(password)).thenReturn(passwordHash);

        UserService userService = new UserService(userDao, encoder);
        userService.registerUser(name, realname, password);

        ArgumentCaptor<String> realnameCaptor = ArgumentCaptor.forClass(String.class);
        verify(userDao).register(any(), realnameCaptor.capture(), any());

        assertThat(realnameCaptor.getValue()).isEqualTo(expectedProcessedRealname);
    }




}
