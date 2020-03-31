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

import lombok.Data;
@Data
@Entity
@Table(name="ELG_DTLS")
public class ElgDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "elg_id_gen")
	@SequenceGenerator(name = "elg_id_gen",allocationSize = 1,initialValue = 1,sequenceName = "ED_Id_SEQ")
	@Column(name="ED_TRACE_ID",length = 19)
	private Integer edTraceId;
	@Column(name="BENEFIT_AMT")
	private Integer benefitAmt;
	@Column(name="CASE_NUMBER",length = 19)
	private Integer caseNumber;
	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DT")
	private Date createdDate;
	@Column(name="DENIAL_REASON",length = 255)
	private String denialReason;
	@Temporal(TemporalType.DATE)
	@Column(name = "PLAN_END_DT")
	private Date planEndDate;
	@Column(name="PLAND_NAME",length = 255)
	private String plandName;
	@Column(name="PLAN_START_DT")
	@Temporal(TemporalType.DATE)
	private Date planStartDate;
	@Column(name="PLAN_STATUS",length = 255)
	private String plandStatus;
	@Column(name="UPDATE_DT")
	@Temporal(TemporalType.DATE)
	private Date updateDate;
}
