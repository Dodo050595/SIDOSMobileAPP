package HelperClasses;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.renderscript.ScriptGroup;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;

import Models.UserTokens;

/**
 * Created by Dominik Deja on 07.05.2017.
 */

public class HelperMethods {
    public static Date getDateString(String dateString){
        Date dt = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = sdf.parse(dateString);
            dt = d;
        } catch (ParseException ex) {
           ex.printStackTrace();
        }
        return dt;
    }

    public static String getStringFromDate(Date dt){
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(dt != null) {
            result = sdf.format(dt);
        }
        return result;
    }

    public static String getCurrentDateString(){
        Date currentTime = Calendar.getInstance().getTime();

        String dt = getStringFromDate(currentTime);

        return dt;
    }

    public static Date AddDays(Date dt, int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        dt = calendar.getTime();

        return dt;
    }

    public static Date getCurrentDate(){
        Date currentTime = Calendar.getInstance().getTime();

        Date dt = getDateString(getStringFromDate(currentTime));

        return dt;
    }

    public static String sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestProperty("Content-Type", "application/json;odata=verbose");
        con.setRequestProperty("Accept", "application/json;odata=verbose");
        if(Utils.UserTokenCls != null) {
            con.setRequestProperty("Authorization", Utils.UserTokenCls.getToken_type() + " " + Utils.UserTokenCls.getAccess_token());
        }
        // optional default is GET
        con.setRequestMethod("GET");


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        //System.out.println(response.toString());
        return response.toString();
    }
    public static void sendPost(String url,String parameters,Activity act) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json");
        if(Utils.UserTokenCls != null) {
            con.setRequestProperty("Authorization", Utils.UserTokenCls.getToken_type() + " " + Utils.UserTokenCls.getAccess_token());
        }

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(parameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + parameters);
        System.out.println("Response Code : " + responseCode);


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        if(responseCode >= 400 && responseCode < 600) {
            throw new Exception();
        }

    }
    public static void CreateInfoAlert(Activity act,String title,String body){
        AlertDialog alertDialog = new AlertDialog.Builder(act).create();
        alertDialog.setTitle(title);
        alertDialog.setIcon(android.R.drawable.ic_input_add);
        alertDialog.setMessage(body);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
    public static void CreateErrorAlert(Activity act,String title,String body){
        AlertDialog alertDialog = new AlertDialog.Builder(act).create();
        alertDialog.setTitle(title);
        alertDialog.setIcon(android.R.drawable.ic_delete);
        alertDialog.setMessage(body);
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

    public static UserTokens sendPostToken(String username, String password) throws Exception {

        String data = URLEncoder.encode("username", "UTF-8")
                + "=" + URLEncoder.encode(username, "UTF-8");

        data += "&" + URLEncoder.encode("password", "UTF-8") + "="
                + URLEncoder.encode(password, "UTF-8");
        data += "&" + URLEncoder.encode("grant_type", "UTF-8") + "="
                + URLEncoder.encode("password", "UTF-8");

        URL obj = new URL(Utils.TokenUUserAPI);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "x-www-form-urlencoded");
        //con.setRequestProperty("Content-Type", "application/json");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        if(responseCode >= 400 && responseCode < 600) {
            return null;
        }
        String a = con.getResponseMessage();
        InputStream b = con.getInputStream();
        String c = b.toString();
        System.out.println("Post parameters : " + data);
        System.out.println("Response Code : " + responseCode);


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        Gson gSon=  new GsonBuilder().create();
        UserTokens usTok = gSon.fromJson(response.toString(), UserTokens.class);

         return usTok;
    }

    public static DatabaseHelper getDatabaseObject(Context cont){
        DatabaseHelper db = new DatabaseHelper(cont);
        return db;

    }

    public static String decryptPassword(String data){

        try {
            SecretKeySpec key = generateKey(Utils.MySecretKeyPasssword);
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE,key);
            byte[] decodeValue = Base64.decode(data,Base64.DEFAULT);
            byte[] deValue = c.doFinal(decodeValue);
            String dcVal = new String(deValue);
            return dcVal;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String encryotPassword(String data){

        try {
            SecretKeySpec key = generateKey(Utils.MySecretKeyPasssword);
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE,key);
            byte[] encVal = c.doFinal(data.getBytes());
            String encryptedValue = Base64.encodeToString(encVal,Base64.DEFAULT);
            return encryptedValue;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static SecretKeySpec generateKey(String password) {

        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = password.getBytes("UTF-8");
            digest.update(bytes,0,bytes.length);
            byte[] key = digest.digest();
            SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
            return secretKeySpec;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
