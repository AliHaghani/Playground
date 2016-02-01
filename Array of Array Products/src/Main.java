import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.print("Type in numbers separated by commas:");
        Scanner input = new Scanner(System.in);
        String enteredValue = "" + input.nextLine();

        enteredValue = enteredValue.replaceAll("\\s","");
        String[] arr = enteredValue.split(",");
        int[] results = new int[arr.length];

        for (int i = 0; i < results.length; i++) {
            try {
                results[i] = Integer.parseInt(arr[i]);
            } catch (NumberFormatException nfe) {};
        }

        System.out.println(Arrays.toString(arrayOfArrays(results)));
    }

    public static int[] arrayOfArrays(int[] arr) {
        int[] productsAbove = new int[arr.length];
        int[] productsBelow = new int[arr.length];

        int p = 1;
        for (int i = 0; i < arr.length; i++) {
            productsBelow[i] = p;
            p*=arr[i];
        }

        p = 1;
        for (int i = arr.length-1; i>=0; i--){
            productsAbove[i] = p;
            p*=arr[i];
        }
        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++){
            result[i] = productsAbove[i] * productsBelow[i];
        }
        return result;

    }
}
