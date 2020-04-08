package pt.ulisboa.tecnico.sise.insure.app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pt.ulisboa.tecnico.sise.insure.app.GlobalState;
import pt.ulisboa.tecnico.sise.insure.app.WSInsuredProfile;
import pt.ulisboa.tecnico.sise.insure.datamodel.Customer;

public class InsuredProfileActivity extends AppCompatActivity {
    TextView viewTextName;
    TextView viewTextAdress;
    TextView viewTextBirthdate;
    TextView viewTextFiscalNumber;
    TextView viewTextPolicyNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insured_profile);
        final GlobalState globalState = (GlobalState) getApplicationContext();
        viewTextName = (TextView)findViewById(R.id.name);
        viewTextAdress = (TextView)findViewById(R.id.adress);
        viewTextBirthdate = (TextView)findViewById(R.id.birthdate);
        viewTextFiscalNumber = (TextView)findViewById(R.id.nif);
        viewTextPolicyNumber = (TextView)findViewById(R.id.policynumber);
        new WSInsuredProfile( globalState, InsuredProfileActivity.this).execute();
        //Logout Button
        final Button btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("Profile", "Logout Button Clicked");
            }
        });
        //Back Button
        final Button profile_btn_back = findViewById(R.id.profile_btn_back);
        profile_btn_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("Profile", "Back Button Clicked");
                Intent intent = new Intent(InsuredProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
    public void updateInterface(Customer customer){
        viewTextName.setText( "Name: " + customer.getName());
        viewTextAdress.setText( "Adress: " + customer.getAddress());
        viewTextBirthdate.setText("Birthdate: "+ customer.getDateOfBirth());
        viewTextFiscalNumber.setText( "Fiscal Number: "+ String.valueOf(customer.getFiscalNumber()));
        viewTextPolicyNumber.setText( "Policy Number: " + String.valueOf(customer.getPolicyNumber()));
    }
}
