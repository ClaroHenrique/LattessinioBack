package com.demo.service;

import com.demo.model.User;

public class UserService {

  public static boolean validateUser(User user) {
    if (user.getName() == null
        || user.getName().length() == 0
        || user.getEmail() == null
        || user.getEmail().length() == 0) {
      return false;
    }
    return user.getActivities().stream().allMatch(ActivityService::validateActivity);
  }
}
