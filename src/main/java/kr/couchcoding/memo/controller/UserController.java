package kr.couchcoding.memo.controller;


import kr.couchcoding.memo.controller.dto.UserDto;
import kr.couchcoding.memo.memo.Memo;
import kr.couchcoding.memo.user.User;
import kr.couchcoding.memo.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 사용자의 요청을 받는 부분 (사용자의 요청을 받아서 Service를 호출한다)
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public User createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }


    @GetMapping("/{id}") // /users/jiin
    public UserDto getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping() // get /users?id={id}
    public UserDto getUserByName(@RequestParam String name){
        // call service
        log.info("call user by name");
        return userService.getUserByName(name);
    }
}
