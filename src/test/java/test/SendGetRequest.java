package test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SendGetRequest {
    public static void main(String[] args) {
        String url = "http://localhost:8080/news/random";
        int count =2;

        for(int i = 0; i < count; i++) {
            try {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("Response code: " + responseCode);
                System.out.println("Response body: " + response.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
