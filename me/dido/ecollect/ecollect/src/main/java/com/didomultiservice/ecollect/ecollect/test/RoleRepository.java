package com.didomultiservice.ecollect.ecollect.test;

import com.didomultiservice.ecollect.ecollect.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

    //@Query(value = "SELECT * FROM \"eRole\" WHERE ? = ANY(\"Name\")", nativeQuery = true)
    Role findByName(String name);
}
