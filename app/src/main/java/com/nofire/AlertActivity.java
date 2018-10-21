package com.nofire;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlertActivity extends AppCompatActivity {

    private LinearLayout captureButton;
    private ImageView ivCaptured;
    private Spinner spnIncidentType;
    private ArrayList<String> incidentTypeList;


    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alertactivity);

        captureButton = this.findViewById(R.id.capture_trigger_layout);
        ivCaptured = this.findViewById(R.id.iv_captured);
        spnIncidentType = this.findViewById(R.id.spn_incident_type);


        captureButton.setOnClickListener(view -> onCaptureCreated());


        incidentTypeList = new ArrayList<>();
        incidentTypeList.add("Residential area fire");
        incidentTypeList.add("Jungle fire");
        incidentTypeList.add("Large smoke cloud");
        incidentTypeList.add("possible fire danger");
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                incidentTypeList);
        spnIncidentType.setAdapter(spinnerCountShoesArrayAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            Log.d("CameraDemo", "Pic saved");
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivCaptured.setImageBitmap(photo);
        }
    }

    private void onCaptureCreated(){
        if (hasPermission())
             capture();
        else
            requestPermission();
    }

    private void requestPermission(){
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        capture();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void capture(){
        final String dir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        newdir.mkdirs();
        count++;
        String file = dir + count + ".jpg";
        File newfile = new File(file);
        try {
            newfile.createNewFile();
        } catch (IOException e) {
        }

        Uri outputFileUri = Uri.fromFile(newfile);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
    }

    private boolean hasPermission(){
        String writePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        String readPermission = Manifest.permission.READ_EXTERNAL_STORAGE;
        int writePermissionResult = ContextCompat.checkSelfPermission( getApplicationContext() , writePermission);
        int readPermissionResult = ContextCompat.checkSelfPermission( getApplicationContext(), readPermission);
        return writePermissionResult == PackageManager.PERMISSION_GRANTED && readPermissionResult == PackageManager.PERMISSION_GRANTED;
    }
}
