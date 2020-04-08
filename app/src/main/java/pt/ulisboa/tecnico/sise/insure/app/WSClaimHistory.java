package pt.ulisboa.tecnico.sise.insure.app;

import android.os.AsyncTask;
import android.util.Log;


import java.util.List;

import pt.ulisboa.tecnico.sise.insure.app.activities.ClaimHistoryActivity;
import pt.ulisboa.tecnico.sise.insure.datamodel.ClaimItem;


    public class WSClaimHistory extends AsyncTask<Integer, String, List<ClaimItem>> {
        public final static String TAG = "ClaimHistory";
        Integer sessionId;
        GlobalState _globalState;
        ClaimHistoryActivity _claimHistory;

        public WSClaimHistory(GlobalState globalState, ClaimHistoryActivity claimHistory) {
            _globalState = globalState;
            _claimHistory = claimHistory;
        }

        @Override
        protected List<ClaimItem> doInBackground(Integer... integers) {
            List<ClaimItem> claimItemList = null;
            sessionId = _globalState.getSessionId();
            try {
                claimItemList = WSHelper.listClaims(sessionId);
                Log.d(TAG, " SessionId =>" + sessionId);
                Log.d(TAG, " ClaimList =>" + claimItemList);
                if (claimItemList != null) {
                    String m = claimItemList.size() > 0 ? "" : "empty array";
                    for (ClaimItem claimItem : claimItemList) {
                        m += " (" + claimItem.toString() + ")";
                    }
                    Log.d(TAG, "List claim item result => " + m);
                } else {
                    Log.d(TAG, "List claim item result => null.");
                }
            } catch (Exception e) {
                Log.d(TAG, e.toString());
            }
            return claimItemList;
        }

        @Override
        protected void onPostExecute(List<ClaimItem> claimItemList) {
            _globalState.set_claimList(claimItemList);
            _claimHistory.UpdateListClaims(claimItemList);
        }
    }