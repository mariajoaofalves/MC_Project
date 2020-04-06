package pt.ulisboa.tecnico.sise.insure.app.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

public class NewClaimActivity extends AppCompatActivity {


    public final static String LOG_TAG = "SISE - NewClaim";
    private TextView resultView;
    private Button buttonCreate;
    private Button buttonBack;
    EditText editTextTitle;
    EditText editTextPlate;
    EditText editTextDate;
    EditText editTextDesc;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_claim);

        buttonCreate = (Button) findViewById(R.id.new_claim_btn_create);
        editTextTitle = (EditText) findViewById(R.id.new_claim_title);
        editTextPlate = (EditText) findViewById(R.id.new_claim_plate);
        editTextDate = (EditText) findViewById(R.id.new_claim_date);
        editTextDesc = (EditText) findViewById(R.id.new_claim_desc);
        builder = new AlertDialog.Builder(this);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            //    Bundle bundle = new Bundle();
            public void onClick(View v) {
                Log.d(LOG_TAG, "Button Create debug message");
                Log.d(LOG_TAG, editTextTitle.getText().toString());
                String claimTitle = editTextTitle.getText().toString();
                String claimPlate = editTextPlate.getText().toString();
                String claimDate = editTextDate.getText().toString();
                String claimDesc = editTextDesc.getText().toString();
                // check the title
                if (claimTitle.equals("")) {
                    Toast.makeText(v.getContext(), "Write a claim title", Toast.LENGTH_LONG).show();
                    return;
                }
                // check the plate
                if (claimPlate.equals("")) {
                    Toast.makeText(v.getContext(), "Insert plate number", Toast.LENGTH_LONG).show();
                    return;
                }
                // check the date
                if (claimDate.equals("")) {
                    Toast.makeText(v.getContext(), "Insert occurrence date", Toast.LENGTH_LONG).show();
                    return;
                }
                // check the description
                if (claimDesc.equals("")) {
                    Toast.makeText(v.getContext(), "Write claim description", Toast.LENGTH_LONG).show();
                    return;
                }
                //           bundle.putString("text", claimTitle);
                //           bundle.putString("text", claimPlate);
                //           bundle.putString("text", claimDate);
                //           bundle.putString("text", claimDesc);
                //create dialog box
                builder.setMessage("Your claim was successfully created! We will take care of it as soon as possible" + "\n" + claimTitle )
                        .setCancelable(false)
                        .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'BACK' Button
                                dialog.cancel();
                                Intent intent = new Intent(NewClaimActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("New Insurance Claim Creation");
                alert.show();
            }

        });

        //Logout Button
        final Button btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("Insure", "Logout Button Clicked");
                Toast.makeText(v.getContext(), "Logout Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewClaimActivity.this, AuthenticationActivity.class);
                startActivity(intent);
            }
        });

        //Back Button
        final Button new_claim_btn_back = findViewById(R.id.profile_btn_back);
        new_claim_btn_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("Insure", "Back Button Clicked");
                Intent intent = new Intent(NewClaimActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


    }
}


