package com.example.blog_app;

import org.springframework.web.bind.annotation.GetMapping;

public class BlogController {
  @GetMapping("/blogs")
  public String blogs(){
    return "blogs";
  }
  @PostMapping("/post")
  
}
