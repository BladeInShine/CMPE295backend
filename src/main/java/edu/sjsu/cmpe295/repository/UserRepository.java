package edu.sjsu.cmpe295.repository;

import edu.sjsu.cmpe295.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by BladeInShine on 16/2/25.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneById(Long userId);

    User findOneByEmail(String email);

    User findOneByUsername(String username);

}
