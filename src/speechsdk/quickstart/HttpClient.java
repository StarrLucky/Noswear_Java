package speechsdk.quickstart;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Struct;
import java.text.ParseException;
import java.net.*;
import java.io.*;

public class HttpClient {
    public void request(String url) throws Exception {
        URL yahoo = new URL(url);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }


}