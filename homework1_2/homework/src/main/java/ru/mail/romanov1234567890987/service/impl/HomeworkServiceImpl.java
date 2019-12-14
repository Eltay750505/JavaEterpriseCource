package ru.mail.romanov1234567890987.service.impl;

import ru.mail.romanov1234567890987.service.HomeworkService;
import ru.mail.romanov1234567890987.service.model.Car;
import ru.mail.romanov1234567890987.service.model.CarModelsNamesEnum;
import ru.mail.romanov1234567890987.service.util.RandomUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HomeworkServiceImpl implements HomeworkService {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private RandomUtil randomUtil = new RandomUtil();

    @Override
    public void runFirstTask() {
        int count = 3;
        int x = randomUtil.getAnyInt();
        int y = randomUtil.getAnyInt();
        int z = randomUtil.getAnyInt();
        logger.debug("x = " + x + " y = " + y + " z = " + z);
        if (x > z) {
            int sum;
            sum = x + y;
            logger.debug("x + y = " + sum);
        } else {
            logger.debug("z = " + z);
        }
        int average = x + y + z / count;
        logger.info("Average = " + average);
    }

    @Override
    public void runSecondTask() {
        int arrayCapacity = 10;
        int[] integerArray = getIntegerArray(arrayCapacity);
        int max = findMax(integerArray);
        int min = findMin(integerArray);
        logger.debug("Min = " + min + " Max = " + max);
        replaceMaxBySpecificState(integerArray, max, min);
        logger.error(Arrays.toString(integerArray));
    }

    @Override
    public void runThirdTask() {
        int count = 10;
        int engineCountBound = 3;
        int engineCountFirstBound = 1;
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int randomEngineCount = randomUtil.getRandomNumber(engineCountBound, engineCountFirstBound);
            CarModelsNamesEnum carModel = getRandomCarModel(engineCountBound);
            String carName = "Name";
            Car car = new Car(carModel, randomEngineCount, carName + i);
            cars.add(car);
        }

        Map<Integer, List<Car>> carsMap = cars
                .stream()
                .collect(Collectors.
                        groupingBy(Car::getEngineCapacity));

        int nextInt = randomUtil.getAnyInt();

        if (nextInt < 1 || nextInt > 3) {
            logger.error("Such engineCapacity is not allowed");
        } else {
            logger.info("Entered engineCapacity " + nextInt + " searching begins");
            String directoryPath = "opt";
            String filePathName = "opt/cars.txt";
            File file = createFileAndDirectories(filePathName, directoryPath);
            if (carsMap.containsKey(nextInt)) {
                writeCarsToFile(file, carsMap.get(nextInt));
            }
        }
    }

    private void writeCarsToFile(File file, List<Car> cars) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Car car : cars) {
                fileWriter.write(car.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot write file : " + e.getMessage());
        }
    }

    private File createFileAndDirectories(String filePathName, String directoryPath) {
        File file = new File(directoryPath);
        File fileTxt = new File(filePathName);

        createDirectories(file);
        createFile(fileTxt);
        return fileTxt;
    }

    private CarModelsNamesEnum getRandomCarModel(int engineCountBound) {
        int nextInt = randomUtil.getRandomNumber(engineCountBound, 1);
        switch (nextInt) {
            case 1:
                return CarModelsNamesEnum.MERCEDES;
            case 2:
                return CarModelsNamesEnum.VOLKSWAGEN;
            case 3:
                return CarModelsNamesEnum.AUDI;
            default:
                return null;
        }
    }

    private int findMin(int[] integerArray) {
        int min = integerArray[0];
        for (int i = 0; i < integerArray.length; i++) {
            if (min > integerArray[i]) {
                min = integerArray[i];
            }
        }
        return min;
    }

    private void replaceMaxBySpecificState(int[] integerArray, int max, int min) {
        for (int i = 0; i < integerArray.length; i++) {
            if (integerArray[i] == max) {
                integerArray[i] = max * min;
            }
        }
    }

    private int findMax(int[] integerArray) {
        int max = integerArray[0];
        for (int i = 0; i < integerArray.length; i++) {
            if (max < integerArray[i]) {
                max = integerArray[i];
            }
        }
        return max;
    }

    private int[] getIntegerArray(int count) {
        int[] integerArray = new int[count];
        int maxBound = 300;
        int minBound = -300;
        for (int i = 0; i < count; i++) {
            integerArray[i] = randomUtil.getRandomNumber(maxBound, minBound);
        }
        return integerArray;
    }

    public void createFile(File file) {
        try {
            if (!file.exists()) {
                boolean newFile = file.createNewFile();

                if (!newFile) {
                    throw new FileNotFoundException("File not found!");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Can't find that file: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public void createDirectories(File file) {
        try {
            if (!file.exists()) {
                boolean isCreated = file.mkdir();
                if (!isCreated) {
                    isCreated = file.mkdirs();

                    if (!isCreated) {
                        throw new IllegalAccessException("Cannot make new directory");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("Illegal access: " + e.getMessage());
        }
    }
}
