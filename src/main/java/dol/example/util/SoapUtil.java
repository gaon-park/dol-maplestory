package dol.example.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SoapUtil {
    public String getCharacterInfoByAccountID(Long accountId) {
        String result = "";
        try {
            // send message
            String sendMessage =
                    "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                            "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                            "  <soap12:Body>\n" +
                            "    <GetCharacterInfoByAccountID xmlns=\"https://api.maplestory.nexon.com/soap/\">\n" +
                            "      <AccountID>" + accountId + "</AccountID>\n" +
                            "    </GetCharacterInfoByAccountID>\n" +
                            "  </soap12:Body>\n" +
                            "</soap12:Envelope>";

            URL url = new URL("https://api.maplestory.nexon.com/soap/maplestory.asmx");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");

            // header
            con.addRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
            con.addRequestProperty("SOAPAction", "https://api.maplestory.nexon.com/soap/GetCharacterInfoByAccountID");
            con.addRequestProperty("Content-Length", String.valueOf(sendMessage.getBytes(StandardCharsets.UTF_8).length));

            // body
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(sendMessage);
            writer.flush();

            // response
            String inputLine = null;
            String responseStr = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
                responseStr += inputLine;
            }

            result = responseStr;

            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
