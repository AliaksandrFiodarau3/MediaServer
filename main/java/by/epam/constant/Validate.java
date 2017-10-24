package by.epam.constant;

/**
 * This class keeping constants with reg exp
 * */

public class Validate {

    public static final String FIELD_LOGIN = "^[a-zA-Z0-9]+$";
    public static final String FIELD_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
    public static final String FIELD_NAME = "^[\\D]{3,20}$";
    public static final String FIELD_EMAIL = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    public static final String FIELD_YEAR = "[0-9]{4}";
}
