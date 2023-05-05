package problems.recursion;

public class TowerOfHanoi {
    public static void main(String[] args) {
        toh(3, "A", "B", "c");
    }

    private static void toh(int n, String src, String dest, String help) {
        if(n == 0) {
            return;
        }
        toh(n-1, src, help, dest);
        System.out.println("Move from " + src + " to " + dest);
        toh(n-1, help, dest, src);
    }
}
