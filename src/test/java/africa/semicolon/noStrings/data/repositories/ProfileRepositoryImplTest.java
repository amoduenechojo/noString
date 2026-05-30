//package africa.semicolon.noStrings.data.repositories;
//
//import africa.semicolon.noStrings.data.models.Profile;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ProfileRepositoryImplTest {
//
//@Autowired
//    private ProfileRepository repository;
//
//    @BeforeEach
//    void setUp() {
//        repository.deleteAll();
//    }
//
//    @Test
//    public void testThatUserCanCreateNewProfile(){
//        Profile newProfile = new Profile("Chojo", "Backend Engineer");
//
//        Profile savedProfile = repository.save(newProfile);
//        assertNotNull(savedProfile);
//    }
//
//    @Test
//    public void testThatMultiplesProfilesCanBeCreated_allProfilesAreSaved(){
//        Profile firstProfile = new Profile("Chojo", "Backend Engineer");
//        Profile secondProfile = new Profile("Inikpi", "Programs Lead");
//
//        assertNotNull(repository.save(firstProfile));
//        assertNotNull(repository.save(secondProfile));
//    }
//
//    @Test
//    void testThatProfileCanBeSavedAndFound() {
//        Profile newProfile = new Profile("Chojo", "Backend Engineer");
//
//        Profile saved = repository.save(newProfile);
//        assertNotNull(saved);
//
//        Profile found = repository.findById(saved.getProfileId());
//        assertNotNull(found);
//        assertEquals("Chojo", found.getUsername());
//    }
//
//    @Test
//    void testUpdateExistingProfile() {
//        Profile profile = new Profile("Chojo", "Backend Engineer");
//        repository.save(profile);
//
//        profile.updateBio("Cooking.");
//        repository.save(profile);
//
//        Profile found = repository.findById(profile.getProfileId());
//        assertEquals("Cooking.", found.getBio());
//    }
//
//    @Test
//    void testDeleteProfile() {
//        Profile profile = new Profile("Chojo.", "BackEnd Engineer");
//        repository.save(profile);
//        String targetId = profile.getProfileId();
//
//        boolean isDeleted = repository.delete(targetId);
//        assertTrue(isDeleted);
//
//        Profile found = repository.findById(targetId);
//        assertNull(found);
//    }
//}