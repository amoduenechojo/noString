package africa.semicolon.noStrings.Services;

import africa.semicolon.noStrings.data.models.Gender;
import africa.semicolon.noStrings.data.models.Seeker;
import africa.semicolon.noStrings.data.repositories.SeekerRepository;

import africa.semicolon.noStrings.dtos.RegisterSeekerRequest;
import africa.semicolon.noStrings.dtos.RegisterSeekerResponse;
import africa.semicolon.noStrings.exceptions.customException.*;
import africa.semicolon.noStrings.services.SeekerService;


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
        RegisterSeekerRequest request = new RegisterSeekerRequest("Enechojo", "StrongP@ssword1", "amoduchojo27@gmail.com", "08123456789", Gender.FEMALE);

        RegisterSeekerResponse response = seekerService.register(request);

//        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertEquals("Seeker registered successfully.", response.getMessage());
        assertEquals(1, seekerRepository.count());
    }

    @Test
    public void testThatValidSeekerCanBeSaved() {

        Seeker seeker = new Seeker();
        seeker.setUsername("Enechojo");
        seeker.setEmail("amoduchojo27@gmail.com");
        seeker.setPassword("StrongP@ssword1");


        Seeker savedSeeker = seekerRepository.save(seeker);


        assertNotNull(savedSeeker);
        assertNotNull(savedSeeker.getId());

        assertEquals("Enechojo", savedSeeker.getUsername());
        assertEquals(1, seekerRepository.count());
    }


    @Test
    public void testThatExistingEmailsAlreadyRegistered_duplicateEmailsNotAllowed_exceptionIsThrown() {

        RegisterSeekerRequest firstRequest = new RegisterSeekerRequest("Enechojo", "StrongP@ssword1", "amoduchojo27@gmail.com","08123456789",  Gender.FEMALE);
        seekerService.register(firstRequest);

        RegisterSeekerRequest request = new RegisterSeekerRequest("Chairman", "CupidsP@ssword1", "amoduchojo27@gmail.com", "08125630570", Gender.MALE) ;

        assertThrows(DuplicateEmailFound.class, () -> seekerService.register(request));
    }



    @Test
    public void testThatExistingUsernameAlreadyRegistered_duplicateUsernameIsNotAllowed_exceptionIsThrown() {

        RegisterSeekerRequest firstRequest = new RegisterSeekerRequest("Enechojo","StrongP@ssword1" , "amoduchojo27@gmail.com", "08123456789", Gender.FEMALE);
        seekerService.register(firstRequest);

        RegisterSeekerRequest request = new RegisterSeekerRequest("Enechojo","P@ssword456" , "eleojoamodu92@gmail.com", "08155555555", Gender.MALE);

        assertThrows(DuplicateUsernameFound.class, () -> seekerService.register(request));

    }


    @Test
    public void testThatExistingPhoneNumberAlreadyRegistered_duplicatePhoneNumberIsNotAllowed_exceptionIsThrown() {

        RegisterSeekerRequest firstRequest = new RegisterSeekerRequest("Enechojo", "StrongP@ssword1", "amoduchojo27@gmail.com", "08123456789", Gender.FEMALE);
        seekerService.register(firstRequest);

        RegisterSeekerRequest request = new RegisterSeekerRequest("Enyojo", "StrongP@ssword2", "amodumartha7@gmail.com", "08123456789", Gender.FEMALE);

        assertThrows(DuplicatePhoneNumberFound.class, () -> seekerService.register(request));

    }


    @Test
    public void testThatEmptyRegistrationFormsCannotBeSubmitted(){

        RegisterSeekerRequest request = null;

        assertThrows(EmptyRequestFormFound.class, () -> seekerService.register(null));
    }


    @Test
    public void TestThatRegistrationFailsWhenUsernameIsMissing(){

        RegisterSeekerRequest request = new RegisterSeekerRequest("", "password123", "chojo@backend.com", "08123456789", Gender.FEMALE);

        assertThrows(EmptyRequestFormFound.class, () -> seekerService.register(request));
    }


    @Test
    public void testThatRegistrationFailsWhenEmailIsMissing(){

        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "password123", "", "08123456789", Gender.FEMALE);

        assertThrows(EmptyRequestFormFound.class, () -> seekerService.register(request));
    }


    @Test
    public void testThatRegistrationFailsWhenPasswordIsMissing(){

        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "", "chojo@backend.com", "08123456789", Gender.FEMALE);

        assertThrows(EmptyRequestFormFound.class, () -> seekerService.register(request));

    }


    @Test
    public void testThatRegistrationFailsWhenGenderIsNotSelected(){

        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "chojo@backend.com", "password123", "08123456789", null);

        assertThrows(EmptyRequestFormFound.class, () -> seekerService.register(request));

    }

    @Test
    public void testRegistration_WhenEmailIsLessThanSixCharacters_ThrowsInvalidEmailException(){
        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "StrongP@ssword1", "abc@gmail.com", "08123456789", Gender.FEMALE);
        assertThrows(InvalidEmailException.class, () -> seekerService.register(request));

    }


    @Test
    public void testRegistration_WhenEmailIsMoreThanThirtyCharacters_ThrowsInvalidEmailException() {
        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "StrongP@ssword1", "thisusernameiswaytoolongforgmailaccounts@gmail.com", "08123456789", Gender.FEMALE);

        assertThrows(InvalidEmailException.class, () -> seekerService.register(request));
    }

    @Test
    public void testRegistration_WhenEmailHasNonGmailDomain_ThrowsInvalidEmailException() {
        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "StrongP@ssword1", "chojo@yahoo.com", "08123456789", Gender.FEMALE);

        assertThrows(InvalidEmailException.class, () -> seekerService.register(request));
    }

    @Test
    public void testRegistration_WhenEmailIsMissingAtSymbol_ThrowsInvalidEmailException() {
        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "StrongP@ssword1", "chojo.gmail.com", "08123456789", Gender.FEMALE);

        assertThrows(InvalidEmailException.class, () -> seekerService.register(request));
    }

    @Test
    public void testRegistration_WhenEmailIsMissingComExtension_ThrowsInvalidEmailException() {
        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "StrongP@ssword1", "chojo@gmail", "08123456789", Gender.FEMALE);

        assertThrows(InvalidEmailException.class, () -> seekerService.register(request));
    }
