package org.sopt.carrotmarket.Repository;

import org.sopt.carrotmarket.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
