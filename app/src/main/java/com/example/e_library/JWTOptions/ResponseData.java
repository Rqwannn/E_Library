package com.example.e_library.JWTOptions;

public class ResponseData {
    private String access_token;
    private String token_type;
    private String message;

    public String getTokenType() { return token_type; }

    public String getAccessToken() { return access_token; }

    public String getMessage() { return message; }
}
