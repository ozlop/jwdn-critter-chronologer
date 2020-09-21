package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository<T extends AppUser> extends JpaRepository<T, Long> {
}
