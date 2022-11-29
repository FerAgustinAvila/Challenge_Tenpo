package org.ferdev.challengetenpo.usecase;

import org.ferdev.challengetenpo.adapter.repository.UserRepository;
import org.ferdev.challengetenpo.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class ExistUsernameTest {

    private static UserRepository userRepository;

    @BeforeAll
    static void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"userMock"})
    void whenUserAlreadyExist_thenReturnTrue(String userIncoming) {

        // Given
        User userMock = getSomeUser(userIncoming);
        assert userMock != null;
        Mockito.when(userRepository.existsByUsername(userIncoming))
                .thenReturn(true);

        // When
        Boolean result = userRepository.existsByUsername(userIncoming);

        // Then
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"userMock"})
    void whenUserDoesNotExist_thenReturnFalse(String userIncoming) {

        // Given
        User userMock = getSomeUser(userIncoming);
        assert userMock != null;
        Mockito.when(userRepository.existsByUsername(userIncoming))
                .thenReturn(false);

        // When
        Boolean result = userRepository.existsByUsername(userIncoming);

        // Then
        assertFalse(result);
    }

    private User getSomeUser(String userIncoming) {

        if ("userMock".equals(userIncoming)) {
            return new User(1L, "userMock", "email", "password", null);
        }
        return null;

    }
}
