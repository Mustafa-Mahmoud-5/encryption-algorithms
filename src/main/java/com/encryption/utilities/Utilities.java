package com.encryption.utilities;

public class Utilities {
    public static String preprocessText(String text , int choice )
    {
        text = text.toLowerCase();
        if(choice == 3 )
            text = text.replace(" ","");
        return text;
    }
}
