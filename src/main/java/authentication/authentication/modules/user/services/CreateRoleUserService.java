package authentication.authentication.modules.user.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import authentication.authentication.modules.user.UserRepository;
import authentication.authentication.modules.user.dto.CreateUserRoleDTO;
import authentication.authentication.modules.user.entities.Role;
import authentication.authentication.modules.user.entities.User;

@Service
public class CreateRoleUserService {

  @Autowired
  UserRepository userRepository;

  public User execute(CreateUserRoleDTO createUserRoleDTO) {

    Optional<User> userExists = userRepository.findById(createUserRoleDTO.getIdUser());


    if (userExists.isEmpty()) {
      System.out.println("teste");
      throw new Error("User does not exists!");
    }
    System.out.println("aaaaa");
    final List<Role> roles = createUserRoleDTO.getIdsRoles().stream().map(Role::new).collect(Collectors.toList());

    User user = userExists.get();
    System.out.println(user);

    user.setRoles(roles);

    userRepository.save(user);

    return user;

  }
}
