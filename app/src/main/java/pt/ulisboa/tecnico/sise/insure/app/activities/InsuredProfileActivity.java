package pt.ulisboa.tecnico.sise.insure.app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InsuredProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insured_profile);

        //Logout Button
    final Button btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            // Perform action on click
            Log.d("Insure", "Logout Button Clicked");
            Toast.makeText(v.getContext(), "Logout Successful!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(InsuredProfileActivity.this, AuthenticationActivity.class);
            startActivity(intent);
        }
    });

        //Back Button
        final Button profile_btn_back = findViewById(R.id.profile_btn_back);
        profile_btn_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("Insure", "Back Button Clicked");
                Intent intent = new Intent(InsuredProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
