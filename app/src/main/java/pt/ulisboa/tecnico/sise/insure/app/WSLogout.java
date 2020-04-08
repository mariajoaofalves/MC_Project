package pt.ulisboa.tecnico.sise.insure.app;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import pt.ulisboa.tecnico.sise.insure.app.activities.AuthenticationActivity;
import pt.ulisboa.tecnico.sise.insure.datamodel.Customer;

public class WSLogout extends AsyncTask<String, String, Boolean> {

    public final static String TAG = "Logout";

    GlobalState _globalState;
    Context _context;

    public WSLogout(GlobalState globalState, Context context){
        _globalState = globalState;
        _context = context;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        int sessionId = _globalState.getSessionId();
        boolean session = false;
        try {
            session = WSHelper.logout(sessionId);
            Log.d(TAG, "ClaimLogout result => " + session);

        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return session;
    }

    protected void onPostExecute(Boolean session){
        Log.d(TAG,"the state of the sessionID " + _globalState.getSessionId() + " is " + session);
        Intent intent = new Intent(_context, AuthenticationActivity.class);
        _context.startActivity(intent);
    }
}
