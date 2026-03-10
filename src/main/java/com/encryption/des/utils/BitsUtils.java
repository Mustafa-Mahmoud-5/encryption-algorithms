package com.encryption.des.utils;

import java.security.SecureRandom;

public class BitsUtils {
    public static int bitToInt(String bit) {
        return Integer.parseInt(bit, 2);
    }

    public static String intToBit(int intVal) {
        return Integer.toBinaryString(intVal);
    }

    public static String generateRandom64BitString() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 64; i++) {
            sb.append(random.nextInt(2));
        }
        return sb.toString();
    }

    public static String xorStrings(String s1, String s2) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                sb.append('0');
            } else {
                sb.append('1');
            }
        }
        return sb.toString();
    }
}
