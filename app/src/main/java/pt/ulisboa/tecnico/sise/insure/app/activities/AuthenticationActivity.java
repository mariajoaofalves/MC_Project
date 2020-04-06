package pt.ulisboa.tecnico.sise.insure.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AuthenticationActivity extends AppCompatActivity {
    public final static String TAG = "Insure_AuthenticationActivity";
    private TextView resultView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

            //Login Button
        final Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("Insure", "Loggin Button Clicked");
                Toast.makeText(btn_login.getContext(), "Login Successful!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AuthenticationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

}
