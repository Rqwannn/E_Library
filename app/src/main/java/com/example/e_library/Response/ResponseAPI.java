package com.example.e_library.Response;

public class ResponseAPI {
    private Boolean Status;
    private String Message;
    private String Tokens;

    public String getMessage() {
        return Message;
    }

    public Boolean getStatus() { return Status; }

    public String getTokens() { return Tokens; }
}
