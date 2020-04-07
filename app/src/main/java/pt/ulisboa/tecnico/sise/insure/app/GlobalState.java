package pt.ulisboa.tecnico.sise.insure.app;

import android.app.Application;

public class GlobalState extends Application {

    private Integer sessionId;

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

}
