package com.concur.amazonaws.lambda.mail;

import java.util.Map.Entry;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.StreamRecord;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;

public class LambdaSendMailFunctionHandler implements RequestHandler<DynamodbEvent, Object> {

	@Override
	public Object handleRequest(DynamodbEvent input, Context context) {
		String employeeId = "";
		String employeeName = "";
		String expenseType = "";
		Double amount = 0.0;

		for (DynamodbStreamRecord r : input.getRecords()) {
			System.out.println(r.getDynamodb());

			StreamRecord sr = r.getDynamodb();

			for (Entry<String, AttributeValue> entry : sr.getNewImage().entrySet()) {
				if (entry == null) {
					continue;
				}
				String k = entry.getKey();
				AttributeValue v = entry.getValue();
				switch (k) {
				case "employee_id":
					employeeId = v.getS();
					break;
				case "employee_name":
					employeeName = v.getS();
					break;
				case "expense_type":
					expenseType = v.getS();
					break;
				case "amount":
					amount = Double.valueOf(v.getS());
					break;
				default:
					context.getLogger().log("Key " + k + " is unknown.");
				}
			}

		}

		context.getLogger().log("ENTRY: " + employeeId + " | " + employeeName + " | " + expenseType + " | " + amount);

		String from = "upkar.kumar@sap.com";

		String to = "prince.moses.rajendran@sap.com";

		String subject = String.format("Expense reimbursment request by %s", employeeName);
		String approvalUrl = String.format(
				"https://zshjgnlw00.execute-api.eu-west-1.amazonaws.com/dev/lambdaapproval/reimbursment?id=%s",
				employeeId);
		System.out.println(approvalUrl);
		String body = String.format(
				"Hello boss,\n\nplease approve my expense reimbursment:\n%s\n\nExpense type: %s\nAmount: %s EUR\n\nThanks!\n%s\nEmployee ID: %s ",
				approvalUrl, expenseType, amount, employeeName, employeeId);
		sendMail(from, to, subject, body);
		context.getLogger().log("Email sent from " + from + " to " + to);

		return null;
	}

	private void sendMail(final String from, final String to, final String subjectStr, final String bodyStr) {
		Destination destination = new Destination().withToAddresses(new String[] { to });

		Content subject = new Content().withData(subjectStr);
		Content textBody = new Content().withData(bodyStr);
		Body body = new Body().withText(textBody);

		Message message = new Message().withSubject(subject).withBody(body);

	}
}