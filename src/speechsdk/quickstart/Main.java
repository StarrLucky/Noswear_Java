package speechsdk.quickstart;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {



        Thr thread = new Thr();
        thread.start();
        System.in.read();
    }
}