package be.ordina.aifaceservicelambdadynamodb;

import be.ordina.aifaceservicelambdadynamodb.models.Request;
import be.ordina.aifaceservicelambdadynamodb.models.Response;
import be.ordina.aifaceservicelambdadynamodb.models.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.SerializationHint;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@NativeHint
@SerializationHint(types = {Request.class, Response.class, Result.class})
@SpringBootApplication
public class AIFaceServiceLambdaDynamodbApplication {
	Region awsRegion = Region.EU_WEST_1;

	public static void main(String[] args) {
		SpringApplication.run(AIFaceServiceLambdaDynamodbApplication.class, args);
	}

	@Bean
	public DynamoDbClient dynamoDbClient() {
		return DynamoDbClient.builder().region(awsRegion).build();
	}

	@Bean
	public String tableName(){
		return "ai-mlops-json-output-table";
	}
}