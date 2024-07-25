package security.masraftakip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.masraftakip.model.User;
import security.masraftakip.repository.UserRepository;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
    public User update(Long id,User user) {
        User updateUser=userRepository.findById(id).orElse(null);
        if(updateUser!=null) {
            updateUser.setUsername(user.getUsername());
            updateUser.setEmail(user.getEmail());
            updateUser.setPassword(user.getPassword());
            return userRepository.save(updateUser);
        }
        return null;
    }
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
