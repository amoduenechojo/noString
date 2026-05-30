package africa.semicolon.noStrings.services;

import africa.semicolon.noStrings.dtos.RegisterSeekerRequest;
import africa.semicolon.noStrings.dtos.RegisterSeekerResponse;
import org.springframework.beans.factory.annotation.Autowired;


public interface SeekerService {
    RegisterSeekerResponse register(RegisterSeekerRequest request);
}