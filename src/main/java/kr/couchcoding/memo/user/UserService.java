package kr.couchcoding.memo.user;

import kr.couchcoding.memo.controller.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {
//
//    Map<String, User> users = new HashMap<>();
//    Map<String, User> usersByName = new HashMap<>();
//    Map<String, User> usersByPassword = new HashMap<>();
    @Autowired
    private UserRepository userRepository;


    //get by id;
    public UserDto getUserById (String id) {
        User user =  userRepository.getById(id);
//        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getPassword());
//        return userDto;
        return new UserDto(user.getId(), user.getName(), user.getPassword());
    }

    //get by name;
    public UserDto getUserByName (String name) {
       User user =  userRepository.findByName(name);
       return new UserDto(user.getId(), user.getName(), user.getPassword());
    }

    @Transactional
    public User createUser(UserDto userDto) {
        // User user = new User(userDto.getId(), userDto.getName(), userDto.getPassword());

        //        user.setId(userDto.getId());
        //        user.setName(userDto.getName());
        //        user.setPassword(userDto.getPassword());

        User user = User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .build();
//
//        users.put(user.getId(), user);
//        usersByName.put(userDto.getName(), user);
//        usersByPassword.put(userDto.getPassword(), user);
        return userRepository.save(user);

    }
}
