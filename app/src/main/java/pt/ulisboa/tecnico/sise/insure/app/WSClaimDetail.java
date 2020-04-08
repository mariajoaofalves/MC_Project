package pt.ulisboa.tecnico.sise.insure.app;

import android.os.AsyncTask;
import android.util.Log;

import pt.ulisboa.tecnico.sise.insure.app.activities.ClaimDetailActivity;
import pt.ulisboa.tecnico.sise.insure.datamodel.ClaimItem;
import pt.ulisboa.tecnico.sise.insure.datamodel.ClaimRecord;

public class WSClaimDetail extends AsyncTask<ClaimRecord, String, ClaimRecord> {
    public final static String TAG = "ClaimDetail";
    Integer sessionId;
    GlobalState _globalState;
    ClaimDetailActivity _claimDetailed;
    ClaimItem claimItem;
    ClaimRecord claimRecord;
    Integer claimId;

    public WSClaimDetail(GlobalState globalState,ClaimDetailActivity claimDetailed) {
        _globalState = globalState;
        _claimDetailed = claimDetailed;
    }
    @Override
    protected ClaimRecord doInBackground(ClaimRecord... claimRecords) {
        sessionId= _globalState.getSessionId();
        claimId= _globalState.getClaimItem().getId();
        try {
            claimRecord = WSHelper.getClaimInfo(sessionId, claimId);
            Log.d(TAG, " ClaimRecord =>" + claimRecord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, " ClaimRecord =>" + claimRecord);
        return claimRecord;
    }
    protected void onPostExecute(ClaimRecord claimRecord) {
        Log.d(TAG, " ClaimRecord =>" + claimRecord);
        _globalState.setClaimRecord(claimRecord);
        _claimDetailed.updateClaimDetail(claimRecord);
    }
}