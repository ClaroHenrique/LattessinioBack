package com.demo.controller;

import com.demo.model.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/")
public class UserController {

  @Autowired UserRepository userRepository;

  @GetMapping("/users")
  public ResponseEntity<List<User>> getUsers(
      @Param("user-name") String userName,
      @Param("activity-name") String activityName) {
    try {
      Stream<User> users = userRepository.findAll().stream();
      System.out.println("GetAllUsers(" + userName + "," +  activityName + ")");

      if(userName != null && !userName.isEmpty()){
        users = users.filter((User u) -> u.hasNameWith(userName));
      }
      if(activityName != null && !activityName.isEmpty()){
        users = users.filter((User u) -> u.hasActivityWithName(activityName));
      }

      List<User> response = users.collect(Collectors.toList());
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
    try {
      Optional<User> user = userRepository.findById(id);

      if (user.isPresent()) {
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    try {
      user.setId(null);
      User createdUser = userRepository.save(user);

      return new ResponseEntity<>(createdUser, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
    try {
      Optional<User> userData = userRepository.findById(id);

      if (userData.isPresent()) {
        user.setId(id);
        User updatedUser = userRepository.save(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
    try {
      Optional<User> userData = userRepository.findById(id);

      if (userData.isPresent()) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
