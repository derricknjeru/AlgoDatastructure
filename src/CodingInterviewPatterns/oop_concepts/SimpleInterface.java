package CodingInterviewPatterns.oop_concepts;

public class SimpleInterface {
    public static void main(String[] args) {
        //communication between processes.
        // It is a form of communication used in object-oriented programming as well as parallel programming

        // Java program to demonstrate working of

        TestClass t = new TestClass();
        t.display();
    }

    // A simple interface
    interface In1 {
        // public, static and final
        int a = 10;

        // public and abstract
        void display();
    }

    // A class that implements the interface.
    static class TestClass implements In1 {
        // Implementing the capabilities of
        // interface.
        public void display() {
            System.out.println("Geek");
            System.out.println(a);
        }

    }


}
