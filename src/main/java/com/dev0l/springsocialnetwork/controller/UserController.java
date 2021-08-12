package com.dev0l.springsocialnetwork.controller;

import com.dev0l.springsocialnetwork.entity.Post;
import com.dev0l.springsocialnetwork.entity.User;
import com.dev0l.springsocialnetwork.service.PostService;
import com.dev0l.springsocialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private PostService postService;

  /******************** Start ********************/

  @GetMapping("/")
  public String welcome(@ModelAttribute("user") User user, Model model,
                        @CookieValue(value = "currentUser", required = false) String currentUser) {
    if (currentUser != null && currentUser != "") {
      model.addAttribute("user", userService.findUserById(Long.parseLong(currentUser)));
      return "index";
    }
    return "index";
  }

  /******************** Sign In ********************/

  @GetMapping("/signin")
  public String signIn() {
    return "signin";
  }

  @PostMapping("/authenticate-user")
  public String authUser(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         HttpServletResponse response) {

    User user = userService.findUserByUsername(username);

    if (user != null && userService.authUser(username, password)) {
      Long id = user.getId();
      Cookie cookie = new Cookie("currentUser", id.toString());
      cookie.setMaxAge(5000);
      response.addCookie(cookie);
      //System.out.println("value of cookie is " + cookie.getValue());
      return "redirect:/profile/" + id;
    }
    return "redirect:/authError";
  }

  @GetMapping("/authError")
  public String authError(Model model) {
    model.addAttribute("msg", "The username and password you entered is incorrect. No Account? Sign Up using the link below.");
    return "signin";
  }

  /******************** Sign Up ********************/

  @GetMapping("/signup")
  public String signUp(@ModelAttribute("user") User user) {
    return "signup";
  }

  @PostMapping("/save-user")
  public String saveUser(User user,
                         @RequestParam("password") String password,
                         @RequestParam("password_confirm") String password_confirm) {

    if (password.equals(password_confirm)) {
      user.setImg("https://via.placeholder.com/150");
      userService.saveUser(user);
      return "redirect:/success";
    }
    return "redirect:/failed";
  }

  @GetMapping("/success")
  public String success(@ModelAttribute("user") User user) {
    return "redirect:/signin";
  }

  @GetMapping("/failed")
  public String failed(@ModelAttribute("user") User user,
                       Model model) {
    model.addAttribute("msg", "Sign up failed. Please try again and make sure that the passwords match.");
    return "signup";
  }

  /******************** Profile ********************/

  @GetMapping("/profile/{id}")
  public String showProfile(@ModelAttribute("post") Post post, Model model,
                            @CookieValue("currentUser") String currentUser,
                            @PathVariable long id) {
    userService.findUserById(id);
    model.addAttribute("posts", postService.findPostByAuthorIdCreatedDate(id));
    model.addAttribute("user", userService.findUserById(Long.parseLong(currentUser)));
    return "profile";
  }

  /******************** Sign Out ********************/

  @GetMapping("/signout")
  public String signOut(HttpServletResponse response) {
    Cookie cookie = new Cookie("currentUser", "");
    cookie.setMaxAge(0);
    response.addCookie(cookie);
    //System.out.println("value of cookie is " + cookie.getValue());
    return "redirect:/";
  }

  /******************** Admin/Edit ********************/

  /*@GetMapping("/admin")
  public ModelAndView adminDashboard() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("admin");
    List<User> users = userService.getAllUsers();
    mv.addObject("users", users);
    return mv;
  }*/
  @GetMapping("/edit/{id}")
  public String editUser(Model model,
                         @PathVariable long id,
                         @CookieValue("currentUser") String currentUser) {
    userService.findUserById(id);
    User curUser = userService.findUserById(Long.parseLong(currentUser));
    model.addAttribute("currentUser", currentUser);
    model.addAttribute(curUser);
    return "edit";
  }

  @PostMapping("/update-user")
  public String updateUser(@ModelAttribute User user) {
    userService.updateUser(user);
    Long id = user.getId();
    return "redirect:/profile/" + id;
  }

  @GetMapping("/delete/{id}")
  public String deleteUser(@PathVariable long id) {
    postService.deletePostsByAuthorId(id);
    userService.deleteUser(id);
    return "redirect:/signout";
  }

}
