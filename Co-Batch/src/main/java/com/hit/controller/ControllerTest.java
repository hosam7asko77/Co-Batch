package com.hit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hit.service.CoDailyBatch;
@RestController
@RequestMapping("/test")
public class ControllerTest {
@Autowired 
CoDailyBatch coDailyBatch;
	
	@GetMapping()
	public String testValue() {
		coDailyBatch.tset();
		return "done";
	}
	@GetMapping(path="me/{name}")
	public String getName(@PathVariable("name") String name) {
		return "hi "+name;
	}
	/*@GetMapping("/preProcess")
	public String checkPreProcess() {
		if(coDailyBatch.preProcess()!=null) {
			return coDailyBatch.preProcess().toString();
		}else {
			return "problem in insert";
		}
	}*/
}
