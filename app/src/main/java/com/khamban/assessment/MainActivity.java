package com.khamban.assessment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void login(View view){
        Intent home = new Intent(getApplicationContext(),Login_complete.class);
        EditText User = (EditText) findViewById(R.id.User);
        EditText Pass = (EditText) findViewById(R.id.Pass);
        String Username = User.getText().toString();
        String Password =  Pass.getText().toString();
        if(!Username.equals("kontee") && !Password.equals("kontee")){
            Toast.makeText(MainActivity.this, "Username หรือ Password ไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
        }else {
            home.putExtra("Username", Username);
            home.putExtra("Password", Password);
            startActivity(home);
        }
    }
}
