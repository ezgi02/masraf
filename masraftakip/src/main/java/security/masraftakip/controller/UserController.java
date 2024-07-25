package security.masraftakip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import security.masraftakip.model.User;
import security.masraftakip.service.UserService;

import java.util.List;
@RestController
@RequestMapping("/api/v1/users")//kurum için kullanılan resource
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public void save(@RequestBody User user) {
        userService.save(user);
    }
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }
    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return userService.getById(id);
    }
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id,user);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
