package com.example.spring_application.service;

import com.example.spring_application.dto.MyRequestDTO;
import com.example.spring_application.dto.MyResponseDTO;

public interface UserService {
    boolean updateEmployee(MyRequestDTO request,String id);
}
