package problems;
public class EuclidGCD {
    public static void main(String[] args) {
        int a = 42;
        int b = 24;
        int rem;

        while(a != 0) {
            rem = b%a;
            b = a;
            a = rem;
        }

        System.out.println(b);
    }
}
