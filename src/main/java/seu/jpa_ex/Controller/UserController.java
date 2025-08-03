package seu.jpa_ex.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import seu.jpa_ex.Api.ApiResponse;
import seu.jpa_ex.Model.UserModel;
import seu.jpa_ex.Service.UserService;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserModel user , Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id ,@Valid @RequestBody UserModel user , Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        boolean isUpdated = userService.updateUser(id,user);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User update failed"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User Not Found"));
    }

    //Extra for admin
    @PutMapping("/admin-de-active-user/{adminid}/{userid}")
    public ResponseEntity<?> adminDeActiveUser(@PathVariable Integer adminid,@PathVariable Integer userid){
        String response = userService.adminDeActiveUser(adminid,userid);
        if(response.equals("User has been Deactivated")){
            return ResponseEntity.status(200).body(new ApiResponse("User has been Deactivated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(response));
    }

    //Extra user Subscription
    @PutMapping("/user-subscription/{id}")
    public ResponseEntity<?> userSubscription(@PathVariable Integer id) {
        String response = userService.userSubscription(id);
        if(response.equals("User has been subscribed")){
            return ResponseEntity.status(200).body(new ApiResponse("User has been Subscribed"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(response));
    }
}
