package com.kubikdata.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubikdata.controllers.request.UserSessionRequest;
import com.kubikdata.controllers.response.UserDataResponse;
import com.kubikdata.domain.infrastructure.Repository;
import com.kubikdata.domain.infrastructure.TimeServer;
import com.kubikdata.domain.infrastructure.TokenGenerator;
import com.kubikdata.utils.TokenTestFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.Random;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserDataControllerEndToEndShould {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private Repository repository;

  @MockBean
  private TokenGenerator tokenUsernameGenerator;

  @MockBean
  private TimeServer timeDataServer;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  private void createDummyRepository(String username, String token, Date date) throws Exception {

    UserSessionRequest userSessionRequest = new UserSessionRequest();
    userSessionRequest.setUsername(username);
    when(timeDataServer.generate()).thenReturn(date);
    when(tokenUsernameGenerator.code(username)).thenReturn(token);

    this.mockMvc.perform(post("/session").contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(userSessionRequest))).andDo(print()).andExpect(status().is(200))
        .andReturn();
  }

  @Test
  public void find_user_data_correctly() throws Exception {

    String username = "username";
    Date date = new Date();
    String token = TokenTestFactory.createBy(username);
    createDummyRepository(username, token, date);
    UserDataResponse userDataResponseExpected = new UserDataResponse(username, token, date);

    MvcResult result = this.mockMvc.perform(get("/info/"+username+"/"+token))
        .andDo(print()).andExpect(status().is(200))
        .andReturn();
    String resultAsString = result.getResponse().getContentAsString();
    UserDataResponse userDataResponse = objectMapper.readValue(resultAsString, UserDataResponse.class);

    Assertions.assertNotNull(resultAsString);
    Assertions.assertEquals(userDataResponseExpected, userDataResponse);
  }

  @Test
  public void throw_an_error_if_user_session_is_not_found() throws Exception {

    String username = "usernameRandom";
    String token = TokenTestFactory.createBy(username);

    MvcResult result = this.mockMvc.perform(get("/info/"+username+"/"+token))
        .andDo(print()).andExpect(status().isMethodNotAllowed())
        .andReturn();
    String resultAsString = result.getResponse().getContentAsString();

    Assertions.assertNotNull(resultAsString);
    Assertions.assertEquals("Session not found", resultAsString);
  }

  @Test
  public void throw_an_error_if_username_is_not_valid() throws Exception {

    String username = "_+@";
    String token = TokenTestFactory.createBy(username);

    MvcResult result = this.mockMvc.perform(get("/info/"+username+"/"+token))
        .andDo(print()).andExpect(status().isBadRequest())
        .andReturn();
    String resultAsString = result.getResponse().getContentAsString();

    Assertions.assertNotNull(resultAsString);
    Assertions.assertEquals("Username not valid", resultAsString);
  }

  @Test
  public void throw_an_error_if_token_is_not_valid() throws Exception {

    String username = "username";
    String token = "token";

    MvcResult result = this.mockMvc.perform(get("/info/"+username+"/"+token))
        .andDo(print()).andExpect(status().isBadRequest())
        .andReturn();
    String resultAsString = result.getResponse().getContentAsString();

    Assertions.assertNotNull(resultAsString);
    Assertions.assertEquals("Token not valid", resultAsString);
  }
}
