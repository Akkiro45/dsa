package oops.two;

import oops.one.A;

public class B extends A {
    public static void main(String[] args) {
        B b = new B();
        // System.out.println(b.field1);
        System.out.println(b.field2);
        // System.out.println(b.field3);
        System.out.println(b.field4); 

        A a = new A();
        // System.out.println(a.field1);
        System.out.println(a.field2);
        // System.out.println(a.field3);
        // System.out.println(a.field4); 
    }
}
