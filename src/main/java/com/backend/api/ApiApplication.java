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
		Parameter p1 = new Parameter(null, "key", "value", "Observação");

		User user = new User(null, "Gabriel", "gabs", pe.encode("password"), UserSituation.ATIVO);

		UserProfile p = new UserProfile(null, "Administrador", 20);

		Route r1 = new Route(null, "Teste", RouteType.REQUISICAO, "/parametros/[0-9]+", null, null, "GET");
		Route r2 = new Route(null, "Teste2", RouteType.REQUISICAO, "/parametros/?", null, null, "GET");


		p.getRoutes().add(r1);
		p.getRoutes().add(r2);
		p.getUsers().add(user);

		r1.getUserProfiles().add(p);
		r2.getUserProfiles().add(p);

		user.getUserProfiles().add(p);

		userRepository.save(user);

		rotaRepository.save(r1);
		rotaRepository.save(r2);

		profileRepository.save(p);


		parametroRepository.save(p1);

		userRepository.save(user);
	}

}
