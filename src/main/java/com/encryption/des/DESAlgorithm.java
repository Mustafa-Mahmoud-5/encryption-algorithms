package com.encryption.des;

import com.encryption.des.constants.PermutationMatrices;
import com.encryption.des.utils.BitsUtils;
import com.encryption.des.utils.Permutator;

public class DESAlgorithm extends CipherAlgorithm{
    ManglerFunction manglerFunction = new ManglerFunction();

    public DESAlgorithm() {
        this.bitSize = 64;
        this.keySize = 64;
        this.rounds = 16;
    }

    @Override
    public String encrypt(String text) throws Exception {
        validateText(text);

        text = Permutator.permutate(PermutationMatrices.INITIAL_PERMUTATION, text);

        String L = text.substring(0, 32);
        String R = text.substring(32);

        for(int i = 1; i <= rounds; i++) {
            String rPrev = R;

            String roundKey = BitsUtils.generateRandom64BitString();
            R = BitsUtils.xorStrings(L, manglerFunction.apply(R, roundKey));

            L = rPrev;
        }
        String swappedText = R+L;
        return Permutator.permutate(PermutationMatrices.INVERSE_INITIAL_PERMUTATION, swappedText);
    }

    @Override
    public String decrypt(String cipher) throws Exception {
        validateText(cipher);
        return "";
    }


    @Override
    protected void validateText(String text) throws Exception {
        if(text.length() != bitSize) {
            throw new Exception("invalid text size, must be 64 bits");
        }


        for(int i = 0; i < text.length(); i++) {
            if(text.charAt(i) != '0' || text.charAt(i) != '1') {
                throw new Exception("invalid character. only accepts bit string");
            }
        }
    }
}
