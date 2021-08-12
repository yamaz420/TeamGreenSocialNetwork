package com.dev0l.springsocialnetwork.service;

import com.dev0l.springsocialnetwork.entity.User;
import com.dev0l.springsocialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public void saveUser(User user) {
    byte[] salt = generateSalt();
    String saltString = convertByteToStringForDB(salt);
    String hashedPassword = createSecureHashPass(user.getPassword(), salt);

    if (!hashedPassword.equals("")) {
      user.setSalt(saltString);
      user.setPassword(hashedPassword);
      userRepository.save(user);
    }
  }

  public String createSecureHashPass(String plainTextPassword, byte[] salt) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(salt);
      byte[] hashedPass = md.digest(plainTextPassword.getBytes());
      return convertByteToStringForDB(hashedPass);

    } catch (NoSuchAlgorithmException ex) {
      ex.printStackTrace();
    }
    return "";
  }

  private String convertByteToStringForDB(byte[] hashedPass) {
    return DatatypeConverter.printHexBinary(hashedPass).toLowerCase();
  }

  private byte[] convertStringToByteForDB(String dbPassword) {
    return DatatypeConverter.parseHexBinary(dbPassword);
  }

  private byte[] generateSalt() {
    SecureRandom sr = new SecureRandom();
    byte[] hashedSalt = sr.generateSeed(12);
    return hashedSalt;
  }

  public boolean authUser(String username, String password) {
    User dbUser = userRepository.findByUsername(username);
    if (dbUser == null) {
      System.out.println("Username is not correct " + username);
      return false;
    }
    String passwordToCompare = createSecureHashPass(password, convertStringToByteForDB(dbUser.getSalt()));
    return dbUser.getPassword().equals(passwordToCompare);
  }

  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  public User findUserById(long id) {
    return userRepository.findById(id).orElseThrow();
  }

  public User findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public void updateUser(User user) {
    User userDB = userRepository.findById(user.getId()).orElseThrow();
    userDB.setUsername(user.getUsername());
    userDB.setAddress(user.getAddress());
    userRepository.save(userDB);
  }

  public void deleteUser(long id) {
    userRepository.deleteById(id);
  }

}
