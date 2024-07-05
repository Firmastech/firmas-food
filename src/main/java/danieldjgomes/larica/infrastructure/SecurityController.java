package danieldjgomes.larica.infrastructure;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {


    @GetMapping("/testing")
    public String testing(@AuthenticationPrincipal OidcUser principal) {
        return String.format("""
                        	<h1>Oauth2 🔐  </h1>
                        <h3>Principal: %s</h3>
                        <h3>Email attribute: %s</h3>
                        <h3>Authorities: %s</h3>
                        <h3>JWT: %s</h3>
                        """, principal, principal.getAttribute("email"), principal.getAuthorities(),
                principal.getIdToken().getTokenValue());
    }

    @PreAuthorize("#restauranteId.equals(principal.getAttribute(\"restaurante\"))")
    @GetMapping("/test/{restauranteId}")
    String test2(@PathVariable("restauranteId") String restauranteId, @AuthenticationPrincipal OidcUser principal) {
        return String.format("""
                        	<h1>Oauth2 🔐  </h1>
                        <h3>Principal: %s</h3>
                        <h3>Email attribute: %s</h3>
                        <h3>Authorities: %s</h3>
                        <h3>JWT: %s</h3>
                        <h3>restaurante: %s</h3>
                        """, principal, principal.getAttribute("email"), principal.getAuthorities(),
                principal.getIdToken().getTokenValue(),principal.getAttribute("restaurante"));
    }

}


