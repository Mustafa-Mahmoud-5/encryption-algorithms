// Asmaa Class

package com.encryption.des;

// TODO(Asmaa): to be refactored if we've available time:
//  1- move the matrices to constants
//  2- reuse the Permutator.permutate() in utils
public class Key {

    String key;               // input
    int[] pc1;                // to get 56 bit from 64 bit
    int[] pc2;                // to make 56 bit to 48 bit (KEY)
    int[] bits;
    String[] keys;      // 3la4an el access by round

    public Key(String key) {

        this.key = key;
        keys = new String[16];

        pc1 = new int[]{
                57,49,41,33,25,17,9,
                1,58,50,42,34,26,18,
                10,2,59,51,43,35,27,
                19,11,3,60,52,44,36,
                63,55,47,39,31,23,15,
                7,62,54,46,38,30,22,
                14,6,61,53,45,37,29,
                21,13,5,28,20,12,4
        };

        pc2 = new int[]{
                14,17,11,24,1,5,
                3,28,15,6,21,10,
                23,19,12,4,26,8,
                16,7,27,20,13,2,
                41,52,31,37,47,55,
                30,40,51,45,33,48,
                44,49,39,56,34,53,
                46,42,50,36,29,32
        };

        bits = new int[]{
                1,1,2,2,2,2,2,2,
                1,2,2,2,2,2,2,1
        };
    }

    private String permutate(String input, int[] table) {

        StringBuilder output = new StringBuilder();

        for(int i=0; i < table.length; i++)
            output.append(input.charAt(table[i]-1));

        return output.toString();
    }

    private String shiftleft(String input, int shifts) {

        String result = input;

        for(int i=0;i<shifts;i++)
            result = result.substring(1) + result.charAt(0);

        return result;
    }

    public void generate() {

        // pc1
        String permutedKey = permutate(key, pc1);
        System.out.println("permutedKey");
        System.out.println(permutedKey);

        // split c and d
        String C = permutedKey.substring(0,28);
        String D = permutedKey.substring(28);
        System.out.println("C");
        System.out.println(C);
        System.out.println("D");
        System.out.println(D);

        for(int r = 0; r < 16; r++) {
            C = shiftleft(C, bits[r]);
            D = shiftleft(D, bits[r]);

            String full = C + D;

            keys[r] = permutate(full, pc2);
        }
    }

    public String getRoundKey(int r) {

        return keys[r-1];
    }
}