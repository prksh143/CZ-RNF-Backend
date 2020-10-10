package com.roofnfloor.roofnfloorz.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="user_type")
public class UserType extends BasicModel{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="type_code", nullable=false)
	private String typeCode;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="userType")
	private Set<User> usersList;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="userType")
	private Set<PricingPackage> pricingList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Set<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(Set<User> usersList) {
		this.usersList = usersList;
	}

	@Override
	public String toString() {
		return "UserType [id=" + id + ", typeCode=" + typeCode + ", title=" + title + "]";
	}
	
}
