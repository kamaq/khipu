package com.raw.khipu.dto;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the userfunction database table.
 * 
 */
@Embeddable
public class UserfunctionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idUser;

	@Column(insertable=false, updatable=false)
	private int idComponent;

	@Column(insertable=false, updatable=false)
	private String idFunction;

	public UserfunctionPK() {
	}
	public int getIdUser() {
		return this.idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdComponent() {
		return this.idComponent;
	}
	public void setIdComponent(int idComponent) {
		this.idComponent = idComponent;
	}
	public String getIdFunction() {
		return this.idFunction;
	}
	public void setIdFunction(String idFunction) {
		this.idFunction = idFunction;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserfunctionPK)) {
			return false;
		}
		UserfunctionPK castOther = (UserfunctionPK)other;
		return 
			(this.idUser == castOther.idUser)
			&& (this.idComponent == castOther.idComponent)
			&& this.idFunction.equals(castOther.idFunction);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUser;
		hash = hash * prime + this.idComponent;
		hash = hash * prime + this.idFunction.hashCode();
		
		return hash;
	}
}