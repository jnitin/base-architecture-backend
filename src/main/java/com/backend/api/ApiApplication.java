package com.backend.api;

import com.backend.api.domain.*;
import com.backend.api.domain.enums.Profile;
import com.backend.api.domain.enums.UserSituation;
import com.backend.api.domain.enums.UserType;
import com.backend.api.enums.Permission;
import com.backend.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@RequestMapping(name = "/api")
public class ApiApplication implements CommandLineRunner {

  @Autowired
  ParameterRepository parametroRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder pe;

  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private RoleRepository roleRepository;


  public static void main(String[] args) {
    SpringApplication.run(ApiApplication.class, args);
  }

  @Override
  public void run(String... args) {
    if (userRepository.findAll().isEmpty()) {
      init();
    }
  }

  private void init() {
    Role r = Role
        .builder()
        .permission(Permission.ALL)
        .name("ADMIN")
        .build();

    Role r2 = Role
        .builder()
        .name("CUSTOMER")
        .build();

    roleRepository.saveAll(List.of(r, r2));

    User user = User.
        builder()
        .name("Gabriel")
        .email("admin")
        .password(pe.encode("admin"))
        .situation(UserSituation.ACTIVE)
        .userProfiles(new HashSet<>())
        .type(UserType.ADMIN)
        .roles(List.of(r))
        .build();
    user.addProfile(Profile.CLIENTE);

    userRepository.save(user);

    Company c = Company
        .builder()
        .name("Zamp")
        .cnpj("12345678912")
        .users(new HashSet<>())
        .parameters(new HashSet<>())
        .build();

    Company c2 = Company
        .builder()
        .name("Zamp2")
        .cnpj("1234567891f")
        .users(new HashSet<>())
        .parameters(new HashSet<>())
        .build();



    Parameter p1 = new Parameter("Maria da Silva", "69999955478", "Porto Velho", new HashSet<>());
    Parameter p2 = new Parameter("Maria das graças", "69815254789", "Porto Velho",  new HashSet<>());
    Parameter p3 = new Parameter("Carlos Alberto", "69554758963", "Ariquemes",  new HashSet<>());
    Parameter p4 = new Parameter("Paulo Cesar", "41814578956", "Curitiba",  new HashSet<>());
    Parameter p5 = new Parameter("José Bonifácio", "41556657842", "Pinhais",  new HashSet<>());
    Parameter p6 = new Parameter("Euclides da Cunha", "45458569856", "Florianópolis",  new HashSet<>());

    UserProfile p = UserProfile
        .builder()
        .description("Administrador")
        .level(20)
        .role(r)
        .users(new HashSet<>())
        .build();
    p.getUsers().add(user);


    UserProfile pro2 = UserProfile
        .builder()
        .description("Atendente")
        .role(r2)
        .level(2)
        .build();


    profileRepository.save(p);
    profileRepository.save(pro2);

    parametroRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6));

    c.getParameters().addAll(Set.of(p1, p2, p3, p4, p5, p6));
    c2.getParameters().addAll(Set.of(p1, p2));

    c.getUsers().add(user);
    c2.getUsers().add(user);

    companyRepository.save(c);
    companyRepository.save(c2);
  }


}
