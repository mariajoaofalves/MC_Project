package pt.ulisboa.tecnico.sise.insure.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pt.ulisboa.tecnico.sise.insure.app.GlobalState;
import pt.ulisboa.tecnico.sise.insure.app.InternalProtocol;
import pt.ulisboa.tecnico.sise.insure.app.WSClaimHistory;
import pt.ulisboa.tecnico.sise.insure.app.WSLogout;
import pt.ulisboa.tecnico.sise.insure.datamodel.ClaimItem;

public class ClaimHistoryActivity extends AppCompatActivity {
    private static final String TAG = " ClaimHistory";
    private ListView listView;
    private ArrayList<ClaimItem> claimList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_history);

        final GlobalState globalState = (GlobalState) getApplicationContext();

        claimList = new ArrayList<ClaimItem>();
        listView = (ListView) findViewById(R.id.list_claim_list);
        new WSClaimHistory( globalState, ClaimHistoryActivity.this).execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, position + "");
                Intent intent = new Intent(ClaimHistoryActivity.this, ClaimDetailActivity.class);
                intent.putExtra(InternalProtocol.READ_CLAIM_INDEX, position);
                startActivity(intent);
                ClaimItem claimItem = (ClaimItem) parent.getItemAtPosition(position);
                globalState.setClaimItem(claimItem);
                Log.d(TAG, "claimItem =>" + claimItem);
            }
        });
        //Back Button
        final Button claim_hist_btn_back = findViewById(R.id.claim_hist_btn_back);
        claim_hist_btn_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d(TAG, "Back Button Clicked");
                Intent intent = new Intent(ClaimHistoryActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        //Logout Button
        final Button btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d(TAG, "Logout Button Clicked");
                try {
                    new WSLogout(globalState, ClaimHistoryActivity.this).execute();
                    Toast.makeText(v.getContext(), "Logout Successful!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void UpdateListClaims(List<ClaimItem> claimItemList){
        ArrayAdapter<ClaimItem> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, claimItemList);
        listView.setAdapter(adapter);
    }

}