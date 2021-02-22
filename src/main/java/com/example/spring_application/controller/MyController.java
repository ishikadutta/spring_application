package com.example.spring_application.controller;
import com.example.spring_application.dto.MyRequestDTO;
import com.example.spring_application.dto.MyResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {
    @GetMapping(path="/hello")
    public String helloWorld(){
       // System.out.println("yeyyy");
        return "success!!";
    }
    @PutMapping(path="/hello-put")
    public String help(){
        return "Need help";
    }
    @PostMapping(path="/hello-post")
    public String helloWorldPost(){
        return "woww";
    }
    @GetMapping(path="hello-query")
    public String helloQuery(@RequestParam String query){
        return "query "+query;
    }
    @PostMapping(path="/register")
    public String registerUser(@RequestBody MyRequestDTO request){
        return request.toString();
    }
    @GetMapping(path="/employee/{employeeId}")
    public MyResponseDTO getEmployeeDetails(@PathVariable String employeeId){
        MyResponseDTO response = new MyResponseDTO();
        response.setEmployeeId(employeeId);
        return response;
    }

}
