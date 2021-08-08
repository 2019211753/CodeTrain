import java.io.Serializable;
import java.io.*;

class Father {
    public int a = 1;
    void get() {
        System.out.println(a);
    }
}

class Daughters extends Father {
    public int a = 2;
}

class Ex6_17 {
    public static void main(String[] args) throws IOException,ClassNotFoundException {
        Father f = new Daughters();
        f.get();
    }
}