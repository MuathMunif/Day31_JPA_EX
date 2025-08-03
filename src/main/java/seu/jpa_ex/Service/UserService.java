package seu.jpa_ex.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seu.jpa_ex.Model.UserModel;
import seu.jpa_ex.Repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;



    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(UserModel user) {
        userRepository.save(user);
    }

    public boolean updateUser(Integer id ,UserModel user) {
        UserModel userModel = userRepository.getById(id);
        if(userModel == null) {
            return false;
        }
        userModel.setUsername(user.getUsername());
        userModel.setPassword(user.getPassword());
        userModel.setEmail(user.getEmail());
        userModel.setRole(user.getRole());
        userModel.setActive(user.isActive());
        userModel.setBalance(user.getBalance());
        userRepository.save(userModel);
        return true;
    }

    public boolean deleteUser(int id) {
        UserModel user = userRepository.getById(id);
        if (user == null) {
            return false;
        }
        userRepository.delete(user);
        return true;
    }


    //Admin deActive user
    public String adminDeActiveUser(Integer adminId , Integer userId) {
        UserModel admin = userRepository.getById(adminId);
        UserModel user = userRepository.getById(userId);
        if(admin == null) {
            return "Admin does not exist";
        }
        if(!admin.getRole().equalsIgnoreCase("admin")) {
            return "You are not an admin";
        }
        if(user == null) {
            return "User does not exist";
        }
        if (!user.isActive()) {
            return user.getUsername()+" already not active";
        }
        user.setActive(false);
        userRepository.save(user);
        return "User has been Deactivated";
    }


    //User subscription
    public String userSubscription (Integer userId) {
        UserModel user = userRepository.getById(userId);
        if(user == null) {
            return "User does not exist";
        }
        if (user.getBalance() < 50){
            return "You have not enough money";
        }
        user.setBalance(user.getBalance() - 50);
        user.setSubscription(true);
        userRepository.save(user);
        return "User has been subscribed";
    }
}
