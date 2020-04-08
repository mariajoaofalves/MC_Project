package pt.ulisboa.tecnico.sise.insure.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import pt.ulisboa.tecnico.sise.insure.app.activities.InsuredProfileActivity;
import pt.ulisboa.tecnico.sise.insure.datamodel.Customer;

public class WSInsuredProfile extends AsyncTask<Customer, Customer, Customer> {
    TextView _viewTextName;
    TextView _viewTextAdress;
    TextView _viewTextBirthdate;
    TextView _viewTextFiscalNumber;
    TextView _viewTextPolicyNumber;
    GlobalState _globalState;
    Customer customer;
    InsuredProfileActivity _profile;
    Integer sessionId;

    public WSInsuredProfile(GlobalState globalState, InsuredProfileActivity profile) {
        _globalState = globalState;
        _profile= profile;
    }
    @Override
    protected Customer doInBackground(Customer... customers) {
        sessionId = _globalState.getSessionId();
        Log.d("Profile", "Profile result => " + sessionId);
        try {
            customer = WSHelper.getCustomerInfo(sessionId);
            Log.d("Profile", "Profile result => " + customer);
        } catch (Exception e) {
            Log.d("Profile", e.toString());
        }
        return customer;
    }
    @Override
    protected void onPostExecute(Customer customer) {
        _globalState.setCustomer(customer);
        _profile.updateInterface(customer);
    }
}
