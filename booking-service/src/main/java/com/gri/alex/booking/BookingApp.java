package com.gri.alex.booking;

import com.gri.alex.booking.common.httpclient.UserRestClient;
import com.gri.alex.booking.common.resttemplate.UserRestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableEurekaClient
public class BookingApp {

    public static void main(String[] args) {
        SpringApplication.run(BookingApp.class, args);
    }
}

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

        LOG.info("\n{}",discoveryClient.description());
        // Get restaurant-service instances and prints its info
        discoveryClient.getInstances(serviceName).forEach(serviceInstance -> {
            LOG.info("\nInstance --> {}\nServer: {}\nPort: {}\nURI: {}\n\n",
                    serviceInstance.getServiceId(),
                    serviceInstance.getHost(),
                    serviceInstance.getPort(),
                    serviceInstance.getUri());
        });
    }
}

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
