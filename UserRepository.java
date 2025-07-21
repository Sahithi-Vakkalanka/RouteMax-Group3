// package com.routemax.teamroutemax.repository;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.routemax.teamroutemax.entity.User;

// public interface UserRepository extends JpaRepository<User, Long> {
//     boolean existsByEmail(String email);
// }
package com.routemax.teamroutemax.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.routemax.teamroutemax.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);  // ✅ Add this
}
