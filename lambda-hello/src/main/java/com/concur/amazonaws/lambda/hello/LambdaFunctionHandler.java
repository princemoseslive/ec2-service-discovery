package com.concur.amazonaws.lambda.hello;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Object, Object> {

   

	@Override
	public Object handleRequest(Object input, Context context) {
		
		return "{'Message':'Successfully launched AWS-Lambda Function'}";
	}

}
