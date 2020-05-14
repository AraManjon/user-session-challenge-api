package com.kubikdata.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubikdata.controllers.request.UserSessionRequest;
import com.kubikdata.controllers.response.UserSessionResponse;
import com.kubikdata.domain.infrastructure.Repository;
import com.kubikdata.domain.infrastructure.TimeServer;
import com.kubikdata.domain.infrastructure.TokenGenerator;
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

  @Autowired
  private TimeServer timeDataServer;

  @Autowired
  private Repository inMemoryUserSessionRepository;

  @Test
  public void create_a_token_when_add_a_user_session_correctly() throws Exception {

    String username = "username";
    UserSessionRequest userSessionRequest = new UserSessionRequest();
    userSessionRequest.setUsername(username);
    TokenUsernameGenerator generator = new TokenUsernameGenerator();
    String jsonRequest = new ObjectMapper().writeValueAsString(userSessionRequest);

    MvcResult result = this.mockMvc.perform(post("/session").contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest)).andDo(print()).andExpect(status().is(200))
        .andReturn();
    String resultAsString = result.getResponse().getContentAsString();
    UserSessionResponse userSessionResponse = objectMapper.readValue(resultAsString, UserSessionResponse.class);

    Assertions.assertNotNull(resultAsString);
    Assertions.assertTrue(generator.decode(userSessionResponse.getToken(), username));
  }

  @Test
  public void throw_an_error_when_username_is_not_valid() throws Exception {

    String username = "_+@";
    UserSessionRequest userSessionRequest = new UserSessionRequest();
    userSessionRequest.setUsername(username);
    String jsonRequest = new ObjectMapper().writeValueAsString(userSessionRequest);

    MvcResult result = this.mockMvc.perform(post("/session").contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest)).andDo(print()).andExpect(status().isBadRequest())
        .andReturn();
    String resultAsString = result.getResponse().getContentAsString();

    Assertions.assertNotNull(resultAsString);
    Assertions.assertEquals("Username not valid", resultAsString);
  }
}
