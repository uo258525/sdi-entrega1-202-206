package com.uniovi.validators;

import com.uniovi.entities.User;
import com.uniovi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class SignUpFormValidator implements Validator {
	@Autowired
	private UserService usersService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty");
	
		if (usersService.getUserByEmail(user.getEmail()) != null) {
			errors.rejectValue("emal", "Error.signup.email.duplicate");
		}
		if (user.getName().length() < 5 || user.getName().length() > 24) {
			errors.rejectValue("name", "Error.signup.name.length");
		}
		if (user.getSurname().length() < 5 || user.getSurname().length() > 24) {
			errors.rejectValue("surname", "Error.signup.surname.length");
		}
		if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
			errors.rejectValue("password", "Error.signup.password.length");
		}
		if (!user.getPassword2().equals(user.getPassword())) {
			errors.rejectValue("password2", "Error.signup.password2.coincidence");
		}
	}
}