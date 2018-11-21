package com.amazonaws.lambda.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputMapper {

	@JsonIgnoreProperties(ignoreUnknown = true)
	private String employeeId;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String employeeName;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String expenseTravel;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String amount;

	@JsonProperty("employee_id")
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@JsonProperty("employee_name")
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@JsonProperty("expense_type")
	public String getExpenseTravel() {
		return expenseTravel;
	}

	public void setExpenseTravel(String expenseTravel) {
		this.expenseTravel = expenseTravel;
	}

	@JsonProperty("amount")
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
