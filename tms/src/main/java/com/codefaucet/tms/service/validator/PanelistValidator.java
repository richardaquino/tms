package com.codefaucet.tms.service.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.codefaucet.tms.model.dto.PanelistDTO;
import com.codefaucet.tms.model.service_interface.validator.IValidator;

@Component
public class PanelistValidator implements IValidator<PanelistDTO> {

	@Override
	public Map<String, String> validate(PanelistDTO model) {
		var errors = new HashMap<String, String>();
		
		if(model.getFirstName() == null || model.getFirstName().isBlank()) {
			errors.put("firstName", "First name is required.");
		}
		model.setFirstName(model.getFirstName().trim());
		if(model.getLastName() == null || model.getLastName().isBlank()) {
			errors.put("lastName", "Last name is required.");
		}
		model.setLastName(model.getLastName().trim());
		
		model.setMiddleName(model.getMiddleName() == null ? "" : model.getMiddleName().trim());
		model.setSuffix(model.getSuffix() == null ? "" : model.getSuffix().trim());
		model.setTitle(model.getTitle()== null ? "" : model.getTitle().trim());
		
		return errors;
	}

}
