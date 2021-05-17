package com.example.warchar.service;


import com.example.warchar.exception.UserAlreadyExistException;
import com.example.warchar.model.Users;
import com.example.warchar.payload.RegisterRequest;
import com.example.warchar.repository.UsersRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SecurityService {

    private final UsersRepository usersRepository;

    public void registerUser(@NotNull RegisterRequest registerRequest) throws UserAlreadyExistException {
        if(usersRepository.existsByEmail(registerRequest.getEmail())){
            throw new UserAlreadyExistException("There is an account with email: " + registerRequest.getEmail());
        }
        Users user = new Users();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        usersRepository.save(user);
    }


}
