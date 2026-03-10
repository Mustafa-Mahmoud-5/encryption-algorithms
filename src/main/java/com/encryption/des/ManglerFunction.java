package com.encryption.des;

import com.encryption.des.constants.PermutationMatrices;
import com.encryption.des.constants.SBoxes;
import com.encryption.des.utils.BitsUtils;
import com.encryption.des.utils.Permutator;

public class ManglerFunction {
    public String apply(String text, String roundKey) {
        // 1- expansion to 48 bits
        text = Permutator.permutate(PermutationMatrices.EXPANSION_TABLE, text);

        // 2- XOR with round key
        text = BitsUtils.xorStrings(text, roundKey);

        // 3- get back to 32 bits
        text = applySBoxesKeySubstitution(text);

        // 4- Transposition P-box
        text = Permutator.permutate(PermutationMatrices.P_BOX, text);
        // return res

        return text;
    }

    private String applySBoxesKeySubstitution(String text) {
        StringBuilder res = new StringBuilder();
        int p = 0;
        for(int i = 0; i < 8; i++) {
            String sub = text.substring(p, p+6);
            String rowBit = (sub.charAt(0) + "" + sub.charAt(sub.length() - 1));
            String colBit =   sub.substring(1, sub.length() - 1);



            int row = BitsUtils.bitToInt(rowBit);
            int col = BitsUtils.bitToInt(colBit);

            int sBoxResInt = SBoxes.S_BOXES[i][row][col];
            String SBoxRes= BitsUtils.intToBit(sBoxResInt);

            res.append(SBoxRes);
            p+=6;
        }
        return res.toString();
    }
}
