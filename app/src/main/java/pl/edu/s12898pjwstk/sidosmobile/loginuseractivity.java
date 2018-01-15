package pl.edu.s12898pjwstk.sidosmobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import HelperClasses.DatabaseHelper;
import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.UserTokens;

public class loginuseractivity extends AppCompatActivity {

    View myView;
    public ProgressDialog progressDialog;
    public DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginuseractivity);

        db = HelperMethods.getDatabaseObject(this.getApplicationContext());
        Utils.UserTokenCls = db.getUserTokenSQL();
        if(Utils.UserTokenCls != null){
            progressDialog = ProgressDialog.show(loginuseractivity.this, "",
                    "Loading. Please wait...", true);
            new AsyncGetUserTokenCheck(progressDialog,db,loginuseractivity.this,loginuseractivity.this.getApplicationContext()).execute();

            return;
        }


        EditText username = (EditText) findViewById(R.id.editText_Username);
        EditText password = (EditText) findViewById(R.id.editText_password);
        username.setSelected(false);
        password.setSelected(false);

        username.setText("wkierownikowski@test.com");
        password.setText("Test123!");

        myView = findViewById(android.R.id.content);

        TextView txGuest = (TextView) findViewById(R.id.txtView_LogAsGuest);
        txGuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(loginuseractivity.this.getApplicationContext(), MainBar.class);
                loginuseractivity.this.startActivity(intent);
                loginuseractivity.this.finish();

            }
        });

        Button btLogIn = (Button) findViewById(R.id.btnuserlogin_zaloguj);
        btLogIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                CheckBox savedataCHK = (CheckBox) findViewById(R.id.CHB_SavePassword);
                EditText username = (EditText) findViewById(R.id.editText_Username);
                EditText password = (EditText) findViewById(R.id.editText_password);

                progressDialog = ProgressDialog.show(loginuseractivity.this, "",
                        "Loading. Please wait...", true);
                new AsyncGetUserToken(username.getText().toString(),password.getText().toString(),savedataCHK.isChecked(),progressDialog,loginuseractivity.this,loginuseractivity.this.getApplicationContext(),db).execute();




            }
        });
    }

    }
class AsyncGetUserToken extends AsyncTask<Void,Void,Void> {

    String username;
    String password;
    ProgressDialog prg;
    Activity act;
    DatabaseHelper db;
    Context cont;
    String Token;
    Boolean saveData;

    public AsyncGetUserToken(String username,String password,Boolean saveData,ProgressDialog prg,Activity act,Context cont,DatabaseHelper db){
        this.prg = prg;
        this.username = username;
        this.password = password;
        this.act = act;
        this.cont = cont;
        this.saveData = saveData;
        this.db = db;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        prg.dismiss();
        if(Utils.UserTokenCls != null){
            Intent intent = new Intent(cont, MainBar.class);
            act.startActivity(intent);
            act.finish();
        }else{
            HelperMethods.CreateErrorAlert(act,"Error","Incorrect Password or Username");
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
           UserTokens Ustk = HelperMethods.sendPostToken(username,password);

                if(saveData && Ustk != null){
                    Ustk.setEncryptedPass(HelperMethods.encryotPassword(password));
                    db.insertData(Ustk);


            }
            Utils.UserTokenCls = Ustk;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}

class AsyncGetUserTokenCheck extends AsyncTask<Void,Void,Void> {

    ProgressDialog pgDialog;
    DatabaseHelper db;
    Activity act;
    Context cont;

    public AsyncGetUserTokenCheck(ProgressDialog pgDialog,DatabaseHelper db,Activity act,Context cont){
        this.pgDialog = pgDialog;
        this.db = db;
        this.act = act;
        this.cont = cont;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        pgDialog.dismiss();
        Intent intent = new Intent(cont, MainBar.class);
        act.startActivity(intent);
        act.finish();
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            String password = HelperMethods.decryptPassword(Utils.UserTokenCls.getEncryptedPass());
            UserTokens ustok = HelperMethods.sendPostToken(Utils.UserTokenCls.getUserName(),password);
            if(ustok != null){
                if(ustok.getAccess_token() != Utils.UserTokenCls.getAccess_token()){
                    db.updateData(ustok.getAccess_token(),Utils.UserTokenCls.getUserName());
                    Utils.UserTokenCls.setAccess_token(ustok.getAccess_token());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
