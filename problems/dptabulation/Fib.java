package problems.dptabulation;

public class Fib {
        public static void main(String[] args) {
            int n = 6;
            System.out.println(fib(n));
        }

        private static long fib(int n) {
            long[] fibs = new long[n+1];
            fibs[1] = 1;
            for(int i=0; i<n; i++) {
                fibs[i+1] += fibs[i];
                if(i != n - 1) {
                    fibs[i+2] += fibs[i];
                }
            }

            return fibs[n];
        }
}
