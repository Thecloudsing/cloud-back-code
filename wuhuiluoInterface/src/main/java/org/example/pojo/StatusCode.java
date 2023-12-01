package org.example.pojo;

public enum StatusCode {

    SUCCESS(200),
    ERROR(400),
    NOT_FOUND(404),
    OTHER(500);
    public final int code;

    StatusCode(int code) {
        this.code = code;
    }
}
