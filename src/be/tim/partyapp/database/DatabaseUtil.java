package be.tim.partyapp.database;

/**
 */
public class DatabaseUtil {

    public static int booleanToInteger(boolean bool) {
        return bool ? 1: 0;
    }

    public static boolean integerToBoolean(int i) {
        return i == 1;
    }
}
