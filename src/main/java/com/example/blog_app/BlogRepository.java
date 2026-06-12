package com.example.blog_app;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
  private final JdbcClient jdbcClient;

  public BlogRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  public List<Blog> findAll() {
    return jdbcClient.sql("SELECT id, user_name, title, content FROM blogs")
        .query(Blog.class)
        .list();
  }

  public List<Blog> searchByTitle(String keyword) {
    return jdbcClient.sql("SELECT id, user_name,  title, content FROM blogs WHERE title LIKE :keyword")
        .param("keyword", "%" + keyword + "%")
        .query(Blog.class)
        .list();
  }

  public void save(Blog blog) {
    jdbcClient.sql("INSERT INTO blogs (user_name, title, content) VALUES (:user_name, :title, :content)")
        .param("user_name", blog.getUser_name())
        .param("title", blog.getTitle())
        .param("content", blog.getContent())
        .update();
  }

  public void delete(Long id) {
    jdbcClient.sql("Delete FROM blogs WHERE id = :id")
        .param("id", id)
        .update();
  }

  public Optional<Blog> findById(Long id) {
    return jdbcClient.sql("SELECT id, user_name, title, content FROM blogs WHERE id = :id")
        .param("id", id)
        .query(Blog.class)
        .optional();
  }

  public void update(Long id,String user_name, String title, String content) {
    jdbcClient.sql("UPDATE blogs SET user_name = :user_name, title = :title, content = :content WHERE id = :id")
        .param("user_name", user_name)
        .param("title", title)
        .param("content", content)
        .param("id", id)
        .update();
  }

  public void deleteById(Long id){
    jdbcClient.sql("DELETE FROM blogs WHERE id = :id")
        .param("id", id)
        .update();
  }

}
