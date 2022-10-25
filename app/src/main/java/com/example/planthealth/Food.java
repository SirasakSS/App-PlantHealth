package com.example.planthealth;

public class Food {
    private String name;
    private String unit;
    private String calories;
    private String type;

    public Food(){}
    public  Food(String name, String unit, String calories, String type) {
        this.name = name;
        this.unit = unit;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
