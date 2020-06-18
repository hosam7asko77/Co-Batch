package com.hit.service;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hit.entity.BatchRunDetailsEntity;
import com.hit.entity.BatchSummaryEntity;
import com.hit.entity.CdTriggersEntity;
import com.hit.entity.ElgDetailsEntity;
import com.hit.repository.BatchRunRepository;
import com.hit.repository.BatchSummaryRepository;
import com.hit.repository.CdTriggersRepository;
import com.hit.repository.ElgDetailsRepository;
import com.hit.share.MainBatch;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.bytebuddy.asm.Advice.This;

@Service
public class CoDailyBatch extends MainBatch{
	private static final String BATCH_NAME="Co Batch";
	private int successCount=0;
	private int filure=0;

	@Autowired 
	BatchRunRepository runRepository;
	@Autowired
	BatchSummaryRepository summaryRepository;
	@Autowired
	CdTriggersRepository triggersRepository;
	@Autowired
	ElgDetailsRepository elgDetailsRepository;
	@Override
	public Integer preProcess(String batchName) {
		// TODO Auto-generated method stub
		return super.preProcess(batchName);
	}
	@Override
	public void postProcess(String batchName, Integer successCount, Integer filure, Integer batchId) {
		// TODO Auto-generated method stub
		super.postProcess(batchName, successCount, filure, batchId);
	}
	@Override
	public void start() {
		List<CdTriggersEntity> cdTriggers=triggersRepository.findByTrgStatus("P");
		ExecutorService executorService=Executors.newFixedThreadPool(20);
		CompletionService<Object> pool=new ExecutorCompletionService<>(executorService);
		for (CdTriggersEntity cdTriggersEntity : cdTriggers) { 
			pool.submit(new Callable<Object>() {
				
				@Override
				public Object call() throws Exception {
					process(cdTriggersEntity);
					return null;
				}
			});
			
		}
		
	}
	@Override
	public void process(CdTriggersEntity cdTriggersEntity) {
		Integer caseNum=cdTriggersEntity.getCaseNum();  
		try {
			ElgDetailsEntity ed=elgDetailsRepository.findByCaseNumber(caseNum);
			generatePdf(ed);
			cdTriggersEntity.setBatchName(BATCH_NAME);
			cdTriggersEntity.setTrgStatus("D");
			triggersRepository.save(cdTriggersEntity);
			successCount++;
			
		} catch (Exception e) {
			e.printStackTrace();
			filure++;
			
		}
		
	}
	@Override
	public void generatePdf(ElgDetailsEntity ed) {
		Document document=new Document();
		try {
			PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream("/Users/hosam7asko/batch/addTableTest"+ed.getCaseNumber()+".pdf"));
			document.open();
			PdfPTable table=new PdfPTable(2);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);
			float[] columnWidths= {1f,1f};
			table.setWidths(columnWidths);
			table.addCell("Case Number");
			table.addCell(ed.getCaseNumber().toString());
			table.addCell("Paln Name");
			table.addCell(ed.getPlandName());
			if (ed.getPlanStartDate() != null) {
				table.addCell("Plan Start Date");
				table.addCell(ed.getPlanStartDate().toString());
				table.addCell("Plan End Date");
				table.addCell(ed.getPlanEndDate().toString());
				table.addCell("Benificiary Amount");
				table.addCell(ed.getBenefitAmt().toString());
			}
			table.addCell("Plan Denial Reson");
			table.addCell(ed.getDenialReason());
			table.addCell("Plan Status");
			table.addCell(ed.getPlandStatus());
			document.add(table);
			document.close();
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	public void tset(){
		Integer batchId=preProcess(BATCH_NAME);
		start();
		postProcess(BATCH_NAME, successCount, filure, batchId);
		
		
		
	}
	
	

	
}
