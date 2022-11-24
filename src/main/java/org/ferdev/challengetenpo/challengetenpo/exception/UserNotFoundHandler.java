package org.ferdev.challengetenpo.challengetenpo.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FunctionalInterface
public interface UserNotFoundHandler {
    void apply(HttpServletResponse response, HttpServletRequest request) throws IOException;
}
