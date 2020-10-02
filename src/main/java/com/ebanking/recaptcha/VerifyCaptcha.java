/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.recaptcha;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Huy Hoang
 */
public class VerifyCaptcha {
    public static final String SITE_VERIFY_URL = //
            "https://www.google.com/recaptcha/api/siteverify";
     
    public static boolean verify (String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
            return false;
        }
        try {
            URL verifyURL = new URL(SITE_VERIFY_URL);
            HttpsURLConnection connection = (HttpsURLConnection) verifyURL.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Chrome/4.0.249.0 Safari/532.5");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            
            String postParams = "secret=" + MyKey.SECRET_KEY + "&response=" + gRecaptchaResponse;
            System.out.println(postParams);
            connection.setDoOutput(true);
            
            OutputStream outStream = connection.getOutputStream();
            outStream.write(postParams.getBytes());
            
            outStream.flush();
            outStream.close();
            
            int responseCode = connection.getResponseCode();
            System.out.println("responseCode=" + responseCode);
            
            InputStream inputStream = connection.getInputStream();
            JsonReader jsonReader = Json.createReader(inputStream);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            
            System.out.println("Response: " + jsonObject);
            boolean success = jsonObject.getBoolean("success");
            return success;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
 
}
