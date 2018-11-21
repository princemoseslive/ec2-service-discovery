# ec2-service-discovery
An EC2 Container Service Reference Architecture for providing Service Discovery to containers using CloudWatch Events, Lambda and Route 53 private hosted zones.

This repository would help you setup AWS Lambda, And then help you setup a service discovery.

Please take a look at the wiki for a detailed(https://github.com/princemoseslive/ec2-service-discovery/wiki/Getting-Started)

## Getting Started (Short)
1. Set up Eclipse, import the projects, and install the AWS Eclipse Toolkit plugin.
2. Upload the Lambda Functions (Right click on project > Amazon Web Services > Upload Function to AWS Lambda)
3. Set up a DynamoDB table 'lambda-reimbursment' with Hash Key 'employee_id' (String). Set the DynamoDB tables as event source for LambdaSendMail and LambdaApproval. Create an API method, so that a click on the link (HTTP GET) calls the LambdaApproval Function (with a parameter 'employee_id')

If the Lambda Function completes successfully, a new entry will be added to the DynamoDB table lambda-reimbursment.
LambdaSendMail gets triggered by the DynamoDB stream (pull model) and sends an e-mail with the info that has been added to the table.
If you have received the e-mail and click on the approval URL in the e-mail body, the LambdaApproval Function will be called and add an "approved" column entry to DynamoDB.

![Alt text] (https://github.com/princemoseslive/ec2-service-discovery/blob/master/cloudcraft%20(2).png)
