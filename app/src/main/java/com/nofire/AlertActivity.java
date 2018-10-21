package com.nofire;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.nofire.Adapter.TwiterAdapter;
import com.nofire.Item.Twit_Item;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

        final String dir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        newdir.mkdirs();
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Here, the counter will be incremented each time, and the
                // picture taken by camera will be stored as 1.jpg,2.jpg
                // and likewise.
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
        });


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
}
