package org.ferdev.challengetenpo.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FunctionalInterface
public interface UnauthorizedHandler {
    void apply(HttpServletResponse response, HttpServletRequest request) throws IOException;
}