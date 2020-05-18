package com.usersession.infrastructure.sql;


import com.usersession.domain.dto.DTO;
import com.usersession.domain.infrastructure.Repository;
import com.usersession.domain.valueobjects.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Component
@Primary
public class SQLSessionRepository implements Repository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public Optional<DTO.UserSession> find(Username username) {

    DTO.UserSession userSessionDTO = new DTO.UserSession();

    try {
      jdbcTemplate.queryForObject("SELECT * FROM usersessions WHERE username=?",
          new Object[]{username.getUsername()},
          (rs, rowNum) -> {
            Timestamp date;
            userSessionDTO.username = rs.getString("username");
            userSessionDTO.token = rs.getString("token");
            date = rs.getTimestamp("session_date");
            userSessionDTO.date = new Date(date.getTime());
            return null;
          });

      return Optional.of(userSessionDTO);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public void add(DTO.UserSession userSessionDTO) {

    Timestamp date = new Timestamp(userSessionDTO.date.getTime());

    jdbcTemplate.update("INSERT INTO usersessions (username, token, session_date) VALUES (?, ?, ?)", userSessionDTO.username, userSessionDTO.token, date);
  }

  @Override
  public void remove(Username username) {

    jdbcTemplate.update("DELETE FROM usersessions WHERE username=?", username.getUsername());
  }
}
