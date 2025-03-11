package com.mycompany.learning.Presentation.Controllers;


import com.mycompany.learning.Application.Dtos.UserDto;
import com.mycompany.learning.Application.Services.IUserService;
import com.mycompany.learning.Domain.Entities.User;
import com.mycompany.learning.Domain.Results.PagedResult;
import com.mycompany.learning.Domain.Results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Users")
@CrossOrigin
@Async
public class UserController {

    @Autowired
    private IUserService userService;

    UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping("/GetAll")
    public ResponseEntity<Result> GetAll(){
        return ResponseEntity.ok(userService.GetAll());
    }

    @GetMapping("/GetById/{id}")
    public ResponseEntity<Result> GetById(int id){
        return ResponseEntity.ok(userService.GetById(id));
    }

    @GetMapping("/GetPagedList/{pageNumber}/{pageSize}")
    public ResponseEntity<PagedResult> GetPagedList(int pageNumber, int pageSize){
        return ResponseEntity.ok(userService.GetPagedList(pageNumber, pageSize));
    }

    @PostMapping("/Add")
    public ResponseEntity<Result> Add(@RequestBody UserDto user){
        return ResponseEntity.ok(userService.Add(user));
    }

    @PostMapping("/Update")
    public ResponseEntity<Result> Update(@RequestBody UserDto user){
        return ResponseEntity.ok(userService.Update(user));
    }

    @PostMapping("/Delete")
    public ResponseEntity<Result> Delete(int id){
        return ResponseEntity.ok(userService.Delete(id));
    }


}
