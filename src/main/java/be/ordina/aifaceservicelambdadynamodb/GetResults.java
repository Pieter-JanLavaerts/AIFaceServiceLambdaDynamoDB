package be.ordina.aifaceservicelambdadynamodb;

import be.ordina.aifaceservicelambdadynamodb.models.Request;
import be.ordina.aifaceservicelambdadynamodb.models.Response;
import be.ordina.aifaceservicelambdadynamodb.models.Result;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GetResults implements Function<Request, Response> {
    private final String tableName;
    private final DynamoDbClient dynamoDbClient;

    public GetResults(String tableName, DynamoDbClient dynamoDbClient) {
        this.tableName = tableName;
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public Response apply(Request request) {
        String outputTableHashKey = request.getOutputTableHashKey();

        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":outputTableHashKey", AttributeValue.builder().s(outputTableHashKey).build());

        QueryRequest queryRequest = QueryRequest.builder()
                .tableName(tableName)
                .keyConditionExpression("outputTableHashKey = :outputTableHashKey")
                .expressionAttributeValues(expressionValues).build();

        List<Map<String, AttributeValue>> queryResponse = dynamoDbClient.query(queryRequest).items();

        List<Result> sessions = queryResponse.isEmpty() ? List.of()
                : queryResponse.stream()
                .map(Result::from)
                .collect(Collectors.toList());

        return new Response(sessions);
    }
}
