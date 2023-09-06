package ru.kpfu.itis.arifulina;

import java.util.Map;

public interface HttpClientInterface {
    String get(String url, Map<String, String> params, String token) throws HttpClientException;
    String post(String url, Map<String, String> params, String token) throws HttpClientException;
    String put(String url, Map<String, String> params, String token) throws HttpClientException;
    String delete(String url, Map<String, String> params, String token) throws HttpClientException;
}
