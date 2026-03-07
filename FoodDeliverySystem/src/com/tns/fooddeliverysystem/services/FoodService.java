package com.tns.fooddeliverysystem.services;

import java.util.ArrayList;
import java.util.List;
import com.tns.fooddeliverysystem.entities.*;

public class FoodService {

    private List<Restaurant> restaurants = new ArrayList<>();

    public void addRestaurant(Restaurant r) {
        restaurants.add(r);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void addFoodItemToRestaurant(int restId, FoodItem item) {

        for(Restaurant r : restaurants) {
            if(r.getId() == restId) {
                r.addFoodItem(item);
            }
        }
    }
}