package com.example.providerApi.util;

import lombok.Data;

@Data
public class Response<T> {

    private Boolean success;

    private T result;

    private String error;

    public Boolean isSuccess() {
        return this.success;
    }

    public static <T> Response ok (T result) {
        Response<T> resp = new Response<>();

        resp.setSuccess(true);
        resp.setResult(result);

        return resp;
    }

    public static <T> Response fail (String error) {
        Response<T> resp = new Response<>();

        resp.setSuccess(false);
        resp.setError(error);

        return resp;
    }
}
