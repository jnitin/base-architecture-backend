package com.backend.api;

import com.backend.api.domain.*;
import com.backend.api.domain.enums.Profile;
import com.backend.api.domain.enums.RouteType;
import com.backend.api.domain.enums.UserSituation;
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
    private RouteRepository routeRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Override
    public void run(String... args) {
        User user = User.
                builder()
                .name("Gabriel")
                .email("admin")
                .password(pe.encode("admin"))
                .situation(UserSituation.ACTIVE)
                .userProfiles(new HashSet<>())
                .companies(new HashSet<>())
                .build();
        user.addProfile(Profile.CLIENTE);

        userRepository.save(user);

        Company c = Company
                .builder()
                .name("Zamp")
                .cnpj("12345678912")
                .users(new HashSet<>())
                .build();

        Company c2 = Company
                .builder()
                .name("Zamp2")
                .cnpj("1234567891f")
                .users(new HashSet<>())
                .build();
        companyRepository.save(c);
        companyRepository.save(c2);

        Parameter p1 = new Parameter("Maria da Silva", "69999955478", "Porto Velho", c);
        Parameter p2 = new Parameter("Maria das graças", "69815254789", "Porto Velho", c);
        Parameter p3 = new Parameter("Carlos Alberto", "69554758963", "Ariquemes", c);
        Parameter p4 = new Parameter("Paulo Cesar", "41814578956", "Curitiba", c);
        Parameter p5 = new Parameter("José Bonifácio", "41556657842", "Pinhais", c);
        Parameter p6 = new Parameter("Euclides da Cunha", "45458569856", "Florianópolis", c);

        UserProfile p = UserProfile
				.builder()
				.description("Administrador")
				.level(20)
				.users(new HashSet<>())
				.build();
		p.getUsers().add(user);


		UserProfile pro2 = UserProfile
				.builder()
				.description("Atendente")
				.level(2)
				.build();

		Set<Route> routes = createRoutes();

        routeRepository.saveAll(routes);

        p.setRoutes(routes);

        profileRepository.save(p);
        profileRepository.save(pro2);

        parametroRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6));

        c.getUsers().add(user);

        companyRepository.save(c);
        companyRepository.save(c2);
    }

    private HashSet<Route> createRoutes() {
        final var routes = new HashSet<Route>();
        routes.add(createReq("Teste", "/parameters/[0-9]+", "GET"));
        routes.add(createReq("Teste2", "/parameters/?", "GET"));
        routes.add(createReq("Teste3", "/parameters/?", "POST"));
        routes.add(createReq("Teste4", "/parameters/[0-9]+", "PUT"));
        routes.add(createReq("Teste5", "/parameters/[0-9]+", "DELETE"));
        routes.add(createReq("Teste6", "/routes/menus/?", "GET"));
        routes.add(createReq("Teste6", "/parameters/page/?", "GET"));
        routes.add(createReq("GET ALL", "/.*", "GET"));
        routes.add(createReq("POST ALL", "/.*", "POST"));
        routes.add(createReq("PUT ALL", "/.*", "PUT"));
        routes.add(createReq("DELETE ALL", "/.*", "DELETE"));
        routes.add(createMenu("Home", "Home", "mdi-home", "Geral"));
        routes.add(createMenu("Usuários", "Users", "mdi-account", "Sistema"));
        routes.add(createMenu("Perfis", "Profiles", "mdi-account-box-multiple", "Sistema"));
        routes.add(createMenu("Parâmetros", "Parameters", "mdi-cog", "Sistema"));
        routes.add(createMenu("Rotas", "Routes", "mdi-routes", "Sistema"));
        routes.add(createMenu("Empresas", "Companies", "mdi-office-building", "Sistema"));
        return routes;
    }

    public static Route createReq(String description, String url, String method) {
        return Route.builder()
                .description(description)
                .url(url)
                .method(method)
                .type(RouteType.REQUISICAO)
                .build();
    }

    public static Route createMenu(String description, String url, String icon, String category) {
        return Route.builder()
                .description(description)
                .url(url)
                .method("GET")
                .type(RouteType.MENU)
                .icon(icon)
                .category(category)
                .build();
    }

}
