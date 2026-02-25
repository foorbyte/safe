package com.didomultiservice.ecollect.ecollect.test;

import com.didomultiservice.ecollect.ecollect.entity.Role;
import com.didomultiservice.ecollect.ecollect.entity.Users;
import com.didomultiservice.ecollect.ecollect.test.dto.UserDTO;
import com.didomultiservice.ecollect.ecollect.test.impl.UsersBusinessImpl;
import com.didomultiservice.ecollect.ecollect.test.jwt.JwtGeneratorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@RequestMapping("/api/")
@RestController
public class UsersController {
    @Autowired
    private UsersBusiness usersBusiness;
    @Autowired
    UsersRepo userRepo;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtGeneratorValidator jwtGenVal;
    @Autowired
    BCryptPasswordEncoder bcCryptPasswordEncoder;

    @Autowired
    UsersBusinessImpl userService;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/registration")
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDto) {

        Users users =  userService.save(userDto);
        if (users.equals(null))
            return generateRespose("Not able to save user ", HttpStatus.BAD_REQUEST, userDto);
        else
            return generateRespose("User saved successfully : " + users.getIdUsers(), HttpStatus.OK, users);
    }

    @GetMapping("/genToken")
    public String generateJwtToken(@RequestBody UserDTO userDto) throws Exception {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getLogin(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtGenVal.generateToken(authentication);
    }


    @GetMapping("/welcomeAdmin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String welcome() {
        return "WelcomeAdmin";
    }

    @GetMapping("/welcomeUser")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String welcomeUser() {
        return "WelcomeUSER";
    }
    public ResponseEntity<Object> generateRespose(String message, HttpStatus st, Object responseobj) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("messasge", message);
        map.put("Status", st.value());
        map.put("data", responseobj);

        return new ResponseEntity<Object>(map, st);
    }

//    @GetMapping
//    public List<Users> getAllUserss() {
//        return usersBusiness.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Users getUsersById(@PathVariable Long id) {
//        return usersBusiness.findById(id);
//    }
//
//    @PostMapping
//    public Users createUsers(@RequestBody UserDTO utilisateur) {
//        return usersBusiness.save(utilisateur);
//    }
//
////    @PutMapping("/{id}")
////    public Users updateUsers(@PathVariable Long id, @RequestBody Users UsersDetails) {
////        Users utilisateur = usersBusiness.findById(id);
////        utilisateur.setNom(utilisateurDetails.getNom());
////        utilisateur.setEmail(utilisateurDetails.getEmail());
////        utilisateur.setPassword(utilisateurDetails.getPassword());
////        return usersBusiness.save(utilisateur);
////    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteUsers(@PathVariable Long id) {
//        usersBusiness.delete(id);
//        return ResponseEntity.ok().build();
//    }
}
