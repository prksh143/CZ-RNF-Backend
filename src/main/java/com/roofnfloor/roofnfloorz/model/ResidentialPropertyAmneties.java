package com.roofnfloor.roofnfloorz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="residential_property_amneties")
public class ResidentialPropertyAmneties {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@Column(name="pid", nullable=false)
	private Long pid;
	
	@Column(name="gym", nullable=false)
	private boolean gym;

	@Column(name="swimming_pool", nullable=false)
	private boolean swimmingPool;

	@Column(name="lift", nullable=false)
	private boolean lift;

	@Column(name="gated", nullable=false)
	private boolean gated;

	@Column(name="security", nullable=false)
	private boolean security;

	@Column(name="salon", nullable=false)
	private boolean salon;

	@Column(name="supermarket", nullable=false)
	private boolean supermarket;

	@Column(name="stationary", nullable=false)
	private boolean stationary;

	@Column(name="indoor_games", nullable=false)
	private boolean indoorGames;

	@Column(name="outdoor_games", nullable=false)
	private boolean outdoorGames;

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

	public boolean isGym() {
		return gym;
	}

	public void setGym(boolean gym) {
		this.gym = gym;
	}

	public boolean isSwimmingPool() {
		return swimmingPool;
	}

	public void setSwimmingPool(boolean swimmingPool) {
		this.swimmingPool = swimmingPool;
	}

	public boolean isLift() {
		return lift;
	}

	public void setLift(boolean lift) {
		this.lift = lift;
	}

	public boolean isGated() {
		return gated;
	}

	public void setGated(boolean gated) {
		this.gated = gated;
	}

	public boolean isSecurity() {
		return security;
	}

	public void setSecurity(boolean security) {
		this.security = security;
	}

	public boolean isSalon() {
		return salon;
	}

	public void setSalon(boolean salon) {
		this.salon = salon;
	}

	public boolean isSupermarket() {
		return supermarket;
	}

	public void setSupermarket(boolean supermarket) {
		this.supermarket = supermarket;
	}

	public boolean isStationary() {
		return stationary;
	}

	public void setStationary(boolean stationary) {
		this.stationary = stationary;
	}

	public boolean isIndoorGames() {
		return indoorGames;
	}

	public void setIndoorGames(boolean indoorGames) {
		this.indoorGames = indoorGames;
	}

	public boolean isOutdoorGames() {
		return outdoorGames;
	}

	public void setOutdoorGames(boolean outdoorGames) {
		this.outdoorGames = outdoorGames;
	}
}
