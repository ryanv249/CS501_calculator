package com.example.lect2_simplemath_1;

public class CalculatorFunctions {

    public static double addition(Double num1, Double num2) {
        return num1 + num2;
    }
    public static double subtraction(Double num1, Double num2){
        return num1 - num2;
    }
    public static double mutliplication(Double num1, Double num2){
        return num1 * num2;
    }
    public static Double division(Double num1, Double num2){
        if(num1 == 0 || num2 == 0)
            return null;
        return num1 / num2;
    }
    public static double modula(Double num1, Double num2){
        return num1% num2;
    }


}
