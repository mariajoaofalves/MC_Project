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
import pt.ulisboa.tecnico.sise.insure.app.WSClaimDetail;
import pt.ulisboa.tecnico.sise.insure.app.WSLogout;
import pt.ulisboa.tecnico.sise.insure.datamodel.ClaimRecord;

public class ClaimDetailActivity extends AppCompatActivity {

    private static final String TAG = " ClaimDetailActivity";

    TextView viewTextTittle;
    TextView viewTextClaimId;
    TextView viewTextIssuingDate;
    TextView viewTextPlate;
    TextView viewTextClaimDescription;
    TextView viewTextClaimStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_detail);

        final GlobalState globalState = (GlobalState) getApplicationContext();

        viewTextTittle= (TextView)findViewById(R.id.claim_detailed_title);
        viewTextClaimId = (TextView)findViewById(R.id.claim_id);
        viewTextIssuingDate = (TextView)findViewById(R.id.issuing_date);
        viewTextPlate = (TextView)findViewById(R.id.plate);
        viewTextClaimDescription = (TextView)findViewById(R.id.claim_description);
        viewTextClaimStatus = (TextView)findViewById(R.id.claim_status);

        new WSClaimDetail(globalState,ClaimDetailActivity.this).execute();

        //Logout Button
        final Button btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d(TAG, "Logout Button Clicked");
                try {
                    new WSLogout(globalState, ClaimDetailActivity.this).execute();
                    Toast.makeText(v.getContext(), "Logout Successful!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //Back Button
        final Button  detailed_inf_back = findViewById(R.id.detailed_inf_back);
        detailed_inf_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("Insure", "Back Button Clicked");
                Intent intent = new Intent(ClaimDetailActivity.this, ClaimHistoryActivity.class);
                startActivity(intent);
            }
        });
    }
    public void updateClaimDetail(ClaimRecord claimRecord){
        viewTextTittle.setText(getString(R.string.claim_title) + claimRecord.getTitle());
        viewTextClaimId.setText(getString(R.string.claim_id) + String.valueOf(claimRecord.getId()));
        viewTextIssuingDate.setText(getString(R.string.claim_occurrence_date) + claimRecord.getOccurrenceDate());
        viewTextPlate.setText(getString(R.string.claim_plate) + claimRecord.getPlate());
        viewTextClaimDescription.setText(getString(R.string.claim_desc) + claimRecord.getDescription());
        viewTextClaimStatus.setText(getString(R.string.claim_status) + claimRecord.getStatus());
    }
}