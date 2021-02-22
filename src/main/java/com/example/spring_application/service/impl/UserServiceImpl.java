package com.example.spring_application.service.impl;

import com.example.spring_application.dto.MyRequestDTO;
import com.example.spring_application.dto.MyResponseDTO;
import com.example.spring_application.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl(){
        System.out.println("Inside UserService Constructor");
    }
    public void init(){
        System.out.println("Inside userService post constructor");
    }
    @Override
    public boolean updateEmployee(MyRequestDTO request, String id){
        System.out.println("Inside User Service"+ request +" id "+id);
        return false;
    }
}
