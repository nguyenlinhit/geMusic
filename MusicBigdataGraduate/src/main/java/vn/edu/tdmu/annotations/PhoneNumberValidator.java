package vn.edu.tdmu.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Created by NguyenLinh on 01/29/2018
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String>{

	@Override
	public void initialize(PhoneNumber constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
            if (value == null || value.isEmpty()){
                return true;
            }

            Double.parseDouble(value);
            if (value.length() > 9 && value.length() < 13){
                return true;
            }
        } catch (NumberFormatException e) {
            //TODO: handle exception
            return false;
        }
		return false;
	}

}