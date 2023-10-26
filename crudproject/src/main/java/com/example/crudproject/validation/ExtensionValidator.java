package com.example.crudproject.validation;

public class ExtensionValidator {
    public static boolean isNullOrEmptyOrNotEl(String str) {
        if(str == null || str.trim().isEmpty() || str.length() != 11)
            return true;
        return false;
    }

    public static boolean isNullOrEmptyOrNotCorrect(String str2) {
        if (str2 == null || str2.trim().isEmpty() || (str2.length() != 10 & str2.length() != 15 & str2.length() != 17))
            return true;
        return false;
    }
}
