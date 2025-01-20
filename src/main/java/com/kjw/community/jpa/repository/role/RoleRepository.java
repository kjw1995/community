package com.kjw.community.jpa.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kjw.community.jpa.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
