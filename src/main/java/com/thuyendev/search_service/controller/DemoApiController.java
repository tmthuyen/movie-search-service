package com.thuyendev.search_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DemoApiController {

  @GetMapping("/public/hello")
  public ResponseEntity<Map<String, String>> publicHello() {
    return ResponseEntity.ok(Map.of("message", "Đây là API public - không cần đăng nhập"));
  }

  @GetMapping("/protected/data")
  public ResponseEntity<Map<String, String>> protectedData() {
    return ResponseEntity.ok(Map.of("message", "Dữ liệu được bảo vệ - yêu cầu xác thực API"));
  }
}
