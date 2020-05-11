package com.kubikdata.services;

import com.kubikdata.domain.infrastructure.TimeServer;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateServer implements TimeServer {
  @Override
  public Date generate() {
    return new Date();
  }
}
