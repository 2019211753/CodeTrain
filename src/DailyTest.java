import org.junit.Test;

public class DailyTest extends Father {

    private void aaa()
    {
        System.out.println(3);
    }

    @Override
    public void aaaa() {
        aaa();
    }

    public static void main(String[] args) {
            int a = 1;
            switch(a) {
                case 2:
                    System.out.println("he");
            }

    }
}

class Father {
    private void aaa()
    {
        System.out.println(2);
    }

    public void aaaa()
    {
        aaa();
    }

}