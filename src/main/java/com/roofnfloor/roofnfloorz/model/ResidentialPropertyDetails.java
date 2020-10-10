package com.roofnfloor.roofnfloorz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="residential_property_details")
public class ResidentialPropertyDetails {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@Column(name="pid", nullable=false)
	private Long pid;
	
	@Column(name="facing")
	private String facing;
	
	@Column(name="parking")
	private int parking;
	
	@Column(name="balcony")
	private int balcony;
	
	@Column(name="bedroom", nullable=false)
	private int bedroom;
	
	@Column(name="bathroom", nullable=false)
	private int bathroom;
	
	@Column(name="available_for")
	private String availableFor;
	
	@Column(name="maintenance_charges")
	private int maintenanceCharges;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getFacing() {
		return facing;
	}

	public void setFacing(String facing) {
		this.facing = facing;
	}

	public int getParking() {
		return parking;
	}

	public void setParking(int parking) {
		this.parking = parking;
	}

	public int getBalcony() {
		return balcony;
	}

	public void setBalcony(int balcony) {
		this.balcony = balcony;
	}

	public int getBedroom() {
		return bedroom;
	}

	public void setBedroom(int bedroom) {
		this.bedroom = bedroom;
	}

	public int getBathroom() {
		return bathroom;
	}

	public void setBathroom(int bathroom) {
		this.bathroom = bathroom;
	}

	public String getAvailableFor() {
		return availableFor;
	}

	public void setAvailableFor(String availableFor) {
		this.availableFor = availableFor;
	}

	public int getMaintenanceCharges() {
		return maintenanceCharges;
	}

	public void setMaintenanceCharges(int maintenanceCharges) {
		this.maintenanceCharges = maintenanceCharges;
	}
}
