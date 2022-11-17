package com.openclassrooms.testing.calcul.service;

import java.util.List;

public class SolutionFormatterImpl implements SolutionFormatter {

    public String format(Integer response){
 
        String resultNotFormatted = response.toString();
        int lengthSpaceCharsAdded = resultNotFormatted.length() / 3 + resultNotFormatted.length();
        char[] charArrayFormatted = new char [lengthSpaceCharsAdded];

        for(int i = 0; i<lengthSpaceCharsAdded; i++){
            if((i+1)%4==0){

                charArrayFormatted[lengthSpaceCharsAdded-i] = ' ';
            }else{
                charArrayFormatted[lengthSpaceCharsAdded-i] = resultNotFormatted.charAt(lengthSpaceCharsAdded - (i+i/3));
            }
        }

        System.out.println("\n Formatted solution : "+charArrayFormatted.toString()+" \n");
        return charArrayFormatted.toString(); 
    }

}
