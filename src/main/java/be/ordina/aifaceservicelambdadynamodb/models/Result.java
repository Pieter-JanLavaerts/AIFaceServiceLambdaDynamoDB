package be.ordina.aifaceservicelambdadynamodb.models;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result implements Serializable {
    private String outputTableHashKey;
    private Map<String, Integer> results;

    public Result() {
    }

    public Result(String outputTableHashKey, Map<String, Integer> results) {
        this.outputTableHashKey = outputTableHashKey;
        this.results = results;
    }

    public static Result from(Map<String, AttributeValue> values){
        Result result = new Result();

        result.setOutputTableHashKey(values.get("outputTableHashKey").s());

        Map<String, AttributeValue> resultValues = values.get("results").m();
        Map<String, Integer> results = new HashMap<>();

        resultValues.forEach((k, v) -> results.put(k, Integer.parseInt(v.n())));
        result.setResults(results);

        return result;
    }

    public void setOutputTableHashKey(String outputTableHashKey) {
        this.outputTableHashKey = outputTableHashKey;
    }

    public void setResults(Map<String, Integer> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Result{" +
                "outputTableHashKey=" + outputTableHashKey + '\'' +
                ", results=" + results +
                '}';
    }
}
