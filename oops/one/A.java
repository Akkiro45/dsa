package oops.one;

class SupperClass {
    String field1 = "test11";
    String field8 = "test4";
    public String field5 = "test5"; 
    private String field6 = "test6"; 
    protected String field7 = "test7";
}

class D {
    private D() {

    }
}

abstract class C {
    int var1 = 1;

    public C() {
        System.out.println("C");
    }

    abstract void method1();
    void method2() {
        System.out.println("Nothing");
    }

    void method3() {
        System.out.println("Nothing");
    }

    protected class E {
        int var2 = 2;
    }
}

interface F {
    public final int f = 100;
    public abstract void method4();
    static void method5() {
        System.out.println("Static");
    } 

    default void method6() {
        System.out.println("Static");
    } 
}

public class A extends C implements F {
    String field1 = "test1";
    public String field2 = "test2"; 
    private String field3 = "test3"; 
    protected String field4 = "test4";

    public A() {
        System.out.println("A");
    }

    public String getField() {
        return field3;
    }

    void method3() {
        super.method3();
        System.out.println("NothingA");
    }

    public void method4() {

    }

    public void method6() {

    }

    void method1() {
        E e = new E();
    }

    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.field1);
        System.out.println(a.field2);
        System.out.println(a.field3);
        System.out.println(a.field4); 

        a.method3();
        
        SupperClass supperClass = new SupperClass();
        System.out.println(supperClass.field5);
        // System.out.println(supperClass.field6);
        System.out.println(supperClass.field7);
        System.out.println(supperClass.field8);

        F.method5();
    }
}


