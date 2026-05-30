package africa.semicolon.noStrings.services;

import africa.semicolon.noStrings.data.models.Seeker;
import africa.semicolon.noStrings.data.repositories.SeekerRepository;
import africa.semicolon.noStrings.dtos.RegisterSeekerRequest;
import africa.semicolon.noStrings.dtos.RegisterSeekerResponse;
import africa.semicolon.noStrings.exceptions.customException.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class SeekerServiceImpl implements SeekerService {

    @Autowired
    private SeekerRepository seekerRepository;

        @Override
        public RegisterSeekerResponse register(RegisterSeekerRequest request) {

            String password = request.getPassword();
            boolean hasUppercase = false;
            boolean hasDigit = false;


            if (usernameExists(request.getUsername())) {
                throw new IllegalArgumentException("Username already exists");
            }

            if (emailExists(request.getEmail())) {
                throw new DuplicateEmailFound("Email already exists");
            }

            if (phoneNumberExists(request.getPhoneNumber())) {
                throw new DuplicatePhoneNumberFound("Phone number already exists");
            }

            if (request == null){
                throw new EmptyRequestFormFound("Request form cannot be empty");
            }


            if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
                throw new IllegalArgumentException("Username cannot be empty");
            }

            if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be empty");
            }


            if(request.getPassword() == null || request.getPassword().trim().isEmpty()){
                throw new IllegalArgumentException("Password cannot be empty");
            }


            if(request.getGender() == null){
                throw new IllegalArgumentException("Gender must be specified");
            }

            if(password == null){
                throw new IllegalArgumentException("Password must be specified");
            }

            if (password.length() < 6) {
                throw new WeakPasswordException("Password is too short! Must be at least 6 characters.");
            }

            for (int index = 0; index < password.length(); index++) {
                char character = password.charAt(index); 

                if (Character.isUpperCase(character)) {
                    hasUppercase = true;
                } 
                
                else if (Character.isDigit(character)) {
                    hasDigit = true;
                }
            }

            if (!hasUppercase) {
                throw new ValidationException("Password must contain at least one uppercase letter.");
            }
            if (!hasDigit) {
                throw new ValidationException("Password must contain at least one number.");
            }
            return null;
        }


        private boolean usernameExists(String username) {
            for (Seeker seeker : seekerRepository.findAll()) {
                if (seeker.getUsername().equalsIgnoreCase(username)) {
                    return true;
                }
            }
            return false;
        }

        private boolean emailExists(String email) {
            if (email == null) return false;
            for (Seeker seeker : seekerRepository.findAll()) {
                if (seeker.getEmail() != null && seeker.getEmail().equalsIgnoreCase(email)) {
                    return true;
                }
            }
            return false;
        }

        private boolean phoneNumberExists(String phoneNumber) {
            if (phoneNumber == null) return false;
            for (Seeker seeker : seekerRepository.findAll()) {
                if (seeker.getPhoneNumber() != null && seeker.getPhoneNumber().equals(phoneNumber)) {
                    return true;
                }
            }
            return false;
        }

        public void validateGmailFormat(String email){
            String gmailFormat = "^(?=.{6,30}@gmail\\.com$)[a-zA-Z0-9.]+@gmail.com$";

          if(email == null){
              throw new EmptyRequestFormFound("Email cannot be empty");
          }

          if(!Pattern.matches(gmailFormat,  email)){
              throw new InvalidEmailException("Invalid email address, username should be between 6-30 characters.");
          }
        }
    }
