package com.demo.service;

import com.demo.model.Activity;

public class ActivityService {

  public static boolean validateActivity(Activity activity) {
    if (activity.getName() == null || activity.getName().length() == 0) {
      return false;
    }
    return true;
  }
}