//
//    @Test
//    public void testRegistration_WhenEmailIsBlankWithSpaces_ThrowsEmptyRequestFormFoundException() {
//        RegisterSeekerRequest request = new RegisterSeekerRequest("Chojo", "StrongP@ssword1", "   ", "08123456789", Gender.FEMALE);
//
//        assertThrows(EmptyRequestFormFound.class, () -> seekerService.register(request));
//    }


    @Test
    public void testThat_WhenThereIsNoUppercaseIn_aPassword_ExceptionIsThrown(){

        RegisterSeekerRequest request = new RegisterSeekerRequest("Enechojo", "nouppercase123", "amoduchojo27@gmail.com", "08123456789", Gender.FEMALE);

        assertThrows(ValidationException.class, ()->seekerService.register(request));

    }

    @Test
    public void testThat_WhenThereIsNoNumberInPassword_ExceptionIsThrown(){
        RegisterSeekerRequest request = new RegisterSeekerRequest("Enechojo", "NoNumberHere", "amoduchojo27@gmail.com", "08123456789", Gender.FEMALE);

        assertThrows(ValidationException.class, ()->seekerService.register(request));

    }

    @Test
    public void testThatWhenPassword_doesNotContain_digitOrCaplock_exceptionIsThrown(){
        RegisterSeekerRequest request = new RegisterSeekerRequest("Enechojo", "no_digits_or_caps", "amoduchojo27@gmail.com", "08123456789", Gender.FEMALE);

        assertThrows(ValidationException.class, ()->seekerService.register(request));

    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "short", "aB1"})
    public void testThatPasswordsBelowMinimumLengthThrowWeakPasswordException(String shortPassword) {
        RegisterSeekerRequest request = new RegisterSeekerRequest("Enechojo", shortPassword, "amoduchojo27@gmail.com", "08123456789", Gender.FEMALE);

        assertThrows(WeakPasswordException.class, () -> seekerService.register(request));
    }

    @Test
    public void testThatStrongPasswordRegistrationSucceeds() {
        RegisterSeekerRequest request = new RegisterSeekerRequest("Enechojo", "StrongP@ssword1", "amoduchojo27@gmail.com", "08123456789", Gender.FEMALE
        );

        RegisterSeekerResponse response = seekerService.register(request);
        assertNotNull(response);
        assertTrue(response.isSuccessful());
    }
}