package com.respodo.commerce.repository.dao;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import com.respodo.commerce.repository.domain.User;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByActivationKey(String activationKey);

    User findOneByResetKey(String resetKey);

    User findOneByLogin(String login);

    void delete(User t);

	List<User> findAllByActivatedIsFalseAndCreatedDateBefore(DateTime minusDays);

}
