package com.masai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Issues {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 300, nullable = false)
	private String issue;
	
	@Column(length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="Customer_id")
	private Customer customer;
	
	@Column(length =300)
	private String reply;
	
	@ManyToOne
	@JoinColumn(name = "CSR_id")
	private SupportRepresentative csr;

	public Issues() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Issues(String issue, Status status, Customer customer, SupportRepresentative csr, String reply) {
		super();
		this.issue = issue;
		this.status = status;
		this.customer = customer;
		this.reply = reply;
		this.csr = csr;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the issue
	 */
	public String getIssue() {
		return issue;
	}

	/**
	 * @param issue the issue to set
	 */
	public void setIssue(String issue) {
		this.issue = issue;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the reply
	 */
	public String getReply() {
		return reply;
	}

	/**
	 * @param reply the reply to set
	 */
	public void setReply(String reply) {
		this.reply = reply;
	}

	/**
	 * @return the csr
	 */
	public SupportRepresentative getCsr() {
		return csr;
	}

	/**
	 * @param csr the csr to set
	 */
	public void setCsr(SupportRepresentative csr) {
		this.csr = csr;
	}

	@Override
	public String toString() {
		return "Issues [id=" + id + ", issue=" + issue + ", status=" + status + ", customer=" + customer + ", reply="
				+ reply + ", csr=" + csr + "]";
	}
	
	
	
	
}
