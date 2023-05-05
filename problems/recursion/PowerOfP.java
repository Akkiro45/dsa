package problems.recursion;

public class PowerOfP {
    public static void main(String[] args) {
        System.out.println(pow(2, 9));
    }

    private static int pow(int n, int p) {
        if(p == 1) {
            return n;
        }
        return n * pow(n, p - 1);
    }
}
