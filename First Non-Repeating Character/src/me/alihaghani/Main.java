package me.alihaghani;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String str = "";
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a string:");
        str = input.nextLine().toString();
        System.out.println(firstNonRepeatingChar(str));

    }


    public static String firstNonRepeatingChar(String string){

        StringBuilder sb = new StringBuilder(string);
        HashSet<Character> repeating = new HashSet<>();
        LinkedList<Character> nonRepeating = new LinkedList<>();

        for (int i = 0; i<sb.length(); i++){
            if (repeating.contains(sb.charAt(i))){
                continue;
            } if (nonRepeating.contains(sb.charAt(i))){
                nonRepeating.remove((Character) sb.charAt(i));
                repeating.add(sb.charAt(i));
            }

            else {
                nonRepeating.add(sb.charAt(i));
            }
        }

        return nonRepeating.isEmpty() ? "No non-repeating characters." : "" + nonRepeating.get(0);
    }
}
