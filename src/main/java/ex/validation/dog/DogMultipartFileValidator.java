package ex.validation.dog;

import ex.model.binding.AddDogBindingModel;
import ex.validation.ValidationConstants;
import ex.validation.annotation.Validator;
import org.springframework.validation.Errors;


@Validator
public class DogMultipartFileValidator implements org.springframework.validation.Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return AddDogBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AddDogBindingModel doctorUpdateBindingModel = (AddDogBindingModel) o;

        if (doctorUpdateBindingModel.getImage().isEmpty()) {
            errors.rejectValue(
                    "image",
                    ValidationConstants.PROFILE_PICTURE_NOT_UPLOADED,
                    ValidationConstants.PROFILE_PICTURE_NOT_UPLOADED
            );
        }

    }
}
