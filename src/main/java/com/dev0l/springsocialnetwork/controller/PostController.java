package com.dev0l.springsocialnetwork.controller;

import com.dev0l.springsocialnetwork.entity.Post;
import com.dev0l.springsocialnetwork.entity.User;
import com.dev0l.springsocialnetwork.service.PostService;
import com.dev0l.springsocialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
public class PostController {

  @Autowired
  private PostService postService;

  @Autowired
  private UserService userService;

  /******************** Start ********************/

  @GetMapping("/posts")
  public String allPosts(@ModelAttribute("post") Post post, Model model,
                         @CookieValue(value = "currentUser", required = false) String currentUser) {
    if (currentUser != null) {
      model.addAttribute("posts", postService.findPostsByCreatedDate());
      model.addAttribute("user", userService.findUserById(Long.parseLong(currentUser)));
      return "posts";
    }
    model.addAttribute("msg", "You must Sign In to view Posts");
    return "signin";
  }

  /******************** Add Post ********************/

  @PostMapping("/addpost")
  public String savePost(@ModelAttribute Post post, Model model,
                         @CookieValue("currentUser") String currentUser) {
    User curUser = userService.findUserById(Long.parseLong(currentUser));
    model.addAttribute("currentUser", currentUser);
    post.setAuthor(curUser);
    LocalDateTime now = LocalDateTime.now();
    Timestamp timestamp = Timestamp.valueOf(now);
    post.setCreatedDate(timestamp);
    postService.savePost(post);
    return "redirect:/posts";
  }

  /******************** Delete Post ********************/

  @GetMapping("/delete-post/{id}")
  public String deletePost(@PathVariable long id) {
    postService.deletePost(id);
    return "redirect:/posts";
  }

  @GetMapping("/delete-by-author/{id}")
  public String deleteByAuthor(@PathVariable long id) {
    postService.deletePostsByAuthorId(id);
    return "redirect:/posts";
  }

}
