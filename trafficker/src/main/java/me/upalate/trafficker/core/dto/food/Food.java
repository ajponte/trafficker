package me.upalate.trafficker.core.dto.food;

import java.util.Map;

/**
 * Food resource.
 */
public class Food {
	private String name;
	private double calories;
	private double fat;
	private double salt;
	private double sugar;
	private double carbs;
	private FoodType[] foodTypes;
    /**
     * The map for this Food item of it's taste type to the
     *  percentage makeup of the food.
     *  e.g. {UNAMI: 0.4, SALTY: 0.4, SWEET: 0.1, SOUR: 0.1, SPICY: 0}
     */
	private Map<TasteType, Double> tasteMap;
	
	/**
     * A new Food resource to represent a food item in our system.
     * Each Food is a representation of its nutritional contents.
    */
	public Food (String name,
                 double calories,
                 double fat,
                 double salt,
                 double sugar,
                 double carbs,
                 FoodType[] foodTypes,
                 Map<TasteType, Double> tasteMap) {
        this.calories = calories;
        this.name = name;
        this.fat = fat;
        this.salt = salt;
        this.sugar = sugar;
        this.carbs = carbs;
        this.foodTypes = foodTypes;
        this.tasteMap = tasteMap;
	}

    public String getName() {
        return name;
    }

    public double getFat() {
        return fat;
    }

    public double getSugar() {
        return sugar;
    }

    public FoodType[] getFoodTypes() {
        return foodTypes;
    }

    public Map<TasteType, Double> getTasteMap() {
        return tasteMap;
    }
}
