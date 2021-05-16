package app.service;

import app.entity.User;
import app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void validateUser_JelszoMegfeleloKarakterSzam_HaMindenRendbenVan() {
        // Given
        User user = new User("Teszt", "teszt");

        // When
        String jelszo = userService.validateUser(user);

        // Then
        assertEquals(user.getJelszo().length()>=4, jelszo.length()>=4);
    }

}