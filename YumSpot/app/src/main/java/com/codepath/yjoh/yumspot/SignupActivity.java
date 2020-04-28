package com.codepath.yjoh.yumspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {
    public static final String TAG ="SignupActivity";
    ImageButton btBack;
    Button btSign;
    EditText etUserName;
    EditText etPassword;
    EditText etName;
    EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btBack = findViewById(R.id.btBack);
        btSign = findViewById(R.id.btSign);
        etUserName = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                user.setUsername(etUserName.getText().toString());
                user.setPassword(etPassword.getText().toString());
                user.setEmail(etEmail.getText().toString());
                user.put("Name", etName.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            signup();
                        }
                        else{
                            Log.e(TAG, "Onclicked", e);
                            Toast.makeText(SignupActivity.this, "Something Wrong with SignUp", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void signup(){
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(SignupActivity.this, "Successfully Signed up", Toast.LENGTH_SHORT).show();
        finish();
    }
}
