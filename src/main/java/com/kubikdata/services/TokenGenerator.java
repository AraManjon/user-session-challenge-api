package com.kubikdata.services;

public interface TokenGenerator {
  String code(String parameter);
  String decode(String token);
}
