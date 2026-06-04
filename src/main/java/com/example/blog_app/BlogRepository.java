package com.example.blog_app;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
  private final JdbcClient jdbcClient;

  public BlogRepository(JdbcClient jdbcClient){
    this.jdbcClient = jdbcClient;
  }

  public List<Blog> findAll(){
    return jdbcClient.sql("SELECT id, title, content FROM blogs")//blogsというテーブルを作る
        .query(Blog.class)
        .list();
  }

  public List<Blog> searchByTitle(String keyword){
    return jdbcClient.sql("SELECT id, title, content FROM blogs WHERE LIKE title = :keyword")
        .param("keyword", "%"+keyword+"%")
        .query(Blog.class)
        .list();
  }

  public void save(Blog blog){
    jdbcClient.sql("INSERT INTO books (id, title, content) VALUES (:id, :title, :content)")
        .param("id", blog.getId())
        .param("title", blog.getTitle())
        .param("content", blog.getContent())
        .update();
  }
  
  public Optional<Blog> findById(Long id){
    return jdbcClient.sql("SELECT id, title, content FROM blogs WHERE id = :id")
        .param("id", id)
        .query(Blog.class)
        .optional();
  }

}
