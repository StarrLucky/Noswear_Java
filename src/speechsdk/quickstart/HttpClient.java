package speechsdk.quickstart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpClient {
    private static final int TIMEOUT_MS = 2000;

    public void request(String url) throws Exception {
        URLConnection connection = new URL(url).openConnection();
        // Without timeouts a slow/unreachable board would block the recognizer's event thread.
        connection.setConnectTimeout(TIMEOUT_MS);
        connection.setReadTimeout(TIMEOUT_MS);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
        }
    }
}
