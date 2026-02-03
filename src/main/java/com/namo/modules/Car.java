package com.namo.modules;

public class Car {
    private String brand;

    public Car(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public static void changeBrand(Car car) {
        car = new Car("Audi");
    }

    public static void main(String[] args) {
        Car car = new Car("Mercedes");
        changeBrand(car);
        System.out.println(car.getBrand());
    }
}
