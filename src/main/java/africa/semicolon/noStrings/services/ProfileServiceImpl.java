package africa.semicolon.noStrings.services;

import africa.semicolon.noStrings.data.repositories.ProfileRepository;
import africa.semicolon.noStrings.data.models.Profile;
import africa.semicolon.noStrings.dtos.RegisterSeekerRequest;
import africa.semicolon.noStrings.dtos.RegisterSeekerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl {

    @Autowired
    private ProfileRepository profileRepository;

//    @Override
//    public RegisterSeekerResponse register(RegisterSeekerRequest request) {
//        Profile profile = new Profile(request.getUsername(), request.getBio());
//        Profile savedProfile = profileRepository.save(profile);;
//    }
}