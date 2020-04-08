package pt.ulisboa.tecnico.sise.insure.app;

import android.app.Application;

import java.util.ArrayList;

import pt.ulisboa.tecnico.sise.insure.datamodel.ClaimItem;
import pt.ulisboa.tecnico.sise.insure.datamodel.Customer;

import android.app.Application;
import java.util.List;
import pt.ulisboa.tecnico.sise.insure.datamodel.ClaimItem;
import pt.ulisboa.tecnico.sise.insure.datamodel.ClaimRecord;
import pt.ulisboa.tecnico.sise.insure.datamodel.Customer;

public class GlobalState extends Application {
    private Integer sessionId;
    private List<ClaimItem> _claimList;
    public Integer getSessionId() {
        return sessionId;
    }
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }
    public List<ClaimItem> get_claimList() {
        return _claimList;
    }
    public void set_claimList(List<ClaimItem> _claimList) {
        this._claimList = _claimList;
    }
    private Customer customer;
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    private ClaimItem claimItem;
    public void setClaimItem(ClaimItem claimItem) {
        this.claimItem = claimItem;
    }
    public ClaimItem getClaimItem() {
        return claimItem;
    }
    private ClaimRecord claimRecord;
    public void setClaimRecord(ClaimRecord claimRecord) {
        this.claimRecord = claimRecord;
    }
    public ClaimRecord getClaimRecord() {
        return claimRecord;
    }

    private Iterable<String> plateList;

    public Iterable<String> getPlateList() {
        return plateList;
    }

    public void setPlateList(Iterable<String> plateList) {
        this.plateList = plateList;
    }

}
