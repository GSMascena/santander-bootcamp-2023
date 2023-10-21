package edu.gsm.service;

import edu.gsm.domain.model.User;

public interface UserService {
    User findById(Long id);

    User create(User userToCreate);


}
