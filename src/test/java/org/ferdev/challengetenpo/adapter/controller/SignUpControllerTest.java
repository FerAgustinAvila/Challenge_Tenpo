package org.ferdev.challengetenpo.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ferdev.challengetenpo.adapter.controller.auth.SignUpController;
import org.ferdev.challengetenpo.dto.auth.SignupRequest;
import org.ferdev.challengetenpo.exception.UnauthorizedHandler;
import org.ferdev.challengetenpo.exception.UserNotFoundHandler;
import org.ferdev.challengetenpo.mapper.UserMapper;
import org.ferdev.challengetenpo.service.impl.UserDetailsServiceImpl;
import org.ferdev.challengetenpo.usecase.ExistEmail;
import org.ferdev.challengetenpo.usecase.ExistUsername;
import org.ferdev.challengetenpo.usecase.SaveUser;
import org.ferdev.challengetenpo.util.event.EventPublisher;
import org.ferdev.challengetenpo.util.jwt.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SignUpController.class)
@AutoConfigureMockMvc(addFilters = false)
class SignUpControllerTest {

    private static final String SIGNUP_ENDPOINT = "/auth/signup";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ExistUsername existUsername;
    @MockBean
    private ExistEmail existEmail;
    @MockBean
    private SaveUser saveUser;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    @Qualifier("AsyncEventPublisher")
    private EventPublisher eventPublisher;
    @MockBean
    private UserNotFoundHandler userNotFoundHandler;
    @MockBean
    private UnauthorizedHandler unauthorizedHandler;
    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void whenUsernameIsMissing_thenReturnBadRequest() throws Exception {
        SignupRequest signupRequest = new SignupRequest(null, "some_email@email.com", "password");
        ObjectMapper objectMapper = new ObjectMapper();

        var request = post(SIGNUP_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest));

        var resultActions = mockMvc.perform(request);

        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void whenPasswordIsMissing_thenReturnBadRequest() throws Exception {
        SignupRequest signupRequest = new SignupRequest("someUser", "some_email@email.com", null);
        ObjectMapper objectMapper = new ObjectMapper();

        var request = post(SIGNUP_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest));

        var resultActions = mockMvc.perform(request);

        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void whenEmailIsMissing_thenReturnBadRequest() throws Exception {
        SignupRequest signupRequest = new SignupRequest("someUser", null, "somePassword");
        ObjectMapper objectMapper = new ObjectMapper();

        var request = post(SIGNUP_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest));

        var resultActions = mockMvc.perform(request);

        resultActions.andExpect(status().isBadRequest());
    }
}
