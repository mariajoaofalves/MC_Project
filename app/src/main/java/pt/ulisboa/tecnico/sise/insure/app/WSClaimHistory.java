package pt.ulisboa.tecnico.sise.insure.app;

import android.os.AsyncTask;
import android.util.Log;


import java.util.List;

import pt.ulisboa.tecnico.sise.insure.datamodel.ClaimItem;

public class WSClaimHistory extends AsyncTask<Integer, String, List<ClaimItem>> {
    public final static String TAG = "ClaimHistory";
    private int _sessionId;

    public WSClaimHistory(GlobalState.getSessionId()) {
        _sessionId = GlobalState.getSessionId();
    }

    @Override
    protected List<ClaimItem> doInBackground(Integer... integers) {
        /*
         * Test method call invocation: listClaims
         */
        publishProgress("Testing method call listClaims...");
        try {
            List<ClaimItem> claimItemList = WSHelper.listClaims(_sessionId);
            if (claimItemList != null) {
                String m = claimItemList.size() > 0 ? "" : "empty array";
                for (ClaimItem claimItem : claimItemList ) {
                    m += " ("+ claimItem.toString() + ")";
                }
                Log.d(TAG, "List claim item result => " + m);
            } else {
                Log.d(TAG, "List claim item result => null.");
            }
            return claimItemList;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");
        }
        return null;
    }
}