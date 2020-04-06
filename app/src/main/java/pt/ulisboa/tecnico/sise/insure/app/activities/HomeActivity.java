package pt.ulisboa.tecnico.sise.insure.app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

            //Logout Button
        final Button btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("Insure", "Logout Button Clicked");
                Toast.makeText(v.getContext(), "Logout Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, AuthenticationActivity.class);
                startActivity(intent);
            }
        });

            //Profile Button
        final Button profile_button = findViewById(R.id.home_btn_profile);
        profile_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 //Perform action on click
                Log.d("Insure", "Profile Button Clicked");
                Intent intent = new Intent(HomeActivity.this, InsuredProfileActivity.class);
                startActivity(intent);
            }
        });

            //New Insurance Claim Button
        final Button newinsuranceclaim_button = findViewById(R.id.home_btn_newclaim);
        newinsuranceclaim_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("Insure", "New Insurance Claim Button Clicked");
                Toast.makeText(newinsuranceclaim_button.getContext(), "Login Successful!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(HomeActivity.this, NewClaimActivity.class);
                startActivity(intent);
            }
        });


            //Insurance Claim History Button
        final Button home_btn_claimhistory = findViewById(R.id.home_btn_claimhistory);
        home_btn_claimhistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Perform action on click
                Log.d("Insure", "Insurance Claim History Button Clicked");
                Intent intent = new Intent(HomeActivity.this, ClaimHistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
