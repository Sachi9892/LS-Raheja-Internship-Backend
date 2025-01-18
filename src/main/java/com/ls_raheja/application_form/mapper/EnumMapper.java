package com.ls_raheja.application_form.mapper;

public class EnumMapper {

    public static <E extends Enum<E>> E mapToEnum(Class<E> enumClass, String value) {
        if (value == null || value.isEmpty()) {
            return null; // Handle null or empty values gracefully
        }
        try {
            return Enum.valueOf(enumClass, value.toUpperCase()); // Convert to uppercase for consistency
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid value '" + value + "' for enum " + enumClass.getSimpleName());
        }
    }
}
