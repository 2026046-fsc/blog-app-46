package com.example.blog_app;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BlogController {
  private final BlogService blogService;

  public BlogController(BlogService blogService){
    this.blogService = blogService;
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
    model.addAttribute("blog", blogOpt.get());
    return "blogs/detail";
  }
  @GetMapping("/post")
  public String postForm() {
      return "post";
  }
  @GetMapping("mypage")
  public String mypage() {
      return "mypage";
  }
  @PostMapping("/blogs")
  public String create(@ModelAttribute Blog blog) {
      blogService.save(blog);
      return "redirect:/blogs";
  }
  
  @GetMapping("/books/{id}/edit")
  public String editForm(@PathVariable Long id, Model model) {
      Optional<Blog> blogOpt = blogService.findById(id);
      if (blogOpt.isEmpty()) {
        return "redirect:/blogs";
      }
      Blog blog = blogOpt.get();

      BlogForm form = new BlogForm();
      form.setTitle(blog.getTitle());
      form.setContent(blog.getContent());
      model.addAttribute("blogForm", form);
      model.addAttribute("blogId", id);
      return "blogs/edit";
  }
  
  

}
