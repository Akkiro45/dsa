package problems;
public class NumberOfDivisible {
    /*
        We need to find how many numbers between 1 and n are divisible by a or b.
        Here we will inclusive and exclusive method
    */
    public static void main(String[] args) {
        int n = 40;
        int a = 5;
        int b = 7;

        int c1 = n / a; // To find how many numbers between 1 and n are divisible by a
        int c2 = n / b; // To find how many numbers between 1 and n are divisible by b
        int c3 = n / (a * b); // To find how many numbers between 1 and n are divisible by a and b
        
        int res = c1 + c2 - c3;
        System.out.println(res); 
    }
}
