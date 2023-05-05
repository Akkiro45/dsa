package numbertheory;

public class BinaryExponentiation {
    public static void main(String[] args) {
        int x = 2;
        int y = 5;
        long ans = 1;
        while(y > 0) {
            if((y & 1) == 1) { // y%2 != 0
                // odd number
                ans *= x;
            }
            y = y >> 1; // y/2
            x *= x;
        }
        System.out.println(ans);
    }
}
