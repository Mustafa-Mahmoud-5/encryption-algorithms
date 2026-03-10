package com.encryption.des.utils;

public class Permutator {
    public static String permutate(int[] permutation, String text) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < permutation.length; i++) {
            sb.append(text.charAt(permutation[i] - 1));
        }
        return sb.toString();
    }
}
