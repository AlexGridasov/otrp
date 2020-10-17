package com.gri.alex.mcrsrvc.domain.model;

import java.util.Collection;

public class RestaurantService extends BaseService<Restaurant, String> {

    private final RestaurantRepository<Restaurant, String> restaurantRepository;

    public RestaurantService(RestaurantRepository<Restaurant, String> repository) {
        super(repository);
        restaurantRepository = repository;
    }

    @Override
    public void add(Restaurant restaurant) throws Exception {
        if (restaurantRepository.containsName(restaurant.getName())) {
            throw new Exception(
                    String.format("There is already a product with the name - %s", restaurant.getName()));
        }

        if (restaurant.getName() == null || restaurant.getName().isEmpty()) {
            throw new Exception("Restaurant name cannot be null or empty string.");
        }
        super.add(restaurant);
    }


    @Override
    public Collection<Restaurant> getAll() {
        return super.getAll();
    }
}
