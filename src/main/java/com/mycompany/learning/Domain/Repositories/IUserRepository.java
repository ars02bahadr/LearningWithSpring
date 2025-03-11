package com.mycompany.learning.Domain.Repositories;

import com.mycompany.learning.Domain.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends  JpaRepository<User, Integer>{

}
