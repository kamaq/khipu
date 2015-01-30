package com.raw.khipu.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the userfunction database table.
 * 
 */
@Entity
@Table(name="userfunction")
@NamedQuery(name="Userfunction.findAll", query="SELECT u FROM Userfunction u")
public class Userfunction implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserfunctionPK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateInsert;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdate;

	private byte[] enabled;

	private int userInsert;

	private int userUpdate;

	//bi-directional many-to-one association to Function
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="idComponent", referencedColumnName="idComponent"),
		@JoinColumn(name="idFunction", referencedColumnName="idFunction")
		})
	private Function function;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	public Userfunction() {
	}

	public UserfunctionPK getId() {
		return this.id;
	}

	public void setId(UserfunctionPK id) {
		this.id = id;
	}

	public Date getDateInsert() {
		return this.dateInsert;
	}

	public void setDateInsert(Date dateInsert) {
		this.dateInsert = dateInsert;
	}

	public Date getDateUpdate() {
		return this.dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public byte[] getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte[] enabled) {
		this.enabled = enabled;
	}

	public int getUserInsert() {
		return this.userInsert;
	}

	public void setUserInsert(int userInsert) {
		this.userInsert = userInsert;
	}

	public int getUserUpdate() {
		return this.userUpdate;
	}

	public void setUserUpdate(int userUpdate) {
		this.userUpdate = userUpdate;
	}

	public Function getFunction() {
		return this.function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}