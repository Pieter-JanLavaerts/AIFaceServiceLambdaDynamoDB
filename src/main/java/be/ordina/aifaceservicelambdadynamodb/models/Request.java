package be.ordina.aifaceservicelambdadynamodb.models;

import java.io.Serializable;

public class Request implements Serializable {
    private String outputTableHashKey;

    public Request() {
    }

    public Request(String outputTableHashKey) {
        this.outputTableHashKey = outputTableHashKey;
    }

    public String getOutputTableHashKey() {
        return outputTableHashKey;
    }

    public void setOutputTableHashKey(String userId) {
        this.outputTableHashKey = userId;
    }
}
