package util;

import exception.InvalidInputException;

public final class ValidationUtil {

    private ValidationUtil() {}

    public static void validateStringNotEmpty(String fieldName, String value) throws InvalidInputException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }

    public static void validateYear(int year) throws InvalidInputException {
        if (year < 1 || year > 4) {
            throw new InvalidInputException("Year of study must be between 1 and 4.");
        }
    }

    public static void validateCgpa(double cgpa) throws InvalidInputException {
        if (cgpa < 0.0 || cgpa > Constants.MAX_CGPA) {
            throw new InvalidInputException("CGPA must be between 0.0 and " + Constants.MAX_CGPA + ".");
        }
    }

    public static void validatePositiveInteger(String fieldName, int value) throws InvalidInputException {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be a positive integer (> 0).");
        }
    }

    public static void validateNonNegativeInteger(String fieldName, int value) throws InvalidInputException {
        if (value < 0) {
            throw new InvalidInputException(fieldName + " cannot be negative.");
        }
    }

    public static void validateNonNegativeDouble(String fieldName, double value) throws InvalidInputException {
        if (value < 0) {
            throw new InvalidInputException(fieldName + " cannot be negative.");
        }
    }
}