package com.codefaucet.tms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.dto.PanelistDTO;
import com.codefaucet.tms.model.service_interface.IPanelistService;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'ENCODER')")
@RequestMapping("panelist")
public class PanelistController {
	
	@Autowired
	private IPanelistService panelistService;
	
	@PostMapping("create")
	public TaskResult<PanelistDTO> create(@RequestBody PanelistDTO dto) {
		return panelistService.create(dto);
	}
	
	@PostMapping("update")
	public TaskResult<PanelistDTO> update(@RequestBody PanelistDTO dto) {
		return panelistService.update(dto);
	}
	
	@PostMapping("delete")
	public TaskResult<Boolean> delete(@RequestBody long panelistId) {
		return panelistService.setActive(panelistId, true);
	}
	
	@PostMapping("restore")
	public TaskResult<Boolean> restore(@RequestBody long panelistId) {
		return panelistService.setActive(panelistId, false);
	}
	
	@PostMapping("getNew")
	public PanelistDTO getNew() {
		return panelistService.getNew();
	}
	
	@PostMapping("get")
	public PanelistDTO get(@RequestBody long panelistId) {
		return panelistService.get(panelistId);
	}
	
	@PostMapping("find")
	public List<PanelistDTO> find(@RequestBody Map<String, Object> parameter) {
		var queryString = (String)parameter.get("queryString");
		var includeInactive = (boolean)parameter.get("includeInactive");
		var pageNumber = (int)(long)parameter.get("pageNumber");
		var pageSize = (int)(long)parameter.get("pageSize");
		return panelistService.find(queryString, includeInactive, pageNumber, pageSize);
	}
	
}
