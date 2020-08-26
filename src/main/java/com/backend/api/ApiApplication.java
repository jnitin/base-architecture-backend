package com.backend.api;

import com.backend.api.domain.Parametro;
import com.backend.api.domain.Usuario;
import com.backend.api.domain.enums.SituacaoUsuario;
import com.backend.api.repositories.ParametroRepository;
import com.backend.api.repositories.UsuarioRepository;

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
	ParametroRepository parametroRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder pe;
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Parametro p1 = new Parametro(null, "chave", "valor", "Observação");

		Usuario user = new Usuario(null, "Gabriel", "gabs", pe.encode("senha"), SituacaoUsuario.ATIVO);

		parametroRepository.save(p1);

		usuarioRepository.save(user);
	}

}
