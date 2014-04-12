package com.wolfsoft.hr.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the job_history database table.
 * 
 */
@Embeddable
public class JobHistoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="employee_id", insertable=false, updatable=false)
	private int employeeId;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private java.util.Date startDate;

	public JobHistoryPK() {
	}
	public int getEmployeeId() {
		return this.employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JobHistoryPK)) {
			return false;
		}
		JobHistoryPK castOther = (JobHistoryPK)other;
		return 
			(this.employeeId == castOther.employeeId)
			&& this.startDate.equals(castOther.startDate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.employeeId;
		hash = hash * prime + this.startDate.hashCode();
		
		return hash;
	}
}