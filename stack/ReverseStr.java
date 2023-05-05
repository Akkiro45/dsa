package stack;

public class ReverseStr {
    
    int size;
    int top = -1;
    String[] stack;

    public ReverseStr() {
        this.size = 0;
        this.stack = new String[size];
    }

    public ReverseStr(int size) {
        this.size = size;
        this.stack = new String[size];
    }

    public void push(String word) {
        if(top == size - 1) {
            System.out.println("Stack overflow!");
            return;
        }
        stack[++top] = word;
    }

    public String pop() {
        if(top == -1) {
            System.out.println("Stack underflow!");
            return null;
        }
        return stack[top--];
    }

    public static void main(String[] args) {
        String str = "How are you!";
        String[] words = str.split(" ");
        ReverseStr s = new ReverseStr(words.length);
        
        for(int i=0; i<words.length; i++) {
            s.push(words[i]);
        }
        str = "";
        for(int i=0; i<words.length; i++) {
            str += " " + s.pop();
        }
        str = str.trim();
        System.out.println(str);
    }

}
