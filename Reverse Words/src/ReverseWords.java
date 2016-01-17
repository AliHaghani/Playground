import java.util.Scanner;

public class ReverseWords {

    public static void main(String[] args) {
        String str = "";
        Scanner input = new Scanner(System.in);
        System.out.print("Type in a sentence:");
        str = input.nextLine();
        reverseWords(str);
    }

    public static void reverseWords(String str) {

        String[] string = str.split(" ");

        StringBuilder result = new StringBuilder();
        for (int i = string.length; i > 0; i--) {
            String currentWord = string[i - 1];

            result.append(currentWord + " ");
        }
        System.out.println(result);
    }
}
