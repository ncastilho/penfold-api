package org.penfold.website;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;

@Slf4j
public class RunAs {

    @FunctionalInterface
    public interface RunAsMethod {
        default void run() {
            try {
                runWithException();
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
            }
        }
        void runWithException() throws Exception;
    }

    public static void runAsAdmin(final RunAsMethod func) {
        final AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("system", "system",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        final Authentication originalAuthentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(token);
        func.run();
        SecurityContextHolder.getContext().setAuthentication(originalAuthentication);
    }

}