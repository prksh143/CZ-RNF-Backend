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
@Table(name="property_type")
public class PropertyType extends BasicModel{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="type_code", nullable=false)
	private String typeCode;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="is_residential", nullable=false)
	private Boolean isResidential;
	
	@Column(name="is_commercial", nullable=false)
	private Boolean isCommercial;
	
	@Column(name="is_project", nullable=false)
	private Boolean isProject;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="propertyType")
	private Set<Property> property;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Set<Property> getProperty() {
		return property;
	}

	public void setProperty(Set<Property> property) {
		this.property = property;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getIsResidential() {
		return isResidential;
	}

	public void setIsResidential(Boolean isResidential) {
		this.isResidential = isResidential;
	}

	public Boolean getIsCommercial() {
		return isCommercial;
	}

	public void setIsCommercial(Boolean isCommercial) {
		this.isCommercial = isCommercial;
	}

	public Boolean getIsProject() {
		return isProject;
	}

	public void setIsProject(Boolean isProject) {
		this.isProject = isProject;
	}
}
