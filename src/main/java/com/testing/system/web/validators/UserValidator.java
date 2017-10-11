package com.testing.system.web.validators;

import com.testing.system.entities.User;
import com.testing.system.exceptions.validation.ValidationException;

/**
 * Created by yuri on 11.10.17.
 */
public class UserValidator extends Validator<User> {

    @Override
    public void validate(User user) throws ValidationException {
        check(isNotNull(user.getRole()) &&
                isNotEmpty(user.getFirstName(), user.getLastName(),
                        user.getUsername(), user.getPassword()) &&
                isMatches(user.getPhone(), ValidationRegexes.PHONE) &&
                isMatches(user.getEmail(), ValidationRegexes.EMAIL));
    }
}
