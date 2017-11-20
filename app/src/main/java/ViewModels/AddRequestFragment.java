package ViewModels;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import Adapters.HorseFilterAdapter;
import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.Kon;
import Models.Pracownik;
import Models.VetRequest;
import Models.VetRequestSEND;
import ViewModels.VetRequestViewModels;
import pl.edu.s12898pjwstk.sidosmobile.R;

import static android.R.attr.data;

/**
 * Created by Dominik Deja on 28.05.2017.
 */

public class AddRequestFragment extends Fragment{
    ProgressDialog progressDialog;
    ArrayList<Pracownik> pracownicy;
    ArrayList<Kon> konie;
    private Boolean photoIn = false;
    int KonID = 0;
    String Picture = "";
    ImageView imgUploadFile;
    String WeterynarzID = "";
    Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
    View myView;
    GestureDetector gestureDetector;
    boolean tapped;
    public static final int GET_FROM_GALLERY = 3;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), selectedImage);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();
                Picture = Base64.encodeToString(byteArray, Base64.DEFAULT);
                System.out.println("Hello");
                BitmapDrawable bitmapConvertedPhoto = new BitmapDrawable(getResources(), bitmap);
                imgUploadFile.setImageDrawable(null);
                imgUploadFile.setImageDrawable(bitmapConvertedPhoto);
                photoIn = true;
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        progressDialog = ProgressDialog.show(getActivity(), "",
                "Loading. Please wait...", true);
        myView = inflater.inflate(R.layout.requestaddfragment, container, false);
        gestureDetector = new GestureDetector(getContext(), new GestureListener());
        imgUploadFile = (ImageView) myView.findViewById(R.id.uploadFileRequest);
        imgUploadFile.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return gestureDetector.onTouchEvent(event);
            }

        });

        //Button uploadPHotoBTN = (Button) myView.findViewById(R.id.UploadPhotoButton);

       /* uploadPHotoBTN.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);


            }
            });  */

        Button btn = (Button) myView.findViewById(R.id.addRequest_button);
        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try {
                    progressDialog = ProgressDialog.show(getActivity(), "",
                            "Loading. Please wait...", true);
                    // Kon kn = (Kon)((Spinner) myView.findViewById(R.id.AutoTextKon)).getSelectedItem();

                    //Pracownik emp = (Pracownik) ((Spinner) myView.findViewById(R.id.Vet_Spinner)).getSelectedItem();
                    String injuryLocation = ((EditText) myView.findViewById(R.id.Injuryrequest_edit)).getText().toString();
                    String priority = ((Spinner) myView.findViewById(R.id.priority_spinnerLay)).getSelectedItem().toString();
                    String description = ((EditText) myView.findViewById(R.id.description_edit)).getText().toString();
                    if (KonID != 0 && !WeterynarzID.equals("")) {
                        VetRequestSEND vt = new VetRequestSEND(description, injuryLocation, priority,KonID, WeterynarzID,VetRequest.HealthProblemStatus.Received.toString(),Picture);
                        //Type listType = new TypeToken<VetRequest>(){}.getType();
                        String body = gSon.toJson(vt, VetRequestSEND.class);
                        System.out.println(body);
                        new AsyncCreateVetRequest(Utils.VetRequestAPI,body).execute();
                    } else{
                        Toast.makeText(getActivity(), "Prosze wybrac Weterynarza i Konia!",
                                Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                new AsyncGetRequest(myView.getContext(),(AutoCompleteTextView) myView.findViewById(R.id.AutoTextKon), (AutoCompleteTextView) myView.findViewById(R.id.VetTextComplex),
                        (Spinner) myView.findViewById(R.id.priority_spinnerLay)).execute();
            }
        });





        return myView;
    }


    public class AsyncGetRequest extends AsyncTask<Void,Void,String>{
        AutoCompleteTextView horse_auto;
        AutoCompleteTextView vet_auto;
        Spinner priority_spinner;
        //Spinner status_spinner;
        Context cont;
        ArrayAdapter<Pracownik> vet_adapter;
        ArrayAdapter<VetRequest.HealthProblemPriority> priority_adapter;
        //ArrayAdapter<VetRequest.HealthProblemStatus> status_adapter;
        ArrayAdapter<Kon> horse_adapter;

        public AsyncGetRequest(Context _cont,AutoCompleteTextView _horse_auto,AutoCompleteTextView _vet_auto,Spinner _priority_spinner){
            horse_auto = _horse_auto;
            vet_auto = _vet_auto;
            priority_spinner = _priority_spinner;
            cont = _cont;
            //status_spinner = _status_spinner;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            vet_auto.setAdapter(vet_adapter);
            vet_auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Pracownik prac = (Pracownik) parent.getItemAtPosition(position);
                    WeterynarzID = prac.getId();
                }
            });
            horse_auto.setAdapter(horse_adapter);
            horse_auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Kon kn = (Kon) parent.getItemAtPosition(position);
                    KonID = kn.getId();
                }
            });
            priority_spinner.setAdapter(priority_adapter);

            progressDialog.dismiss();
            super.onPostExecute(s);
            //Looper.loop();
        }

        @Override
        protected String doInBackground(Void... params) {
         //   Looper.prepare();
            Type listType = new TypeToken<ArrayList<Pracownik>>(){}.getType();

            try {
                // JSONArray arrayHorseList = new JSONArray(HelperMethods.sendGet(URLHorseWebServie));

                pracownicy = gSon.fromJson(HelperMethods.sendGet(Utils.EmployeesAPI), listType);
                listType = new TypeToken<ArrayList<Kon>>(){}.getType();
                konie = gSon.fromJson(HelperMethods.sendGet(Utils.HorseAPI), listType);
                vet_adapter = new ArrayAdapter<Pracownik>(cont,
                        android.R.layout.simple_spinner_dropdown_item, pracownicy);

               // horse_adapter = new HorseFilterAdapter(getActivity(),R.layout.row_horse, R.id.lbl_name, konie);

                horse_adapter = new ArrayAdapter<Kon>(cont,
                        android.R.layout.simple_spinner_dropdown_item, konie);
                priority_adapter = new ArrayAdapter<VetRequest.HealthProblemPriority>(cont,android.R.layout.simple_spinner_dropdown_item, VetRequest.HealthProblemPriority.values());
                //status_adapter = new ArrayAdapter<VetRequest.HealthProblemStatus>(cont,android.R.layout.simple_spinner_dropdown_item, VetRequest.HealthProblemStatus.values());


            } catch (Exception e) {
                e.printStackTrace();
            }

            return "Done";
        }
    }
    public class AsyncCreateVetRequest extends AsyncTask<Void,Void,String>{
        private boolean err = false;
        private String url;
        private String body;

        public AsyncCreateVetRequest(String _url,String _body){
            url = _url;
            body = _body;
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            if(err) {
                HelperMethods.CreateErrorAlert(getActivity(), "Error", "Cant add Vet request");
            }else{
                HelperMethods.CreateInfoAlert(getActivity(), "Done", "Vet request was added");
                getActivity().finish();
            }
                //getActivity().finish();
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                HelperMethods.sendPost(url,body,getActivity());
            } catch (Exception e) {
                err = true;
                e.printStackTrace();
            }
            return "Done";
        }
    }


    public class GestureListener extends
            GestureDetector.SimpleOnGestureListener {


        @Override
        public boolean onDown(MotionEvent e) {

            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if(!photoIn){
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
                photoIn = true;
            }

            return super.onSingleTapUp(e);
        }

        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {

            if(photoIn) {
                imgUploadFile.setImageDrawable(null);
                Picture = "";

                imgUploadFile.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_camera, null));
                photoIn = false;
            }
                return true;
        }
    }
}
