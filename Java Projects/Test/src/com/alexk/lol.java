package com.alexk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class lol {

	public static void main(String[] args) {
		String dog = "Mr John Smith        ";
		char[] yes = dog.toCharArray();
		System.out.println(Arrays.toString(yes));
		
		System.out.println(replaceWhiteSpace(yes, 13));
	}
	
	//1.4 CTCI
	public static char[] replaceWhiteSpace(char[] string, int trueLength) {
	    for (int i = 0; i < string.length; i++) {
	    	if (i + 1 < string.length) {
		        if (string[i] == ' ' && string[i + 1] != ' ') {
			         
		            for (int j = trueLength; j >= i; j--) {
		                string[j + 2] = string[j];
		            }
		            string[i] = '%';
		            string[i+1] = '2';
		            string[i+2] = '0';
		            trueLength += 2;
		        } 
	    	}
	    }
	    return string;
	}

	//1.3 CTCI
	public static boolean isPermu (String str1, String str2) {    
	    //store each string into char[]
	    char[] temp1 = str1.toCharArray();
	    char[] temp2 = str2.toCharArray();
	    boolean result = false;
	    
	    if (temp1.length == temp2.length) {
	        Arrays.sort(temp1);
	        Arrays.sort(temp2);
	        //result = temp1.equals(temp2);
	        for (int i = 0; i < temp1.length; i++) {
	        	if (temp1[i] == temp2[i]) {
	        		
	        	} else {
	        		return result;
	        	}
	        }
	        System.out.println(temp1.equals(temp2));
	        return true;
	    } else {
	        return result;
	    }
	}
}
