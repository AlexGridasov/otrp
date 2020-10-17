package com.gri.alex.domain;

import com.gri.alex.mcrsrvc.domain.model.Restaurant;
import com.gri.alex.mcrsrvc.domain.model.RestaurantService;
import com.gri.alex.mcrsrvc.domain.model.Table;
import com.gri.alex.mcrsrvc.persistence.InMemRestaurantRepository;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RestaurantApp {

    public static void main(String[] args) {
        try {
            // Initialize the RestaurantService
            RestaurantService restaurantService = new RestaurantService(new InMemRestaurantRepository());

            // Data Creation for Restaurants
            List<Table> tableList = Arrays.asList(
                    new Table("Table 1", BigInteger.ONE, 6),
                    new Table("Table 2", BigInteger.valueOf(2), 4),
                    new Table("Table 3", BigInteger.valueOf(3), 2)
            );

            // Add few restaurants using Service
            // Note: To raise an exception give same restaurant name to one of the below restaurant
            restaurantService.add(new Restaurant("Big-O Restaurant", "1", tableList));
            restaurantService.add(new Restaurant("Pizza Shops", "2", List.of()));
            restaurantService.add(new Restaurant("La Pasta", "3", List.of()));

            // Retrieving all restaurants using Service
            Collection<Restaurant> restaurants = restaurantService.getAll();

            // Print the retrieved restaurants on console
            System.out.println("Restaurants List:");
            restaurants.stream()
                    .map(r -> String.format("Restaurant: %s", r))
                    .forEach(System.out::println);

        } catch (Exception ex) {
            System.out.printf("Exception: %s%n", ex.getMessage());
            // Exception Handling Code
        }
    }
}
