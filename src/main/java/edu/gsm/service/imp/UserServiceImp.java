package edu.gsm.service.imp;

import edu.gsm.domain.model.User;
import edu.gsm.domain.repository.UserRepository;
import edu.gsm.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if (userToCreate.getId() != null && userRepository.existsById(userToCreate.getId()))
            throw new IllegalArgumentException("This user id already exists.");
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber()))
            throw new IllegalArgumentException("This account number already exists.");
        if (userRepository.existsByCardNumber(userToCreate.getCard().getNumber()))
            throw new IllegalArgumentException("This card number already exists.");
        return userRepository.save(userToCreate);
    }
}
