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
@Table(name="BATCH_SUMMARY")
public class BatchSummaryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="SUMMARY_ID",length = 19)
	private Integer summaryId;
	@Column(name="BATCH_NAME",length = 255)
	private String batchName;
	@Column(name="CRATE_BY",length = 255)
	private String createdBy;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DT",length = 6)
	private Date createDate;
	@Column(name="SUMMARY_DTLS",length = 255)
	private String summaryDtls;
	@Column(name="UPDATED_BY",length = 255)
	private String updatedBy;
	@Column(name="UPDATE_DT",length = 6)
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
}
