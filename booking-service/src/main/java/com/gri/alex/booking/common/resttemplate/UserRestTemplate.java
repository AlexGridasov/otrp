package com.gri.alex.booking.common.resttemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gri.alex.booking.domain.valueobject.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Component
public class UserRestTemplate {

    private static final Logger LOG = LoggerFactory.getLogger(UserRestTemplate.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    // private static final String userEndpoint = "http://user-service/v1/user";
    private static final String userEndpoint = "http://localhost:2082/v1/user";

    public void getUser() {
        try {
            ResponseEntity<Collection<UserVO>> response
                    = restTemplate.exchange(
                    userEndpoint + "?name=z",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }, "restaurants");

            LOG.info("Response status: {}", response.getStatusCode());
            LOG.info("Response headers: {}", response.getHeaders());
            LOG.info("Response body: {}", response.getBody());
            if (response.getStatusCodeValue() == 200) {
                response.getBody().forEach((UserVO userVO) -> LOG.info("UserVO: {}", userVO));
            }
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound ex) {
            LOG.info(ex.getMessage());
        }
    }

    public void postUser() throws Exception {
        UserVO userVO = new UserVO();
        userVO.setId("4");
        userVO.setName("Z User");
        userVO.setAddress("Z Address");
        userVO.setCity("Z City");
        userVO.setPhoneNo("1234567890");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(userVO),
                headers);

        ResponseEntity<UserVO> response
                = restTemplate.exchange(
                userEndpoint,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {
                }, new UserVO());

        LOG.info("Response status: {}", response.getStatusCode());
        LOG.info("Response headers: {}", response.getHeaders());
        LOG.info("Response body: {}", response.getBody());
    }

    public void putUser() throws Exception {
        UserVO userVO = new UserVO();
        userVO.setId("4");
        userVO.setName("Z User 1");
        userVO.setAddress("Z Address 1");
        userVO.setCity("Z City 1");
        userVO.setPhoneNo("1234567899");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity =
                new HttpEntity<>(objectMapper.writeValueAsString(userVO), headers);

        ResponseEntity<Void> response
                = restTemplate.exchange(
                userEndpoint + "/4",
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<>() {
                }, new UserVO());

        LOG.info("Response status: {}", response.getStatusCode());
        LOG.info("Response headers: {}", response.getHeaders());
    }

    public void deleteUser() {
        ResponseEntity<Void> response
                = restTemplate.exchange(
                userEndpoint + "/4",
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<>() {
                }, Void.class);

        LOG.info("Response status: {}", response.getStatusCode());
        LOG.info("Response headers: {}", response.getHeaders());
    }
}

