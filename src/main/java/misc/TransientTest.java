package misc;

import java.io.*;

// Java program to demonstrate transient keyword
class TransientTest implements Serializable
{
    // Normal variables
    int i = 10, j = 20;

    // Transient variables
    transient int k = 30;

    // Use of transient has no impact here
    static transient int l = 40;
    final transient int m = 50;

    public static void main(String[] args) throws Exception
    {
        TransientTest input = new TransientTest();

        // serialization
        FileOutputStream fos = new FileOutputStream("abc.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(input);
        // is this needed
        oos.flush();
        oos.close();

        // de-serialization
        FileInputStream fis = new FileInputStream("abc.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        TransientTest output = (TransientTest)ois.readObject();
        System.out.println("i = " + output.i);
        System.out.println("j = " + output.j);
        System.out.println("k = " + output.k);
        System.out.println("l = " + output.l);
        System.out.println("m = " + output.m);
        ois.close();
        fis.close();
    }
}
