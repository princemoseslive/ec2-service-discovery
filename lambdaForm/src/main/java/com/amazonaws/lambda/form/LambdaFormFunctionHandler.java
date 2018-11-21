package com.amazonaws.lambda.form;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LambdaFormFunctionHandler implements RequestHandler<Object, Object> {

	@Override
	public Object handleRequest(Object input, Context context) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDB dynamoDB = new DynamoDB(client);
		Table tableName = dynamoDB.getTable("lambda-reimbursment");

		ObjectMapper mapper = new ObjectMapper();
		InputMapper inputMapper = mapper.convertValue(input, InputMapper.class);

		Item item = new Item().withPrimaryKey("employee_id", inputMapper.getEmployeeId())
				.withString("employee_name", inputMapper.getEmployeeName())
				.withString("expense_type", inputMapper.getExpenseTravel())
				.withString("amount", inputMapper.getAmount());
		tableName.putItem(item);
		return "{'status':'done'}";
	}

}