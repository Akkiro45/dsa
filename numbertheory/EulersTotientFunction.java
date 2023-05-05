package numbertheory;

public class EulersTotientFunction {
    public static void main(String[] args) {
        int n = 10;
        int[] totient = new int[n+1];

        for(int i=0; i<=n; i++) {
            totient[i] = i;
        }
        for(int i=2; i<=n; i++) {
            if(totient[i] == i) {
                for(int j=(i+i); j<=n; j+=i) {
                    totient[j] *= i - 1;
                    totient[j] /= i;
                }
                totient[i] = i - 1;
            }
        }

        for(int i=0; i<=n; i++) {
            System.out.print(totient[i] + " ");
        }
    }
}
