package com.testing.system.web.controllers.common;

import com.testing.system.entities.User;
import com.testing.system.service.interfaces.UserService;
import com.testing.system.utils.CurrentUserFetcher;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dispatcher.RequestService;

/**
 * Created by yuri on 07.10.17.
 */
public class ProfileInfoController extends Controller {
    UserService userService;

    public ProfileInfoController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String get(RequestService requestService) {
        User user = userService.findById(
                CurrentUserFetcher.getCurrentUserId(getRequest()));

        requestService.setAttribute("user", user);
        return "profile-info";
    }
}
