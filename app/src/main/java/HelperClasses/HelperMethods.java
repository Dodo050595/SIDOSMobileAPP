package HelperClasses;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

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
        result = sdf.format(dt);
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
}
