package com.kubikdata.infrastructure;

import com.kubikdata.domain.dto.DTO;
import com.kubikdata.domain.valueobjects.Username;
import com.kubikdata.infrastructure.sql.SQLSessionRepository;
import com.kubikdata.utils.TokenTestFactory;
import com.kubikdata.utils.UserSessionDTOTestFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = {"/schema.sql"})
public class SQLSessionRepositoryShould {

  @Autowired
  SQLSessionRepository sqlSessionRepository;

  @Test
  public void add_and_find_userSession_correctly() {

    String username = "username";
    String token = TokenTestFactory.createBy(username);
    DTO.UserSession userSessionDTO = UserSessionDTOTestFactory.create(username, token);
    Optional<DTO.UserSession> userSessionExpected = Optional.of(userSessionDTO);

    sqlSessionRepository.add(userSessionDTO);
    Optional<DTO.UserSession> result = sqlSessionRepository.find(new Username(username));

    Assert.assertEquals(userSessionExpected, result);
  }

  @Test
  public void return_empty_when_not_find_userSession() {

    String username = "usernameNotFound";
    Optional<DTO.UserSession> result = sqlSessionRepository.find(new Username(username));

    Assert.assertEquals(Optional.empty(), result);
  }

  @Test
  public void remove_userSession_from_a_list_correctly(){

    String username = "usernameToDelete";
    String token = TokenTestFactory.createBy(username);
    DTO.UserSession userSession = UserSessionDTOTestFactory.create(username, token);

    sqlSessionRepository.add(userSession);
    sqlSessionRepository.remove(new Username(username));

    Assert.assertEquals(Optional.empty(), sqlSessionRepository.find(new Username(username)));

  }
}
