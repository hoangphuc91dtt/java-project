package com.example.crud.service;

import com.example.crud.model.User;
import com.example.crud.model.Company;
import com.example.crud.repository.UserRepository;
import com.example.crud.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        if (user.getCompany() != null) {
            Company company = companyRepository.findById(user.getCompany().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + user.getCompany().getId()));
            user.setCompany(company);
        }
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
