package pt.ulisboa.tecnico.sise.insure.app;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import pt.ulisboa.tecnico.sise.insure.app.activities.ClaimHistoryActivity;
import pt.ulisboa.tecnico.sise.insure.app.activities.NewClaimActivity;
import pt.ulisboa.tecnico.sise.insure.datamodel.ClaimItem;



public class WSListPlates extends AsyncTask<Integer, String, Iterable<String>> {

    public final static String TAG = "ListPlates";

        Integer sessionId;
        GlobalState _globalState;
        NewClaimActivity _newClaim;

    public WSListPlates(GlobalState globalState, NewClaimActivity newClaim) {
        _globalState = globalState;
        _newClaim = newClaim;
    }

    @Override
    protected Iterable<String>  doInBackground(Integer... integers) {
        Iterable<String> plateList = null;
        sessionId = _globalState.getSessionId();
        Log.d(TAG, " SessionId =>" + sessionId);
        try {
            plateList = WSHelper.listPlates(sessionId);
            Log.d(TAG, " PlateList =>" + plateList);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return plateList;
    }

    @Override
    protected void onPostExecute(Iterable<String> plateList) {
        _globalState.setPlateList(plateList);
        _newClaim.UpdateListPlates(plateList);
    }

}
