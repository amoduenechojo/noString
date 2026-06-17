package africa.semicolon.noStrings.services;

import africa.semicolon.noStrings.dtos.RegisterSeekerRequest;
import africa.semicolon.noStrings.dtos.RegisterSeekerResponse;

public interface ProfileService {
    RegisterSeekerResponse register(RegisterSeekerRequest request);
}