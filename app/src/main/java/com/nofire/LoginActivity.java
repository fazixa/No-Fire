package com.nofire;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private EditText mEditText;
    private GifImageView gifImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = getApplicationContext();

        if (!hasPhoneNumber()) {
            gifImageView = findViewById(R.id.login_back_image);
            gifImageView.setFreezesAnimation(false);

            findViewById(R.id.login_submit).setOnClickListener(this);

            mEditText = findViewById(R.id.login_edit_text);
        } else {
            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_submit:
                submit();
                break;
        }
    }

    private void submit(){
        if (mEditText.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Enter phone number",Toast.LENGTH_SHORT).show();
            return;
        }
        addPhoneNumber(mEditText.getText().toString().trim());
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private void addPhoneNumber(String phoneNumber){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("phoneNumber",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone",phoneNumber);
        editor.apply();
    }

    private boolean hasPhoneNumber(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("phoneNumber",MODE_PRIVATE);
        return sharedPreferences.contains("phone");
    }
}
