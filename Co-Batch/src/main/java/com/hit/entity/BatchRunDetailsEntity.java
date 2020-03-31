package com.hit.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
@Data
@Entity
@Table(name="BATCH_RUN_DYLS")
public class BatchRunDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="RUN_ID",length = 19)
	private Integer runId;
	@Column(name="BATCH_NAME",length = 255)
	private String batchName;
	@Column(name="CRATE_BY",length = 255)
	private String createdBy;
	@Column(name="CREATE_DT")
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Column(name="END_DT")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	@Column(name="RUN_STATUS",length = 255)
	private String runStatus;
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DT")
	private Date startedDate;
	@Column(name="UPDATE_DT")
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	@Column(name="UPDATED_BY",length = 255)
	private String updatedBy;
}
