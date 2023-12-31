package br.com.luaccminerva.santanderdevweek2023.service.impl;

import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

import br.com.luaccminerva.santanderdevweek2023.domain.model.User;
import br.com.luaccminerva.santanderdevweek2023.domain.repository.UserRepository;
import br.com.luaccminerva.santanderdevweek2023.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new); //lançando exceção
    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("O número da Account já existe.");
        }

        return userRepository.save(userToCreate);
    }
}
