package week3.domain;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        MyApplication application = new MyApplication();
        System.out.println("An application is about to start..");
        System.out.println("Welcome to my application!");
        application.start();
        //String data = "Almaty is the largest city in Kazakhstan";
        //Files.write(Paths.get("D:\\JAVA\\week2.2.0\\src\\com\\company\\db.txt"), data.getBytes());
    }
}
