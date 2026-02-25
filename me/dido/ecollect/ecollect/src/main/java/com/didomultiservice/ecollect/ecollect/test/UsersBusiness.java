package com.didomultiservice.ecollect.ecollect.test;

import com.didomultiservice.ecollect.ecollect.entity.Users;
import com.didomultiservice.ecollect.ecollect.test.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersBusiness extends UserDetailsService {
    Users save(UserDTO userRegisteredDTO);
}
