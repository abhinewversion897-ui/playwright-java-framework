package utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    static Faker faker = new Faker();

    // First Name
    public String getFirstName() {

        return faker.name().firstName();
    }

    // Last Name
    public static String getLastName() {

        return faker.name().lastName();
    }

    // Full Name
    public static String getFullName() {

        return faker.name().fullName();
    }

    // Email
    public static String getEmail() {

        return faker.internet().emailAddress();
    }

    // Phone Number
    public static String getPhoneNumber() {

        return faker.phoneNumber().cellPhone();
    }

    // Employee ID
    public String getEmployeeId() {

        return faker.number().digits(5);
    }
}