package com.codefaucet.tms.service.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codefaucet.tms.model.UserRole;
import com.codefaucet.tms.model.dto.UserDTO;
import com.codefaucet.tms.model.service_interface.validator.IValidator;
import com.codefaucet.tms.repository.IUserRepository;

@Component
public class UserValidator implements IValidator<UserDTO> {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public Map<String, String> validate(UserDTO model) {
		var errors = new HashMap<String, String>();

		if (model.getFirstName() == null || model.getFirstName().isBlank()) {
			errors.put("firstName", "First name is required.");
		}
		model.setFirstName(model.getFirstName().trim());
		if (model.getLastName() == null || model.getLastName().isBlank()) {
			errors.put("lastName", "Last name is required.");
		}
		model.setLastName(model.getLastName().trim());
		if (model.getRole() == UserRole.UNKNOWN) {
			errors.put("role", "Invalid user role.");
		}
		if (model.getUsername() == null || model.getUsername().isBlank()) {
			errors.put("username", "Username is required.");
		}
		model.setUsername(model.getUsername().trim());

		if (errors.isEmpty()) {
			var usernameConflict = userRepository.findByUsername(model.getUsername());
			if (usernameConflict != null && usernameConflict.getId() != model.getId()) {
				errors.put("username", "Username '" + model.getUsername() + "' is not available.");
			}
		}

		return errors;
	}

}
