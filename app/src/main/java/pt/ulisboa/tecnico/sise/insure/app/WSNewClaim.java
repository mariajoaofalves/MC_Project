package pt.ulisboa.tecnico.sise.insure.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import pt.ulisboa.tecnico.sise.insure.app.activities.ClaimHistoryActivity;
import pt.ulisboa.tecnico.sise.insure.app.activities.NewClaimActivity;

public class WSNewClaim extends AsyncTask<String, String, Boolean> {

    String _claimTitle;
    String _occurrenceDate;
    String _plate;
    String _claimDescription;
    GlobalState _globalState;
    NewClaimActivity _newClaim;
    AlertDialog.Builder builder;

    public WSNewClaim(String claimTitle, String occurrenceDate, String plate, String claimDescription, GlobalState globalState, NewClaimActivity newClaim){
        _claimTitle = claimTitle;
        _occurrenceDate = occurrenceDate;
        _plate = plate;
        _claimDescription = claimDescription;
        _globalState = globalState;
        _newClaim = newClaim;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        int sessionId = _globalState.getSessionId();
        boolean res = false;
        try {
            res = WSHelper.submitNewClaim(sessionId, _claimTitle, _occurrenceDate, _plate, _claimDescription);
            Log.d("ClaimCreation", "ClaimCreation result => " + res);

        } catch (Exception e) {
            Log.d("ClaimCreation", e.toString());
        }
        return res;
    }

    @Override
    protected void onPostExecute(Boolean res) {
        Log.d("ClaimCreation", "doInBackground res => " + res);
        if(res != null && res.equals(true)){
            Log.d("ClaimCreation", "entra no if res => " + res);
            builder = new AlertDialog.Builder(_newClaim);

            builder.setMessage("Your claim was successfully created! We will take care of it as soon as possible")
                    .setCancelable(false)
                    .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Intent intent = new Intent(_newClaim, ClaimHistoryActivity.class);
                            Log.d("ClaimCreation", "faz o build => ");
                            _newClaim.startActivity(intent);
                            Log.d("ClaimCreation", "inicia nova activity => ");
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("New Insurance Claim Creation");
            alert.show();

        } else {
            Toast.makeText(_newClaim.getApplicationContext(), "Server Connection Failed!", Toast.LENGTH_SHORT).show();
        }

    }


}

