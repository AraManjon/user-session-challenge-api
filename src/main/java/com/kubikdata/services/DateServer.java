package com.kubikdata.services;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateServer implements TimeServer {
  @Override
  public Date generate() {
    return new Date();
  }
}
