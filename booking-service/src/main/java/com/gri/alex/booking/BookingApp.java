package com.gri.alex.booking;

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

