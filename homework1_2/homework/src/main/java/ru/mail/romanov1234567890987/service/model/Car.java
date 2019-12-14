package ru.mail.romanov1234567890987.service.model;

public class Car {
    private String name;
    private CarModelsNamesEnum carModelsNamesEnum;
    private int engineCapacity;

    @Override
    public String toString() {
        return getName() + " " + carModelsNamesEnum.getName() +
                " " + getEngineCapacity() + "\n";
    }

    public Car(CarModelsNamesEnum carModel, int engineCapacity, String carName) {
        this.name = carName;
        this.carModelsNamesEnum = carModel;
        this.engineCapacity = engineCapacity;
    }

    public String getName() {
        return name;
    }

    public String getCarModelsNamesEnum() {
        return carModelsNamesEnum.getName();
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }
}
