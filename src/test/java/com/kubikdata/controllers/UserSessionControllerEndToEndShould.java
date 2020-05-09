package com.kubikdata.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubikdata.controller.request.UserSessionRequest;
import com.kubikdata.controller.response.UserSessionResponse;
import com.kubikdata.services.TokenGenerator;
import com.kubikdata.services.TokenUsernameGenerator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserSessionControllerEndToEndShould {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private TokenGenerator tokenUsernameGenerator;

  @Test
  public void create_a_token_when_add_a_session_correctly() throws Exception {

    String username = new Random().toString();
    UserSessionRequest userSessionRequest = new UserSessionRequest();
    userSessionRequest.setUsername(username);
    TokenUsernameGenerator generator = new TokenUsernameGenerator();
    String jsonRequest = new ObjectMapper().writeValueAsString(userSessionRequest);

    MvcResult result = this.mockMvc.perform(post("/session").contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest)).andDo(print()).andExpect(status().is(200))
        .andReturn();

    String resultAsString = result.getResponse().getContentAsString();
    UserSessionResponse sessionResponse = objectMapper.readValue(resultAsString, UserSessionResponse.class);
    Assertions.assertNotNull(resultAsString);
    Assertions.assertEquals(username, generator.decode(sessionResponse.getToken()).getSubject());
  }
}
