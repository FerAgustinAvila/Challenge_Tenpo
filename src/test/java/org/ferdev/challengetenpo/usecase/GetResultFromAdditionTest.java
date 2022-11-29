package org.ferdev.challengetenpo.usecase;

import org.ferdev.challengetenpo.adapter.gateway.GetAddedPercentageGateway;
import org.ferdev.challengetenpo.adapter.repository.AdditionRepository;
import org.ferdev.challengetenpo.util.event.EventPublisher;
import org.ferdev.challengetenpo.util.jwt.JwtUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetResultFromAdditionTest {

    private GetAddedPercentageGateway getAddedPercentageGateway;
    private AdditionRepository additionRepository;

    @BeforeEach
    void setUp() {
        this.getAddedPercentageGateway = Mockito.mock(GetAddedPercentageGateway.class);
        this.additionRepository = Mockito.mock(AdditionRepository.class);
    }


}
