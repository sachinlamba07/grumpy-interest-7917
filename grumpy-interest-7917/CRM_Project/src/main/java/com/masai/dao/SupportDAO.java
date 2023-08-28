package com.masai.dao;

import java.util.List;

import com.masai.entity.Feedback;
import com.masai.entity.Issues;
import com.masai.entity.SupportRepresentative;
import com.masai.exception.DuplicateDataException;
import com.masai.exception.NoRecordFound;
import com.masai.exception.SomethingWentWrong;

public interface SupportDAO {

	void addCSR(SupportRepresentative csr) throws DuplicateDataException;

	void logIn(String username, String password) throws NoRecordFound;

	List<Issues> viewIssue() throws SomethingWentWrong;

	void assignToSelf()throws SomethingWentWrong;

	void assignToOther(int id)throws SomethingWentWrong;

	void replyToIssue(int id, String reply) throws SomethingWentWrong;

	void closeIssue(int id) throws SomethingWentWrong;

	List<Feedback> viewFeedback() throws SomethingWentWrong,NoRecordFound;

	void deleteAccount() throws SomethingWentWrong, NoRecordFound;

	void deleteIssue(int id) throws SomethingWentWrong, NoRecordFound;

	
	

}
