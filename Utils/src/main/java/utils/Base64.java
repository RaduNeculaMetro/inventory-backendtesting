package utils;


public class Base64 {

    public static String encode (byte [] bytes) {
        return new String (java.util.Base64.getEncoder().encode(bytes));

    }

    public static String encode (String toEncode) {
        return encode(toEncode.getBytes());
    }
}
