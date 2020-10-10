package com.roofnfloor.roofnfloorz.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BasicModel {

	
	@Column(name="created_at", nullable=true)
	private LocalDateTime createdAt;
	
	@Column(name="modified_at", nullable=true)
	private LocalDateTime modifiedAt;
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
