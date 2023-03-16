package com.example.digicertdemoapi.dto;

import lombok.Data;

@Data
public class RequestMetadata {
  private Long userId;
  private String userName;
  private String emailId;
  private String userType;

}

