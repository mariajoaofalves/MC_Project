package pt.ulisboa.tecnico.sise.insure.app;

import android.app.Application;

import java.util.ArrayList;

import pt.ulisboa.tecnico.sise.insure.datamodel.ClaimItem;

public class GlobalState extends Application {

    private Integer sessionId;

    private ArrayList<ClaimItem> _claimList;

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public ArrayList<ClaimItem> get_claimList() {
        return _claimList;
    }

    public void set_claimList(ArrayList<ClaimItem> _claimList) {
        this._claimList = _claimList;
    }
}
