package com.roofnfloor.roofnfloorz.request;

public class PropertyTypeRequest {

	private String title;

	private Boolean isResidential;
	
	private Boolean isCommercial;
	
	private Boolean isProject;

	private String typeCode;

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

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	
}
