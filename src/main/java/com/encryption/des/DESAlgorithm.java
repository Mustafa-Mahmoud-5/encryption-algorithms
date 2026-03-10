package com.encryption.des;

import com.encryption.des.constants.PermutationMatrices;
import com.encryption.des.utils.BitsUtils;
import com.encryption.des.utils.Permutator;

public class DESAlgorithm extends CipherAlgorithm{
    ManglerFunction manglerFunction = new ManglerFunction();

    public DESAlgorithm(String key) throws Exception {
        this.bitSize = 64;
        this.keySize = 64;
        this.rounds = 16;
        this.setKey(key);
    }


    @Override
    public String encrypt(String text) throws Exception {
        validateText(text);

        text = Permutator.permutate(PermutationMatrices.INITIAL_PERMUTATION, text);
        System.out.println("Initial Permutation: " + text);
        String L = text.substring(0, 32);
        String R = text.substring(32);

        for(int i = 1; i <= rounds; i++) {
            System.out.println("========Round " + i + "=============");
            System.out.println("L:" + L);
            System.out.println("R:" + R);


            String rPrev = R;

            String roundKey = this.key.getRoundKey(i);
            R = BitsUtils.xorStrings(L, manglerFunction.apply(R, roundKey));

            L = rPrev;
            System.out.println("========Round " + i + " end=============");
            System.out.println("L:" + L);
            System.out.println("R:" + R);
        }
        String swappedText = R+L;
        String res = Permutator.permutate(PermutationMatrices.INVERSE_INITIAL_PERMUTATION, swappedText);
        System.out.println("Final Permutation: " + res);
        return res;
    }

    @Override
    public String decrypt(String cipher) throws Exception {
        validateText(cipher);
        cipher = Permutator.permutate(PermutationMatrices.INITIAL_PERMUTATION, cipher);
        System.out.println("Initial Permutation: " + cipher);
        String L = cipher.substring(0, 32);
        String R = cipher.substring(32);

        for(int i = rounds; i >= 1; i--) {
            System.out.println("========Round " + (i) + "=============");
            System.out.println("L:" + L);
            System.out.println("R:" + R);

            String rPrev = R;

            String roundKey = this.key.getRoundKey(i);
            R = BitsUtils.xorStrings(L, manglerFunction.apply(R, roundKey));

            L = rPrev;
            System.out.println("========Round " + (i) + " end=============");
            System.out.println("L:" + L);
            System.out.println("R:" + R);
        }
        String swappedText = R+L;
        String res = Permutator.permutate(PermutationMatrices.INVERSE_INITIAL_PERMUTATION, swappedText);
        System.out.println("Final Permutation: " + res);
        return res;
    }


    @Override
    protected void validateText(String text) throws Exception {
        if(text.length() != bitSize) {
            throw new Exception("invalid text/key size, must be 64 bits");
        }


        for(int i = 0; i < text.length(); i++) {
            if(text.charAt(i) != '0' && text.charAt(i) != '1') {
                System.out.println("Wrong Character Detected: " + text.charAt(i));
                throw new Exception("invalid character. only accepts bit string");
            }
        }
    }


    public static void main(String[] args) throws Exception {
        CipherAlgorithm des = new DESAlgorithm("0001001100110100010101110111100110011011101111001101111111110001");
        String text = "0000000100100011010001010110011110001001101010111100110111101111";
        String cipher = des.encrypt(text);
        String expectedCipher = "1000010111101000000100110101010000001111000010101011010000000101";
        System.out.println("cipher");
        System.out.println(cipher);
        System.out.println("Passed: " + (cipher.equals(expectedCipher)));
        System.out.println("Encryption Passed: " + (cipher.equals(expectedCipher)));

        String decyphered = des.decrypt(cipher);
        System.out.println("Decryption Passed: " + decyphered.equals(text));
    }
}
