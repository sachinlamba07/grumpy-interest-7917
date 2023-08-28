package com.masai.dao;

import com.masai.entity.Customer;
import com.masai.exception.DuplicateDataException;
import com.masai.exception.NoRecordFound;
import com.masai.exception.SomethingWentWrong;

public interface CustomerDAO {

	void addCustomer(Customer customer) throws DuplicateDataException, SomethingWentWrong;

	void logIn(String username, String password) throws NoRecordFound;

	void raiseIssue(String issue) throws SomethingWentWrong;

	void giveFeedback(int id, String feedback, int rating) throws SomethingWentWrong;

	void deleteAccount() throws SomethingWentWrong;

}
