package com.cars;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Collection<Car> cars = new ArrayList<Car>();

        System.out.println("Good welcom to  the app");
        System.out.println("(1] Capture vehicle details\n"+
                "(2] View vehicle report\n"+
                "(3] Exit app");

        int menuOption = scanner.nextInt();

        while(menuOption == 1 || menuOption == 2) {

            if (menuOption == 1) {
                Car carObj = new Car();
                int year;
                int millage;
                String make;
                String model;
                String plateNum="";
                String vinNum;

                System.out.println("Enter make");
                make = scanner.next();

                System.out.println("Enter model");
                model = scanner.next();

                System.out.println("Enter vin");
                vinNum = scanner.next();
                while (!(vinNum.length() == 17)) {
                    System.out.println("Enter vin and make sure it 17 characters long");
                    vinNum = scanner.next();
                }

                System.out.println("Please Enter (1) for old format license plate number, and (2) for new format plate number");
                int plateChoice = scanner.nextInt();

                if (plateChoice == 1) {
                    System.out.println("Enter plate number e.g AAA555");
                    plateNum = scanner.next();
                }else if (plateChoice == 2) {
                    System.out.println("Enter plate number e.g AA66BBBP");
                    plateNum = scanner.next();
                }

                System.out.println("Enter millage");
                millage = scanner.nextInt();

                System.out.println("Enter year");
                year = scanner.nextInt();

                carObj.setMake(make);
                carObj.setModel(model);
                carObj.setPlateNumber(plateNum);
                carObj.setVin(vinNum);
                carObj.setYear(year);
                carObj.setMillage(millage);

                cars.add(carObj);
            }else if (menuOption == 2) {
                if (cars.isEmpty()) {
                    System.out.println("There are no cars captured");
                }else {
                    System.out.println("*********************************\n"+
                            "VEHICLE REPORT\n"+
                            "***************************************************");
                    for (Car carObject : cars) {
                        System.out.println("MAKE: "+carObject.getMake()+"\nMODEL: "+carObject.getModel());

                    }
                }
            }
            System.out.println("(1] Capture vehicle details\n"+
                    "(2] View vehicle report\n"+
                    "(3] Exit app");
            menuOption = scanner.nextInt();
        }
        System.out.println("Thanks for using the app");
    }
}
