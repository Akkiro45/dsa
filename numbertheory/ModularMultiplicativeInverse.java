package numbertheory;

public class ModularMultiplicativeInverse {
    
    public static void main(String[] args) {
        // (a * b) % m = 1
        // b = m
        // ax * my = 1
        int a = 3;
        int m = 11;
        Answer ans = ExtendedEuclideanAlgorithm.extendedEuclideanAlgorithm(a, m);
        System.out.println("x(b) = " + ans.x);
        System.out.println("y = " + ans.y);
        System.out.println("gcd = " + ans.gcd);
        // To prove
        System.out.println((a * ans.x) % m);
    }
}
