package com.gri.alex.booking.common.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Map;

public class RestClient {

    HttpClient httpClient = HttpClient
            .newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    public Builder requestBuilder(URI uri, Map<String, String> additionalHeaders) {
        Builder builder = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json");

        if (!additionalHeaders.isEmpty()) {
            additionalHeaders.forEach((k, v) -> builder.header(k, v));
        }
        return builder;
    }

    public HttpResponse<String> send(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, BodyHandlers.ofString());
    }
}
