package africa.semicolon.noStrings.Services;

import africa.semicolon.noStrings.data.models.Gender;
import africa.semicolon.noStrings.data.models.Seeker;
import africa.semicolon.noStrings.data.repositories.SeekerRepository;

import africa.semicolon.noStrings.dtos.RegisterSeekerRequest;
import africa.semicolon.noStrings.dtos.RegisterSeekerResponse;
import africa.semicolon.noStrings.exceptions.customException.*;
import africa.semicolon.noStrings.services.SeekerService;
import africa.semicolon.noStrings.services.SeekerServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class SeekerServiceTest {

    @Autowired
    private SeekerService seekerService;


    @Autowired
    private SeekerRepository seekerRepository;


    @BeforeEach
    void setUp() {
        seekerRepository.deleteAll();
    }

    @Test
    public void testThatUserCanRegisterSuccessfully() {
        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "StrongP@ssword1", "chojo@backend.com", "08123456789", Gender.FEMALE);

        RegisterSeekerResponse response = seekerService.register(request);

        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertEquals("Registration successful!", response.getMessage());
        assertEquals(1, seekerRepository.count());
    }

    @Test
    public void testThatValidSeekerCanBeSaved() {

        Seeker seeker = new Seeker();
        seeker.setUsername("chojo");
        seeker.setEmail("chojo@backend.com");
        seeker.setPassword("password123");


        Seeker savedSeeker = seekerRepository.save(seeker);


        assertNotNull(savedSeeker);
//        assertNotNull(savedSeeker.getId());

        assertEquals("chojo", savedSeeker.getUsername());
        assertEquals(1, seekerRepository.count());
    }


    @Test
    public void testThatExistingEmailsAlreadyRegistered_duplicateEmailsNotAllowed_exceptionIsThrown() {

        RegisterSeekerRequest firstRequest = new RegisterSeekerRequest("Chojo", "StrongP@ssword1", "chojo@backend.com","08123456789",  Gender.FEMALE);
        seekerService.register(firstRequest);

        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "StrongP@ssword1", "chojo@backend.com", "08125630570", Gender.FEMALE) ;

        assertThrows(DuplicateEmailFound.class, () -> {
            seekerService.register(request);
        });
    }



    @Test
    public void testThatExistingUsernameAlreadyRegistered_duplicateUsernameIsNotAllowed_exceptionIsThrown() {

        RegisterSeekerRequest firstRequest = new RegisterSeekerRequest("Chojo","StrongP@ssword1" , "chojo@backend.com", "08123456789", Gender.FEMALE);
        seekerService.register(firstRequest);

        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo","P@ssword456" , "eamodu92@gmail.com", "08155555555", Gender.FEMALE);

        assertThrows(DuplicateUsernameFound.class, () -> {
            seekerService.register(request);
        });
    }


    @Test
    public void testThatExistingPhoneNumberAlreadyRegistered_duplicatePhoneNumberIsNotAllowed_exceptionIsThrown() {

        RegisterSeekerRequest firstRequest = new RegisterSeekerRequest("Chojo", "StrongP@ssword1", "chojo@gmail.com", "08123456789", Gender.FEMALE);
        seekerService.register(firstRequest);

        RegisterSeekerRequest request = new RegisterSeekerRequest("Afor", "StrongP@ssword2", "amodumartha7@gmail.com", "08123456789", Gender.FEMALE);

        assertThrows(DuplicatePhoneNumberFound.class, () -> {
            seekerService.register(request);
        });
    }


    @Test
    public void testThatEmptyRegistrationFormsCannotBeSubmitted(){

        RegisterSeekerRequest request = null;

        assertThrows(EmptyRequestFormFound.class, () -> {
            seekerService.register(null);
        });
    }


    @Test
    public void TestThatRegistrationFailsWhenUsernameIsMissing(){

        RegisterSeekerRequest request = new RegisterSeekerRequest("", "chojo@backend.com", "password123", "08123456789", Gender.FEMALE);

        assertThrows(IllegalArgumentException.class, () -> {
            seekerService.register(request);
        });
    }


    @Test
    public void testThatRegistrationFailsWhenEmailIsMissing(){

        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "   ", "password123", "08123456789", Gender.FEMALE);

        assertThrows(IllegalArgumentException.class, () -> {
            seekerService.register(request);
        });
    }


    @Test
    public void testThatRegistrationFailsWhenPasswordIsMissing(){

        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "chojo@backend.com", null, "08123456789", Gender.FEMALE);

        assertThrows(IllegalArgumentException.class, () -> {
            seekerService.register(request);
        });
    }


    @Test
    public void testThatRegistrationFailsWhenGenderIsNotSelected(){

        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "chojo@backend.com", "password123", "08123456789", null);

        assertThrows(IllegalArgumentException.class, () -> {
            seekerService.register(request);
        });
    }





    @ParameterizedTest
    @ValueSource(strings = {"abc@gmail.com", "thisusernameiswaytoolongforgmailaccounts@gmail.com", "chojo@yahoo.com", "chojo.gmail.com", "chojo@gmail", "   "})
    public void testThatWhenUserEntersInvalidEmailFormat_anErrorOccurs(String invalidEmail){


        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "StrongP@ssword1", invalidEmail, "08123456789", Gender.FEMALE);

        assertThrows(InvalidEmailException.class, ()->{
            seekerService.register(request);
        });
    }



    @ParameterizedTest
    @ValueSource(strings = {"nouppercase123", "NoNumberHere", "no_digits_or_caps"})

    public void testThatPasswordsMissingRequiredCharactersThrowValidationException(String invalidPassword){

        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", invalidPassword, "chojo@backend.com", "08123456789", Gender.FEMALE);

        assertThrows(ValidationException.class, ()->{
            seekerService.register(request);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "short", "aB1"})
    public void testThatPasswordsBelowMinimumLengthThrowWeakPasswordException(String shortPassword) {
        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", shortPassword, "chojo@gmail.com", "08123456789", Gender.FEMALE);

        assertThrows(WeakPasswordException.class, () -> {
            seekerService.register(request);
        });
    }

    @Test
    public void testThatStrongPasswordRegistrationSucceeds() {
        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "StrongP@ssword1", "chojo@gmail.com", "08123456789", Gender.FEMALE
        );

        RegisterSeekerResponse response = seekerService.register(request);
        assertNotNull(response);
        assertTrue(response.isSuccessful());
    }
}