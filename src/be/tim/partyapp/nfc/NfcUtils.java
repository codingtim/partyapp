package be.tim.partyapp.nfc;

/**
 */
public class NfcUtils {

    public static String getHexString(byte[] data) {
        return getHexString(data, data.length);
    }

    public static String getHexString(byte[] data, int size) {
        StringBuilder hex = new StringBuilder(size * 2);
        for (int i = 0; i < size; i++) {
            hex.append(String.format(" 0x%02x", data[i]));
        }

        return hex.toString();
    }

    public static String getString(byte[] data) {
        return getString(data, data.length);
    }

    public static String getString(byte[] data, int size) {
        StringBuilder hex = new StringBuilder(size * 2);
        for (int i = 0; i < size; i++) {
            hex.append(String.format("%02x", data[i]));
        }

        return hex.toString();
    }


}
