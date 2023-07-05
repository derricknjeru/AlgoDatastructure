package CodingInterviewPatterns;

public class StringsInava {
    public static void main(String[] args) {
        //1. String literal
        String s = "GeeksforGeeks";

        //2.Using new keyword
        String s1 = new String("example");
        // creating Java string by new keyword
        // this statement create two object i.e
        // first the object is created in heap
        // memory area and second the object is
        // created in String constant pool.

        System.out.println(s1);

        String s2 = "Sachin";

        // concat() method appends
        // the string at the end
        //Sachin is not changed but a new object is created with “Sachin Tendulkar”. That is why a string is known as immutable.
        s2.concat(" Tendulkar");


        // This will print Sachin
        // because strings are
        // immutable objects
        System.out.println(s2);

        //can be fixed like this
        String s3 = "Sachin";
        s3 = s3.concat(" Tendulkar");
        System.out.println(s3);
    }
}
