package numbertheory;

class Answer {
    int x;
    int y;
    int gcd;
}

public class ExtendedEuclideanAlgorithm {

    public static Answer extendedEuclideanAlgorithm(int a, int b) {
        if(b == 0) {
            Answer ans = new Answer();
            ans.x = 1;
            ans.y = 0;
            ans.gcd = a;
            return ans;
        }
        Answer smallAns = extendedEuclideanAlgorithm(b, a%b);
        Answer ans = new Answer();
        ans.x = smallAns.y;
        ans.gcd = smallAns.gcd;
        ans.y = smallAns.x - (a/b) * smallAns.y;

        return ans;
    }

    public static void main(String[] args) {
        // ax + by = GCD(a, b)
        // a and b is given find x and y
        int a = 30;
        int b = 20;
        Answer ans = extendedEuclideanAlgorithm(a, b);
        System.out.println("x = " + ans.x);
        System.out.println("y = " + ans.y);
        System.out.println("gcd = " + ans.gcd);
        System.out.println();

        a = 35;
        b = 15;
        ans = extendedEuclideanAlgorithm(a, b);
        System.out.println("x = " + ans.x);
        System.out.println("y = " + ans.y);
        System.out.println("gcd = " + ans.gcd);
        System.out.println();
    }
}
