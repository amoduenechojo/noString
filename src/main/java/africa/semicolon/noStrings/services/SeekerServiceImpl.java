package africa.semicolon.noStrings.services;

import africa.semicolon.noStrings.data.models.Seeker;
import africa.semicolon.noStrings.data.repositories.SeekerRepository;
import africa.semicolon.noStrings.dtos.RegisterSeekerRequest;
import africa.semicolon.noStrings.dtos.RegisterSeekerResponse;
import africa.semicolon.noStrings.exceptions.customException.*;
import africa.semicolon.noStrings.mapper.SeekerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.regex.Pattern;

@Service
public class SeekerServiceImpl implements SeekerService {

    @Autowired
    private SeekerRepository seekerRepository;

        @Override
        public RegisterSeekerResponse register(RegisterSeekerRequest request) {

            if (request == null){
                throw new EmptyRequestFormFound("Request form cannot be empty");
            }

            if (usernameExists(request.getUsername())) {
                throw new DuplicateUsernameFound("Username already exists");
            }

            if (emailExists(request.getEmail())) {
                throw new DuplicateEmailFound("Email already exists");
            }

            if (phoneNumberExists(request.getPhoneNumber())) {
                throw new DuplicatePhoneNumberFound("Phone number already exists");
            }

            if (request.getUsername() == null || request.getUsername().isEmpty()) {
                throw new EmptyRequestFormFound("Username cannot be empty");
            }

            if (request.getEmail() == null || request.getEmail().isEmpty()) {
                throw new EmptyRequestFormFound("Email cannot be empty");
            }


            if(request.getPassword() == null || request.getPassword().isEmpty()){
                throw new EmptyRequestFormFound("Password cannot be empty");
            }


            if(request.getGender() == null){
                throw new EmptyRequestFormFound("Gender must be specified");
            }

            validateGmailFormat(request.getEmail());
            validatePassword(request.getPassword());

            Seeker seeker = SeekerMapper.toEntity(request);
            seekerRepository.save(seeker);

            return SeekerMapper.toRegisterResponse(seeker);

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

//            String gmailFormat = "^(?=.{6,30}@gmail\\.com$)[a-zA-Z0-9.]+@gmail.com$";

            if(!Pattern.matches(gmailFormat, email)){
              throw new InvalidEmailException("Invalid email address.");
          }
        }


        public void validatePassword(String password) {
            if (password == null) {
                throw new EmptyRequestFormFound("Password cannot be empty");

            }

            if (password.length() < 6) {
                throw new WeakPasswordException("Password is too short! Must be at least 6 characters.");
            }

            boolean hasUppercase = false;
            boolean hasDigit = false;


            for (int index = 0; index < password.length(); index++) {
                char character = password.charAt(index);

                if (Character.isUpperCase(character)) {
                    hasUppercase = true;
                } else if (Character.isDigit(character)) {
                    hasDigit = true;
                }
            }

            if (!hasUppercase) {
                throw new ValidationException("Password must contain at least one uppercase letter.");
            }

            if (!hasDigit) {
                throw new ValidationException("Password must contain at least one number.");
            }
        }
    }
