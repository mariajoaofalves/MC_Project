package pt.ulisboa.tecnico.sise.insure.app.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pt.ulisboa.tecnico.sise.insure.app.GlobalState;
import pt.ulisboa.tecnico.sise.insure.app.WSAuthentication;

public class AuthenticationActivity extends AppCompatActivity {

    EditText editTextUsername;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        final Button btn_login = findViewById(R.id.btn_login);

        final GlobalState globalState = (GlobalState) getApplicationContext();

        btn_login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editTextUsername = (EditText) findViewById(R.id.Username);
                editTextPassword = (EditText) findViewById(R.id.Password);
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                Log.d("Authentication", "Loggin Button Clicked");
                new WSAuthentication(username,password, globalState,AuthenticationActivity.this ).execute();
            }
        });

    }


}
