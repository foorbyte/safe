package com.didomultiservice.ecollect.ecollect.test.impl;

import com.didomultiservice.ecollect.ecollect.entity.Role;
import com.didomultiservice.ecollect.ecollect.entity.Users;
import com.didomultiservice.ecollect.ecollect.test.RoleRepository;
import com.didomultiservice.ecollect.ecollect.test.UsersBusiness;
import com.didomultiservice.ecollect.ecollect.test.UsersRepo;
import com.didomultiservice.ecollect.ecollect.test.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersBusinessImpl  implements UsersBusiness {
    @Autowired
    UsersRepo userRepo;

    @Autowired
    RoleRepository roleRepo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Users save(UserDTO userRegisteredDTO) {
        // Récupérer le rôle existant
        Role role = roleRepository.findByName("ROLE_USER");
        if (role != null) {
            userRegisteredDTO.setRole(role.getName()); // Assurez-vous que les rôles existent déjà
        }else {
            return null;
        }
        Users user = new Users();
        user.setLogin(userRegisteredDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userRegisteredDTO.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepo.findByLogin(username);
        return new org.springframework.security.core.userdetails.User(user.get().getLogin(), user.get().getPassword(), mapRolesToAuthorities(user.get().getRoles()));
    }
    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
