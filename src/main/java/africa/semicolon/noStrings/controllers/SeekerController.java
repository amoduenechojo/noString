package africa.semicolon.noStrings.controllers;

import africa.semicolon.noStrings.dtos.RegisterSeekerRequest;
import africa.semicolon.noStrings.dtos.RegisterSeekerResponse;
import africa.semicolon.noStrings.services.SeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class  SeekerController {
        @Autowired
        private SeekerService seekerService;

        @PostMapping("/seekers")
        public ResponseEntity<RegisterSeekerResponse> register(@RequestBody RegisterSeekerRequest request){
            RegisterSeekerResponse response = seekerService.register(request);

        return ResponseEntity.ok(response);
    }

}
