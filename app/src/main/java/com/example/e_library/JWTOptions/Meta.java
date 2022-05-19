package com.example.e_library.JWTOptions;

import com.google.gson.annotations.SerializedName;

public class Meta {
    private String status;
    private String message;
    private int code;

    public String getMessage() { return message; }

    public String getStatus() { return status; }

    public int getCode() { return code; }
}
