package com.evaluation.ec.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void when_call_save_user_then_return_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .content("""
                                {
                                  "name": "John Doe",
                                  "email": "aaaa@dominio.cl",
                                  "password": "Password123",
                                  "phones": [
                                    {
                                      "number": "1234567",
                                      "citycode": "1",
                                      "countrycode": "56"
                                    }
                                  ]
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString("Usuario creado con éxito")));
    }

    @Test
    void get_all_users_then_return_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString("John Doe")));
    }
}
