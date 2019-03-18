package com.codefaucet.tms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.dto.PanelistDTO;
import com.codefaucet.tms.model.service_interface.IPanelistService;

@RestController
@RequestMapping("/panelist")
public class PanelistController {
	
	@Autowired
	private IPanelistService panelistService;
	
	public TaskResult<PanelistDTO> create(@RequestBody PanelistDTO dto) {
		return panelistService.create(dto);
	}
	
	public TaskResult<PanelistDTO> update(@RequestBody PanelistDTO dto) {
		return panelistService.update(dto);
	}
	
	public TaskResult<Boolean> delete(@RequestBody long panelistId) {
		return panelistService.setActive(panelistId, true);
	}
	
	public TaskResult<Boolean> restore(@RequestBody long panelistId) {
		return panelistService.setActive(panelistId, false);
	}
	
	public PanelistDTO getNew() {
		return panelistService.getNew();
	}
	
	public PanelistDTO get(@RequestBody long panelistId) {
		return panelistService.get(panelistId);
	}
	
	public List<PanelistDTO> find(@RequestBody Map<String, Object> parameter) {
		var queryString = (String)parameter.get("queryString");
		var includeInactive = (boolean)parameter.get("includeInactive");
		var pageNumber = (int)(long)parameter.get("pageNumber");
		var pageSize = (int)(long)parameter.get("pageSize");
		return panelistService.find(queryString, includeInactive, pageNumber, pageSize);
	}
	
}
