package UNite_TEST.UNIT_TEST.Service;


import UNite_TEST.UNIT_TEST.Repo.UserRepo;
import UNite_TEST.UNIT_TEST.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepo userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    public Optional<User> getUserById(Integer id) {
        return userRepo.findById(id);
    }
    public User aggiornaUser(Integer id , User userdetails){
        Optional<User> userOpt = userRepo.findById(id);
        if(userOpt.isPresent()){
            User user = userOpt.get();
            user.setName(userdetails.getName());
            user.setEmail(userdetails.getEmail());
            user.setEmail(userdetails.getEmail());
            return userRepo.save(user);
        }
        return null;
    }
    public void deleteUser(Integer id){
        userRepo.deleteById(id);
    }

}
