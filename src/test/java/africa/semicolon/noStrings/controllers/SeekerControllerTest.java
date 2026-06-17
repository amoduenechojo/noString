package africa.semicolon.noStrings.controllers;

import africa.semicolon.noStrings.data.repositories.SeekerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@AutoConfigureMockMvc
public class SeekerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SeekerRepository seekerRepository;

    @BeforeEach
    void setUp() {
        seekerRepository.deleteAll();
    }

    @Test
    public void testThatValidRegistration_givesA200() throws Exception {
        String userInformation = """
        {
            "username" : "Deborah",
            "password":"Password1",
            "email":"deborah@gmail.com",
            "phoneNumber": "09132332314",
            "gender":"FEMALE"
        }
        """;

        mockMvc.perform(
                        post("/api/seekers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userInformation))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.successful").value(true));
    }

    @Test
    public void testThatDuplicateUsername_gives400() throws Exception {
        String userInformation = """
        {
            "username" : "Deborah",
            "password":"Zassword1",
            "email":"jakarata@gmail.com",
            "phoneNumber": "08020438903",
            "gender":"MALE"
        }
        """;


        mockMvc.perform(
                        post("/api/seekers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userInformation))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.successful").value(true));


        mockMvc.perform(
                        post("/api/seekers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userInformation))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.successful").value(false));
    }

    @Test
    public void testThatDuplicateEmail_gives400() throws Exception {
        String userInformation1 = """
        {
            "username" : "Eneojo",
            "password":"Ertion26",
            "email":"jakarata@gmail.com",
            "phoneNumber": "08020528889",
            "gender":"MALE"
        }
        """;

        mockMvc.perform(
                        post("/api/seekers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userInformation1))
                .andExpect(status().isOk());

        String userInformation2 = """
        {
            "username" : "DifferentUsername",
            "password":"Ertion26",
            "email":"jakarata@gmail.com",
            "phoneNumber": "08020528881",
            "gender":"MALE"
        }
        """;


        mockMvc.perform(
                        post("/api/seekers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userInformation2))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.successful").value(false));
    }

    @Test
    public void testThatDuplicatePhoneNumber_gives400() throws Exception {
        String userInformation1 = """
        {
            "username" : "Deborah",
            "password":"Zassword1",
            "email":"jakarata@gmail.com",
            "phoneNumber": "08020528889",
            "gender":"MALE"
        }
        """;

        mockMvc.perform(
                        post("/api/seekers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userInformation1))
                .andExpect(status().isOk());

        String userInformation2 = """
        {
            "username" : "DifferentName",
            "password":"Zassword1",
            "email":"different@gmail.com",
            "phoneNumber": "08020528889",
            "gender":"MALE"
        }
        """;

        mockMvc.perform(
                        post("/api/seekers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userInformation2))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.successful").value(false));
    }

    @Test
    public void testThatEmptyUsername_gives400() throws Exception {
        String userInformation = """
        {
            "username" : "",
            "password":"Zassword1",
            "email":"jakarata@gmail.com",
            "phoneNumber": "08020528889",
            "gender":"MALE"
        }
        """;


        mockMvc.perform(
                        post("/api/seekers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userInformation))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.successful").value(false));
    }

    @Test
    public void testThatEmptyEmail_gives400() throws Exception {
        String userInformation = """
        {
            "username" : "Deborah",
            "password":"Zassword1",
            "email":"",
            "phoneNumber": "08020528889",
            "gender":"MALE"
        }
        """;

        mockMvc.perform(
                        post("/api/seekers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userInformation))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.successful").value(false));
    }

    @Test
    public void testThatEmptyPassword_gives400() throws Exception {
        String userInformation = """
        {
            "username" : "Deborah",
            "password":"",
            "email":"jakarata@gmail.com",
            "phoneNumber": "08020528889",
            "gender":"MALE"
        }
        """;

        mockMvc.perform(
                        post("/api/seekers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userInformation))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.successful").value(false));
    }

    @Test
    public void testThatNoGender_gives400() throws Exception {
        String userInformation = """
        {
            "username" : "Deborah",
            "password":"Password123",
            "email":"jakarata@gmail.com",
            "phoneNumber": "08020528889",
        }
        """;

        mockMvc.perform(
                        post("/api/seekers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userInformation))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.successful").value(false));
    }

    @Test
    public void testThatWeakPassword_gives400() throws Exception {
        String userInformation = """
        {
            "username" : "Deborah",
            "password":"Ab1",
            "email":"jakarata@gmail.com",
            "phoneNumber": "08020528889",
            "gender":"MALE"
        }
        """;

        mockMvc.perform(
                        post("/api/seekers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userInformation))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.successful").value(false));
    }


    @Test
    public void testThatPasswordWithoutUppercase_gives400() throws Exception {
        String userInformation = """
        {
            "username" : "Deborah",
            "password":"password1",
            "email":"jakarata@gmail.com",
            "phoneNumber": "08020528889",
            "gender":"MALE"
        }
        """;

        mockMvc.perform(
                        post("/api/seekers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userInformation))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.successful").value(false));
    }


    @Test
    public void testThatPasswordWithoutDigit_gives400() throws Exception {
        String userInformation = """
        {
            "username" : "Deborah",
            "password":"password",
            "email":"jakarata@gmail.com",
            "phoneNumber": "08020528889",
            "gender":"MALE"
        }
        """;

        mockMvc.perform(
                        post("/api/seekers/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userInformation))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.successful").value(false));
    }
}