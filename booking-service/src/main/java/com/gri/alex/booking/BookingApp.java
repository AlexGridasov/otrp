package com.gri.alex.booking;

import com.gri.alex.booking.common.httpclient.UserRestClient;
import com.gri.alex.booking.common.openfeign.UserFeignClient;
import com.gri.alex.booking.common.resttemplate.UserRestTemplate;
import com.gri.alex.booking.domain.valueobject.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class BookingApp {

    public static void main(String[] args) {
        SpringApplication.run(BookingApp.class, args);
    }
}

@Profile("!test")
@Component
@ConditionalOnProperty(prefix = "command.autorun", name = "enabled",
        havingValue = "true", matchIfMissing = true)
class DiscoveryClientSample implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DiscoveryClientSample.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public void run(String... strings) {
        final String serviceName = "restaurant-service";

        LOG.info("\n{}", discoveryClient.description());
        // Get restaurant-service instances and prints its info
        discoveryClient.getInstances(serviceName).forEach(si ->
                LOG.info("\nInstance --> {}\nServer: {}\nPort: {}\nURI: {}\n\n",
                        si.getServiceId(),
                        si.getHost(),
                        si.getPort(),
                        si.getUri()
                )
        );
    }
}

@Profile("!test")
@Component
@ConditionalOnProperty(prefix = "command.autorun", name = "enabled",
        havingValue = "true", matchIfMissing = true)
class RestTemplateSample implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(RestTemplateSample.class);

    @Autowired
    private UserRestTemplate userRestTemplate;

    @Override
    public void run(String... strings) throws Exception {
        LOG.info("Creating new user");
        userRestTemplate.postUser();
        LOG.info("\n\nUpdate newly created user");
        userRestTemplate.putUser();
        LOG.info("\n\nRetrieve users again to check if newly created object got updated");
        userRestTemplate.getUser();
        LOG.info("\n\nDelete newly created user");
        userRestTemplate.deleteUser();
        LOG.info("\n\nRetrieve users again to check if deleted user still exists");
        userRestTemplate.getUser();
    }
}

@Profile("!test")
@Component
@ConditionalOnProperty(prefix = "command.autorun", name = "enabled",
        havingValue = "true", matchIfMissing = true)
class Java11HttpClientSample implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(Java11HttpClientSample.class);

    // Java 11 HttpClient for calling User REST endpoints
    @Autowired
    private UserRestClient httpClient;

    @Override
    public void run(String... strings) throws Exception {
        LOG.info("Creating new user");
        httpClient.postUser();
        LOG.info("\n\nUpdate newly created user");
        httpClient.putUser();
        LOG.info("\n\nRetrieve users");
        httpClient.getUser();
        LOG.info("\n\nPatch updated user");
        httpClient.patchUser();
        LOG.info("\n\nRetrieve patched user");
        httpClient.getUser();
        LOG.info("\n\nDelete newly created users");
        httpClient.deleteUser();
        LOG.info("\n\nRetrieve user again to check if deleted user still exists");
        httpClient.getUser();
    }
}

@Profile("!test")
@Component
@ConditionalOnProperty(prefix = "command.autorun", name = "enabled",
        havingValue = "true", matchIfMissing = true)
class OpenfeignClientSample implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(OpenfeignClientSample.class);

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public void run(String... strings) {
        LOG.info("Creating new user");
        UserVO userVO = new UserVO();
        userVO.setId("5");
        userVO.setName("Y User");
        userVO.setAddress("Y Address");
        userVO.setCity("Y City");
        userVO.setPhoneNo("1234567890");
        try {
            UserVO newUser = userFeignClient.postUser(userVO);
            assert newUser.getId().equals("5");
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        LOG.info("\n\nUpdate newly created user");
        userVO = new UserVO();
        userVO.setId("5");
        userVO.setName("Y User 1");
        userVO.setAddress("Y Address 1");
        userVO.setCity("Y City 1");
        userVO.setPhoneNo("1234567890");
        try {
            userFeignClient.putUser(5, userVO);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        LOG.info("\n\nRetrieve users again to check if newly created object got updated");
        try {
            userFeignClient.getUser("y")
                    .forEach((UserVO user) -> LOG.info("GET /v1/user --> {}", user));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        LOG.info("\n\nDelete newly created user");
        try {
            userFeignClient.deleteUser(5);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        LOG.info("\n\nRetrieve users again to check if deleted user still exists");
        try {
            userFeignClient.getUser("y")
                    .forEach((UserVO user) -> LOG.info("GET /v1/user --> {}", user));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
