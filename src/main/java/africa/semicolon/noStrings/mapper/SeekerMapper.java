package africa.semicolon.noStrings.mapper;

import africa.semicolon.noStrings.data.models.Seeker;
import africa.semicolon.noStrings.dtos.RegisterSeekerRequest;
import africa.semicolon.noStrings.dtos.RegisterSeekerResponse;

public class SeekerMapper {

    public Seeker toEntity(RegisterSeekerRequest request){
        if (request == null){
            return null;
        }

        Seeker seeker = new Seeker();
        seeker.setUsername(request.getUsername());
        seeker.setPassword(request.getPassword());
        seeker.setEmail(request.getEmail());
        seeker.setPhoneNumber(request.getPhoneNumber());
        seeker.setGender(request.getGender());

    return seeker;
    }


    public RegisterSeekerResponse toRegisterResponse(Seeker savedSeeker){
        if (savedSeeker == null){
            return null;
        }

        RegisterSeekerResponse response = new RegisterSeekerResponse();
        response.setId(String.valueOf(savedSeeker.getId()));
        response.setMessage("Seeker registered successfully.");
        response.setSuccessful(true);

    return response;
    }
}
