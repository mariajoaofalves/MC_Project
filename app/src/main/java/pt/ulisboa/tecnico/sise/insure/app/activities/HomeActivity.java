package pt.ulisboa.tecnico.sise.insure.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import pt.ulisboa.tecnico.sise.insure.app.GlobalState;
import pt.ulisboa.tecnico.sise.insure.app.InternalProtocol;
import pt.ulisboa.tecnico.sise.insure.app.WSLogout;
import pt.ulisboa.tecnico.sise.insure.datamodel.ClaimItem;

public class HomeActivity extends AppCompatActivity {

    public final static String TAG = "HomeActivity";
    private ListView _listView;
    private ArrayList<ClaimItem> _claimList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final GlobalState globalState = (GlobalState) getApplicationContext();

            //Logout Button
        final Button btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d(TAG, "Logout Button Clicked");
                try {
                    new WSLogout(globalState, HomeActivity.this).execute();
                    Toast.makeText(v.getContext(), "Logout Successful!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

            //Profile Button
        final Button profile_button = findViewById(R.id.home_btn_profile);
        profile_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 //Perform action on click
                Log.d(TAG, "Profile Button Clicked");
                Intent intent = new Intent(HomeActivity.this, InsuredProfileActivity.class);
                startActivity(intent);
            }
        });

            //New Insurance Claim Button
        final Button newinsuranceclaim_button = findViewById(R.id.home_btn_newclaim);
        newinsuranceclaim_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d(TAG, "New Insurance Claim Button Clicked");
                Intent intent = new Intent(HomeActivity.this, NewClaimActivity.class);
                startActivity(intent);
            }
        });


            //Insurance Claim History Button
        final Button home_btn_claimhistory = findViewById(R.id.home_btn_claimhistory);
        home_btn_claimhistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Perform action on click
                Log.d(TAG, "Insurance Claim History Button Clicked");
                Intent intent = new Intent(HomeActivity.this, ClaimHistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case InternalProtocol.NEW_CLAIM_REQUEST:

                if (resultCode == Activity.RESULT_OK) {

                    // retrieve the title and body of the note from the intent
                    //Integer claimId = data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_ID);
                    String claimTitle = data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_TITLE);
                    String claimPlate = data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_PLATE);
                    String claimDate = data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_DATE);
                    String claimDescription = data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_DESC);

                    //Log.d(InternalProtocol.LOG, "New Claim:" + claimId + "," + claimTitle);

                    // update the domain data structures
                    //_claimList.add(new ClaimItem(claimId, claimTitle));

                    // refresh the list on screen
                    _listView.setAdapter(new ArrayAdapter<>(this,
                            android.R.layout.simple_list_item_1, android.R.id.text1, _claimList));

                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Log.d(InternalProtocol.LOG, "Cancel pressed.");
                } else {
                    Log.d(InternalProtocol.LOG, "Internal error: unknown result code.");
                }
                break;
            default:
                Log.d(InternalProtocol.LOG, "Internal error: unknown intent message.");
        }
    }
}
