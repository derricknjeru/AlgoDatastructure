package CodingInterviewPatterns.oop_concepts;

public class Polymorphism {
    public static void main(String[] args) {
        //OOP concepts that allow using one word to mean different things in different context
        //Compile time polymorphism i.e  function Overloading (
        // is a concept where a function/method is represented in different form by either having different number of arguments with different types
        // )
        //Runtime polymorphism . It is a process in which a function call to the overridden method is resolved at Runtime.Overriding is a feature that allows a subclass or child class to provide a specific implementation of a method that is already provided by one of its super-classes or parent classes

        RuntimePolymorphismParent a;

        a = new Child1();
        a.print();

        a = new Child2();
        a.print();
    }

}

class CompileTimePolymorphism {
    private int multiply(int a, int b) {
        return a * b;
    }

    private int multiply(int a, int b, int c) {
        a += c;
        return a * b;
    }

}

class CompileTimePolymorphism2 {
    private int multiply(int a, int b) {
        return a * b;
    }

    private double multiply(double a, double b) {
        return a * b;
    }

}


class RuntimePolymorphismParent {
    void print() {

    }
}

class Child1 extends RuntimePolymorphismParent {
    void print() {
        System.out.println("Child1");
    }
}

class Child2 extends RuntimePolymorphismParent {
    void print() {
        System.out.println("Child2");
    }

}
