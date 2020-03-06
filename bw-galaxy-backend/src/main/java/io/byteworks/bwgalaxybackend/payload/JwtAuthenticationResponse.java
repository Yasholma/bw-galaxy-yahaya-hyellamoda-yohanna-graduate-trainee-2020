package io.byteworks.bwgalaxybackend.payload;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Yasholma on 26-Feb-20.
 */
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String authority;



    public JwtAuthenticationResponse(String accessToken, Collection<?> authority) {
        this.accessToken = accessToken;
        this.authority = authority.stream().map(Object::toString).collect(Collectors.joining(""));
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(Collection<?> authority) {
        this.authority = authority.toString();
    }
}