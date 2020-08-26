package com.backend.api;

import com.backend.api.domain.Parameter;
import com.backend.api.domain.UserProfile;
import com.backend.api.domain.Rota;
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
	UserRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private RouteRepository rotaRepository;

	@Autowired
	private ProfileRepository perfilRepository;
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Parameter p1 = new Parameter(null, "chave", "valor", "Observação");

		User user = new User(null, "Gabriel", "gabs", pe.encode("senha"), UserSituation.ATIVO);

		UserProfile p = new UserProfile(null, "Administrador", 20);

		Rota r1 = new Rota(null, "Teste", RouteType.REQUISICAO, "/parametros/[0-9]+", null, null, "GET");
		Rota r2 = new Rota(null, "Teste2", RouteType.REQUISICAO, "/parametros/?", null, null, "GET");


		p.getRotas().add(r1);
		p.getRotas().add(r2);
		p.getUsuarios().add(user);

		r1.getPerfis().add(p);
		r2.getPerfis().add(p);

		user.getPerfis().add(p);

		usuarioRepository.save(user);

		rotaRepository.save(r1);
		rotaRepository.save(r2);

		perfilRepository.save(p);


		parametroRepository.save(p1);

		usuarioRepository.save(user);
	}

}
