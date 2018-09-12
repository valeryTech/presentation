package tech.valery.presentation.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import tech.valery.presentation.model.Performance;

public class PerformanceValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Performance.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "perftime", "perftime.empty");
    }
}
