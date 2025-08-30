package com.example.demo.service.validator;

import org.springframework.stereotype.Service;

import com.example.demo.domain.DTO.RegisterDTO;
import com.example.demo.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Service
public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {

    private final UserRepository userRepository;

    public RegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {

        boolean valid = true;

        // Check if password fields match

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Passwords nhập không chính xác")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        if (this.userRepository.existsByEmail(user.getEmail())) {
            context.buildConstraintViolationWithTemplate("Email đã tồn tại")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        return valid;
    }
}