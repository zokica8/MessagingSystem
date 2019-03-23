package kurs.messaging.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import kurs.messaging.beans.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidationService {
	
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	private Set<ConstraintViolation<User>> violations;

	public void validateUser(User user) {
		
		violations = validator.validate(user);
		
		for(ConstraintViolation<User> violation : violations) {
			log.error(violation.getMessage() + " " + violation.getPropertyPath());
		}
		
	}
	
	

}
