package com.demo.controller;

import com.demo.model.Activity;
import com.demo.repository.ActivityRepository;
import com.demo.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class ActivityController {
  @Autowired ActivityRepository activityRepository;

  @GetMapping("/activities")
  public ResponseEntity<List<Activity>> getAllActivities(
      @RequestParam(required = false) String title) {
    try {
      List<Activity> activities = new ArrayList<>();

      if (title == null) {
        activities = activityRepository.findAll();
      } else {
        activities = activityRepository.findByNameContaining(title);
      }

      if (activities.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(activities, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/activities/{id}")
  public ResponseEntity<Activity> getActivity(@PathVariable("id") Long id) {
    try {
      Optional<Activity> activity = activityRepository.findById(id);
      if (activity.isPresent()) {
        return new ResponseEntity<>(activity.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/activities")
  public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {

    if (!ActivityService.validateActivity(activity)) {
      return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    try {
      Activity createdActivity = activityRepository.save(activity);
      return new ResponseEntity<>(createdActivity, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/activities/{id}")
  public ResponseEntity<Activity> updateActivity(
      @PathVariable("id") Long id, @RequestBody Activity activity) {

    if (!ActivityService.validateActivity(activity)) {
      return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    try {
      Optional<Activity> activityData = activityRepository.findById(id);

      if (activityData.isPresent()) {
        activity.setId(id);
        Activity updatedActivity = activityRepository.save(activity);

        return new ResponseEntity<>(updatedActivity, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/activities/{id}")
  public ResponseEntity<Activity> deleteActivity(@PathVariable("id") Long id) {
    try {
      Optional<Activity> userData = activityRepository.findById(id);

      if (userData.isPresent()) {
        activityRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
