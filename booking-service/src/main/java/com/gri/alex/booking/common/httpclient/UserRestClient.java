package com.gri.alex.booking.common.httpclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gri.alex.booking.domain.valueobject.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.Map;

@Component
public class UserRestClient {

    private static final Logger LOG = LoggerFactory.getLogger(UserRestClient.class);

    private static final String userEndpoint = "http://localhost:2082/v1/user";

    @Autowired
    private ObjectMapper objectMapper;

    private final RestClient restClient = new RestClient();

    public void getUser() throws Exception {
        HttpRequest request = restClient.requestBuilder(
                URI.create(userEndpoint + "?name=x"),
                Map.of()
        ).GET().build();

        HttpResponse<String> response = restClient.send(request);
        LOG.info("Response status code: {}", response.statusCode());
        LOG.info("Response headers: {}", response.headers());
        LOG.info("Response body: {}", response.body());

        if (response.statusCode() == 200) {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            UserVO[] userVO = objectMapper.readValue(response.body(), UserVO[].class);
            LOG.info("UserVO: {}", userVO.length);
        }
    }

    public void postUser() throws Exception {
        UserVO userVO = new UserVO();
        userVO.setId("3");
        userVO.setName("X User");
        userVO.setAddress("X Address");
        userVO.setCity("X City");
        userVO.setPhoneNo("1234567890");

        HttpRequest request = restClient.requestBuilder(
                URI.create(userEndpoint),
                Map.of()
        ).POST(BodyPublishers.ofString(objectMapper.writeValueAsString(userVO)))
                .build();

        HttpResponse<String> response = restClient.send(request);
        LOG.info("Response status code: {}", response.statusCode());
        LOG.info("Response headers: {}", response.headers());
        LOG.info("Response body: {}", response.body());
    }

    public void putUser() throws Exception {
        UserVO userVO = new UserVO();
        userVO.setId("3");
        userVO.setName("X User 1");
        userVO.setAddress("X Address 1");
        userVO.setCity("X City 1");
        userVO.setPhoneNo("1234567899");

        HttpRequest request = restClient.requestBuilder(
                URI.create(userEndpoint + "/3"),
                Map.of()
        ).PUT(BodyPublishers.ofString(objectMapper.writeValueAsString(userVO)))
                .build();

        HttpResponse<String> response = restClient.send(request);
        LOG.info("Response status code: {}", response.statusCode());
        LOG.info("Response headers: {}", response.headers());
        LOG.info("Response body: {}", response.body());
    }

    public void patchUser() throws Exception {
        HttpRequest request = restClient.requestBuilder(
                URI.create(userEndpoint + "/3/name?value=Duke+Williams"), Map.of())
                .method("PATCH", BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = restClient.send(request);
        LOG.info("Response status code: {}", response.statusCode());
        LOG.info("Response headers: {}", response.headers());
        LOG.info("Response body: {}", response.body());
    }

    public void deleteUser() throws Exception {
        HttpRequest request = restClient.requestBuilder(
                URI.create(userEndpoint + "/3"),
                Map.of()
        ).DELETE().build();

        HttpResponse<String> response = restClient.send(request);
        LOG.info("Response status code: {}", response.statusCode());
        LOG.info("Response headers: {}", response.headers());
        LOG.info("Response body: {}", response.body());
    }
}
