package pt.ulisboa.tecnico.sise.insure.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import pt.ulisboa.tecnico.sise.insure.app.activities.AuthenticationActivity;
import pt.ulisboa.tecnico.sise.insure.app.activities.HomeActivity;

public class WSAuthentication extends AsyncTask<String, String, Integer> {

    private String _username;
    private String _password;
    GlobalState _globalState;
    AuthenticationActivity _login;

    public WSAuthentication(String username, String password, GlobalState globalState, AuthenticationActivity login) {
        _username = username;
        _password = password;
        _globalState = globalState;
        _login= login;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        int sessionId=-1;
        try {
            sessionId = WSHelper.login(_username,_password);
            Log.d("Login", "Login result => " + sessionId);

        } catch (Exception e) {
            Log.d("Login", e.toString());
        }
        Log.d("batata","Login result => " + sessionId);
        return sessionId;
    }

    @Override
    protected void onPostExecute(Integer sessionId) {
        if(sessionId>0){
            _globalState.setSessionId(sessionId);
            Toast.makeText(_login.getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(_login.getApplicationContext(), HomeActivity.class);
            //_login.getApplicationContext().startActivity(intent);
            //((Activity) _login.getApplicationContext() ).finish();
        }else if(sessionId==0){
            Toast.makeText(_login.getApplicationContext(), "Login Failed! Wrong Password or Username! Please Try Again.", Toast.LENGTH_SHORT);
        } else{
            Toast.makeText(_login.getApplicationContext(), "Server Connection Failed!", Toast.LENGTH_SHORT);
        }

    }

}

