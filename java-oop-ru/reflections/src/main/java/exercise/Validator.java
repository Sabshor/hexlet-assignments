package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        Field[] fields = address.getClass().getDeclaredFields();
        List<String> errors = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        errors.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return errors;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Field[] fields = address.getClass().getDeclaredFields();
        Map<String, List<String>> outMap = new HashMap<>();
        for (Field field : fields) {
            List<String> listErrorsField = new ArrayList<>();
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        listErrorsField.add("can not be null");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            if (field.isAnnotationPresent(MinLength.class)) {
                try {
                    if (field.get(address) != null) {
                        field.setAccessible(true);
                        MinLength minLength = field.getAnnotation(MinLength.class);
                        int lengthValidate = field.get(address).toString().length();
                        int length = minLength.minLength();
                        if (lengthValidate < length) {
                            listErrorsField.add("length less than " + length);
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            if (!listErrorsField.isEmpty()) {
                outMap.put(field.getName(), listErrorsField);
            }
        }
        return outMap;
    }
}
// END
