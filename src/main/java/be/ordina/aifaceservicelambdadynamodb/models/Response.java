package be.ordina.aifaceservicelambdadynamodb.models;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {
    private List<Result> results;

    public Response() {
    }

    public Response(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setSessions(List<Result> results) {
        this.results = results;
    }
}
