package com.example.blog_app;

import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public class BlogController {
  private final BlogService blogService;

  public BlogController(BlogService blogService){
    this.blogService = blogService;
  }
  @GetMapping("/blogs")
  public String blogs() {
    return "blogs";
  }

  @GetMapping("/blogs")
  public String blogs(@RequestParam(required = false) String keyword, Model model) {
    model.addAttribute("blogs", blogService.search(keyword));
    return "blogs";
  }

  @GetMapping("/blogs/{id}")
  public String detail(@PathVariable Long id, Model model){
    Optional<Blog> blogOpt = blogService.findById(id);
    if (blogOpt.isEmpty()) {
      return "redirect:/blogs";
    }
    model.addAttribute(blogOpt.get());
    return "blogs/detail";
  }

}
