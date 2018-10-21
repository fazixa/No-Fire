package com.nofire;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlertActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout captureButton;
    private ImageView ivCaptured;
    private Spinner spnIncidentType;
    private ArrayList<String> incidentTypeList;
    private File mImageFile;

    private static final int TAKE_PHOTO_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alertactivity);

        captureButton = this.findViewById(R.id.capture_trigger_layout);
        ivCaptured = this.findViewById(R.id.iv_captured);
        spnIncidentType = this.findViewById(R.id.spn_incident_type);


        captureButton.setOnClickListener(view -> onCaptureCreated());


        incidentTypeList = new ArrayList<>();
        incidentTypeList.add("Residential area fire");
        incidentTypeList.add("Jungle fire");
        incidentTypeList.add("Large smoke cloud");
        incidentTypeList.add("Possible fire danger");
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                incidentTypeList);
        spnIncidentType.setAdapter(spinnerCountShoesArrayAdapter);

        findViewById(R.id.alert_submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alert_submit:
                submit();
                break;
        }
    }

    private void submit(){
        Toast.makeText(this, "Report is Submitted", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = (Bitmap) extras.get("data");
                ivCaptured.setImageBitmap(photo);
                try {
                    mImageFile = saveBitmap(photo);
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"Error on saving image",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private File saveBitmap(Bitmap bitmap) throws IOException {
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/PhysicsSketchpad";
        File dir = new File(file_path);
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dir, "sketchpad.png");
        FileOutputStream fOut = new FileOutputStream(file);

        bitmap.compress(Bitmap.CompressFormat.PNG, 85, fOut);
        fOut.flush();
        fOut.close();
        return dir;
    }

    private void onCaptureCreated() {
        if (hasPermission())
            capture();
        else
            requestPermission();
    }

    private void requestPermission() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
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

    private void capture() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
    }

    private boolean hasPermission() {
        String writePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        String readPermission = Manifest.permission.READ_EXTERNAL_STORAGE;
        int writePermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), writePermission);
        int readPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), readPermission);
        return writePermissionResult == PackageManager.PERMISSION_GRANTED && readPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onDestroy() {
        if (captureButton != null) captureButton = null;
        if (ivCaptured != null) ivCaptured = null;
        if (spnIncidentType != null) spnIncidentType = null;
        if (incidentTypeList != null) incidentTypeList = null;
        if (mImageFile != null) mImageFile = null;
        super.onDestroy();
    }
}
