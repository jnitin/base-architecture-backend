package com.backend.api;

import com.backend.api.domain.*;
import com.backend.api.domain.enums.RouteType;
import com.backend.api.domain.enums.UserSituation;
import com.backend.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public void run(String... args) throws Exception {
		User user = new User(null, "Gabriel", "admin", pe.encode("admin"), UserSituation.ACTIVE);

		Company c = new Company(null, "Zamp", "12345678912");
		Company c2 = new Company(null, "Zamp2", "12345678912f");
		companyRepository.save(c);
		companyRepository.save(c2);

		Parameter p1 = new Parameter(null, "Maria da Silva", "69999955478", "Porto Velho", c);
		Parameter p2 = new Parameter(null, "Maria das graças", "69815254789", "Porto Velho", c);
		Parameter p3 = new Parameter(null, "Carlos Alberto", "69554758963", "Ariquemes", c);
		Parameter p4 = new Parameter(null, "Paulo Cesar", "41814578956", "Curitiba", c);
		Parameter p5 = new Parameter(null, "José Bonifácio", "41556657842", "Pinhais", c);
		Parameter p6 = new Parameter(null, "Euclides da Cunha", "45458569856", "Florianópolis", c);

		UserProfile p = new UserProfile(null, "Administrador", 20);
		UserProfile pro2 = new UserProfile(null, "Atendente", 2);

		Route r1 = new Route(null, "Teste", RouteType.REQUISICAO, "/parameters/[0-9]+", null, null, "GET", null);
		Route r2 = new Route(null, "Teste2", RouteType.REQUISICAO, "/parameters/?", null, null, "GET", null);
		Route r3 = new Route(null, "Teste3", RouteType.REQUISICAO, "/parameters/?", null, null, "POST", null);
		Route r4 = new Route(null, "Teste4", RouteType.REQUISICAO, "/parameters/[0-9]+", null, null, "PUT", null);
		Route r5 = new Route(null, "Teste5", RouteType.REQUISICAO, "/parameters/[0-9]+", null, null, "DELETE", null);
		Route r6 = new Route(null, "Teste6", RouteType.REQUISICAO, "/routes/menus/?", null, null, "GET", null);
		Route r7 = new Route(null, "Teste6", RouteType.REQUISICAO, "/parameters/page/?", null, null, "GET", null);
		Route rAllGet = new Route(null, "GET ALL", RouteType.REQUISICAO, "/.*", null, null, "GET", null);
		Route rAllPost = new Route(null, "POST ALL", RouteType.REQUISICAO, "/.*", null, null, "POST", null);
		Route rAllPut = new Route(null, "PUT ALL", RouteType.REQUISICAO, "/.*", null, null, "PUT", null);
		Route rAllDelete = new Route(null, "DELETE ALL", RouteType.REQUISICAO, "/.*", null, null, "DELETE", null);
		Route home = new Route(null, "Home", RouteType.MENU, "Home", "mdi-home", null, "GET", "Geral");
		Route account = new Route(null, "Usuários", RouteType.MENU, "Users", "mdi-account", null, "GET", "Sistema");
		Route profiles = new Route(null, "Perfis", RouteType.MENU, "Profiles", "mdi-account-box-multiple", null, "GET", "Sistema");
		Route parameters = new Route(null, "Parâmetros", RouteType.MENU, "Parameters", "mdi-cog", null, "GET", "Sistema");
		Route routes = new Route(null, "Rotas", RouteType.MENU, "Routes", "mdi-routes", null, "GET", "Sistema");
		Route companies = new Route(null, "Empresas", RouteType.MENU, "Companies", "mdi-office-building", null, "GET",
				"Sistema");

		addRoute(p, r1);
		addRoute(p, r2);
		addRoute(p, r3);
		addRoute(p, r4);
		addRoute(p, r5);
		addRoute(p, r6);
		addRoute(p, r7);
		addRoute(p, rAllGet);
		addRoute(p, rAllPost);
		addRoute(p, rAllPut);
		addRoute(p, rAllDelete);
		addRoute(p, profiles);
		addRoute(p, home);
		addRoute(p, account);
		addRoute(p, parameters);
		addRoute(p, companies);
		addRoute(p, routes);

		p.getUsers().add(user);

		user.getUserProfiles().add(p);

		userRepository.save(user);

		profileRepository.save(p);
		profileRepository.save(pro2);

		parametroRepository.save(p1);
		parametroRepository.save(p2);
		parametroRepository.save(p3);
		parametroRepository.save(p4);
		parametroRepository.save(p5);
		parametroRepository.save(p6);

		userRepository.save(user);

		user.getCompanies().add(c);
		user.getCompanies().add(c2);

		c.getUsers().add(user);
		c2.getUsers().add(user);

		userRepository.save(user);
		companyRepository.save(c);
		companyRepository.save(c2);
	}

	public void addRoute(UserProfile up, Route r) {
		up.getRoutes().add(r);
		routeRepository.save(r);
	}

}
