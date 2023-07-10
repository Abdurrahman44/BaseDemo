package Workspace.io.BaseDemo.Repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Workspace.io.BaseDemo.Entities.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long >{

	User findByUsername(String username);

}
