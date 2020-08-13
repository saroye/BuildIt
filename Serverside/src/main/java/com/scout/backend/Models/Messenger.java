package com.scout.backend.Models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import javax.persistence.Table;

@Entity
@Table(name = "Messenger")
public class Messenger {

	@Id
  	@GeneratedValue(strategy=GenerationType.AUTO)
  	private Integer m_id;
	
	@NotEmpty
	@ManyToOne
	@JoinColumn
	private User user1;
	
	
	@NotEmpty
	@ManyToOne
	@JoinColumn
	private User user2;
	

	@NotEmpty
	private String messageCotent;
	
	@NotEmpty
	private Timestamp time;

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getM_id() {
		return m_id;
	}

	public void setM_id(Integer m_id) {
		this.m_id = m_id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public String getMessageCotent() {
		return messageCotent;
	}

	public void setMessageCotent(String messageCotent) {
		this.messageCotent = messageCotent;
	}
	
	public void setCurrentTime () {
		 Timestamp ts = new Timestamp(10000);
		 this.time = ts;
	}
	


	
}
