package string;

public class RabinKarpAlgo {
    public static void main(String[] args) {
        String txt = "This is test text from test";
        String pattern = "test";

        int q = 101;
        int h = 1;
        int pHash = 0;
        int txtHash = 0;
        int d = 256;
        int n = txt.length();
        int m = pattern.length();
        boolean flag;

        for(int i=0; i<m-1; i++) {
            h = (h*d) % q;
        }
        for(int i=0; i<m; i++) {
            pHash = (pHash*d + pattern.charAt(i)) % q;
            txtHash = (txtHash*d + txt.charAt(i)) % q;
        }
        for(int i=0; i<n-m+1; i++) {
            if(pHash == txtHash) {
                flag = true;
                for(int j=0; j<m; j++) {
                    if(pattern.charAt(j) != txt.charAt(i+j)) {
                        flag = false;
                    }
                }
                if(flag == true) {
                    System.out.println("Found at : " + i);
                }
            }
            if(i < n-m) {
                txtHash = (((txtHash - txt.charAt(i) * h) * d) + txt.charAt(i+m)) % q;
                if(txtHash < 0) {
                    txtHash = txtHash + q;
                }
            }
        }
    }
}
