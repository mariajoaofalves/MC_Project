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
import pt.ulisboa.tecnico.sise.insure.datamodel.ClaimItem;

public class ClaimHistoryActivity extends AppCompatActivity {

    private static final String LOG_TAG = "WEB_APP - ClaimHistory";
    private ListView _listView;
    private ArrayList<ClaimItem> _claimList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_history);

        //Logout Button
        final Button btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("Insure", "Logout Button Clicked");
                Toast.makeText(v.getContext(), "Logout Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ClaimHistoryActivity.this, AuthenticationActivity.class);
                startActivity(intent);
            }
        });

        // place the note list in the application domain
        //_claimList = new ArrayList<ClaimItem>();
        GlobalState globalState = (GlobalState) getApplicationContext();
        //List<ClaimItem> claimList =
        globalState.set_claimList(_claimList);

        // assign adapter to list view
        _listView = (ListView) findViewById(R.id.list_claim_list);
        ArrayAdapter<ClaimItem> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, _claimList);
        _listView.setAdapter(adapter);

        // attach click listener to list view items
        _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // create the read note activity, passing to it the index position as parameter
                Log.d("position", position + "");
                Intent intent = new Intent(ClaimHistoryActivity.this, ClaimDetailActivity.class);
                intent.putExtra(InternalProtocol.READ_CLAIM_INDEX, position);
                startActivity(intent);

                // if instead of string, we pass a list with notes, we can retrieve the original Note object this way
                //Note note = (Note)parent.getItemAtPosition(position);
            }
        });

        //Back Button
        final Button claim_hist_btn_back = findViewById(R.id.claim_hist_btn_back);
        claim_hist_btn_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("Insure", "Back Button Clicked");
                Intent intent = new Intent(ClaimHistoryActivity.this, HomeActivity.class);
                startActivity(intent); // (se calhar falta isto) startActivityForResult(intent, InternalProtocol.CLAIM_INFORMATION_REQUEST);
            }
        });


        /*//Detailed Claim Button
        final Button claim_one = findViewById(R.id.claim_one);
        claim_one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("Insure", "Detailed Claim Button Clicked");
                Intent intent = new Intent(ClaimHistoryActivity.this, ClaimDetailActivity.class);
                startActivity(intent);
            }
        });
        */
    }


}
