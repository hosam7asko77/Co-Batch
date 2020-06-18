package com.hit.share;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hit.entity.BatchRunDetailsEntity;
import com.hit.entity.BatchSummaryEntity;
import com.hit.entity.CdTriggersEntity;
import com.hit.entity.ElgDetailsEntity;
import com.hit.repository.BatchRunRepository;
import com.hit.repository.BatchSummaryRepository;

@Component
public abstract class MainBatch {
	@Autowired 
	BatchRunRepository runRepository;
	@Autowired
	BatchSummaryRepository summaryRepository;
	public Integer preProcess(String batchName) {
		BatchRunDetailsEntity detailsEntity=new BatchRunDetailsEntity();
		detailsEntity.setBatchName(batchName);
		detailsEntity.setCreatedBy("hosam");
		detailsEntity.setStartedDate(new Date()); 
		detailsEntity.setRunStatus("Start run");
		BatchRunDetailsEntity detailsEntity2 = runRepository.save(detailsEntity);
		return detailsEntity2.getRunId();
	}
	public abstract void start();
	public abstract void process(CdTriggersEntity cdTriggersEntity);
	public abstract void generatePdf(ElgDetailsEntity ed);
	
	
	public void  postProcess(String batchName,Integer successCount,Integer filure,Integer batchId){
		BatchSummaryEntity summaryEntity=new BatchSummaryEntity();
		summaryEntity.setBatchName(batchName);
		summaryEntity.setCreatedBy("hosam");
		summaryEntity.setSummaryDtls("the number of success is : "+successCount+" number of fulire : "+filure);
		summaryRepository.save(summaryEntity);
		BatchRunDetailsEntity getDetailsEntity=runRepository.findById(batchId).get();
		getDetailsEntity.setRunStatus("success");
		getDetailsEntity.setEndDate(new Date());
		runRepository.save(getDetailsEntity);
		
	}

}
