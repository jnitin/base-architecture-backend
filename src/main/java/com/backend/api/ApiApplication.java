package com.backend.api;

import com.backend.api.domain.Parameter;
import com.backend.api.domain.UserProfile;
import com.backend.api.domain.Route;
import com.backend.api.domain.User;
import com.backend.api.domain.enums.UserSituation;
import com.backend.api.domain.enums.RouteType;
import com.backend.api.repositories.ParameterRepository;
import com.backend.api.repositories.ProfileRepository;
import com.backend.api.repositories.RouteRepository;
import com.backend.api.repositories.UserRepository;

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
	private RouteRepository rotaRepository;

	@Autowired
	private ProfileRepository profileRepository;
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Parameter p1 = new Parameter(null, "Maria da Silva", "valor1", "Observação");
		Parameter p2 = new Parameter(null, "chave2", "valor2", "Observação 2");
		Parameter p3 = new Parameter(null, "chave.3", "valor3", "Observação 3");

		User user = new User(null, "Gabriel", "admin", pe.encode("admin"), UserSituation.ATIVO);

		UserProfile p = new UserProfile(null, "Administrador", 20);

		Route r1 = new Route(null, "Teste", RouteType.REQUISICAO, "/parameters/[0-9]+", null, null, "GET", null);
		Route r2 = new Route(null, "Teste2", RouteType.REQUISICAO, "/parameters/?", null, null, "GET", null);
		Route r3 = new Route(null, "Teste3", RouteType.REQUISICAO, "/parameters/?", null, null, "POST", null);
		Route r4 = new Route(null, "Teste4", RouteType.REQUISICAO, "/parameters/[0-9]+", null, null, "PUT", null);
		Route r5 = new Route(null, "Teste5", RouteType.REQUISICAO, "/parameters/[0-9]+", null, null, "DELETE", null);
		Route r6 = new Route(null, "Teste6", RouteType.REQUISICAO, "/routes/menus/?", null, null, "GET", null);
		Route r7 = new Route(null, "Teste6", RouteType.REQUISICAO, "/parameters/page/?", null, null, "GET", null);
		Route home = new Route(null, "Home", RouteType.MENU, "Home", "mdi-cog", null, "GET", "Sistema");
		Route account = new Route(null, "Minha Conta", RouteType.MENU, "Account", "mdi-account", null, "GET", "Sistema");

		addRoute(p, r1);
		addRoute(p, r2);
		addRoute(p, r3);
		addRoute(p, r4);
		addRoute(p, r5);
		addRoute(p, r6);
		addRoute(p, r7);
		addRoute(p, home);
		addRoute(p, account);

		p.getUsers().add(user);

		user.getUserProfiles().add(p);

		userRepository.save(user);

		profileRepository.save(p);

		parametroRepository.save(p1);
		parametroRepository.save(p2);
		parametroRepository.save(p3);

		userRepository.save(user);
	}

	public void addRoute(UserProfile up, Route r) {
		up.getRoutes().add(r);
		// r.getUserProfiles().add(up);
		rotaRepository.save(r);
	}

}
