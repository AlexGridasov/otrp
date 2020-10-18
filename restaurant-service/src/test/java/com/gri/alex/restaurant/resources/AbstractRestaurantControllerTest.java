package com.gri.alex.restaurant.resources;

import com.gri.alex.restaurant.domain.model.entity.Entity;
import com.gri.alex.restaurant.domain.model.entity.Restaurant;
import com.gri.alex.restaurant.domain.valueobject.RestaurantVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractRestaurantControllerTest {

    /**
     * RESTAURANT ID constant having value 1
     */
    protected static final String RESTAURANT = "1";

    /**
     * RESTAURANT name constant having value Big-O Restaurant
     */
    protected static final String RESTAURANT_NAME = "Le Meurice";

    /**
     * RESTAURANT address constant
     */
    protected static final String RESTAURANT_ADDRESS = "228 rue de Rivoli, 75001, Paris";

    @Autowired
    RestaurantController restaurantController;

    /**
     * Test method for findById method
     */
    @Test
    public void validResturantById() throws Exception {
        Logger.getGlobal().info("Start validResturantById test");
        ResponseEntity<Entity<String>> restaurant = restaurantController.findById(RESTAURANT);

        assertEquals(HttpStatus.OK, restaurant.getStatusCode());
        assertTrue(restaurant.hasBody());
        assertNotNull(restaurant.getBody());
        assertEquals(RESTAURANT, restaurant.getBody().getId());
        assertEquals(RESTAURANT_NAME, restaurant.getBody().getName());
        Logger.getGlobal().info("End validResturantById test");
    }

    /**
     * Test method for findByName method
     */
    @Test
    public void validResturantByName() throws Exception {
        Logger.getGlobal().info("Start validResturantByName test");
        ResponseEntity<Collection<Restaurant>> restaurants = restaurantController
                .findByName(RESTAURANT_NAME);
        Logger.getGlobal().info("In validAccount test");

        assertEquals(HttpStatus.OK, restaurants.getStatusCode());
        assertTrue(restaurants.hasBody());
        assertNotNull(restaurants.getBody());
        assertFalse(restaurants.getBody().isEmpty());
        Restaurant restaurant = (Restaurant) restaurants.getBody().toArray()[0];
        assertEquals(RESTAURANT, restaurant.getId());
        assertEquals(RESTAURANT_NAME, restaurant.getName());
        Logger.getGlobal().info("End validResturantByName test");
    }

    /**
     * Test method for add method
     */
    @Test
    public void validAdd() throws Exception {
        Logger.getGlobal().info("Start validAdd test");
        RestaurantVO restaurant = new RestaurantVO();
        restaurant.setId("999");
        restaurant.setName("Test Restaurant");

        ResponseEntity<Restaurant> restaurants = restaurantController.add(restaurant);
        assertEquals(HttpStatus.CREATED, restaurants.getStatusCode());
        Logger.getGlobal().info("End validAdd test");
    }
}
