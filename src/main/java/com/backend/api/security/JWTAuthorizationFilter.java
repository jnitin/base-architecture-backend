package com.backend.api.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backend.api.domain.UserProfile;
import com.backend.api.domain.Rota;
import com.backend.api.domain.User;
import com.backend.api.domain.enums.RouteType;
import com.backend.api.repositories.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;

	private UserRepository usuarioRepository;

	private UserDetailsService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
			UserDetailsService userDetailsService, UserRepository usuarioRepository) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
			if (auth != null) {
				UserSS userSS = (UserSS) auth.getPrincipal();
				Integer id = userSS.getId();

				User user = usuarioRepository.findById(id).orElse(null);
				String route = request.getRequestURI();
				String method = request.getMethod();

				if (isAllowedRoute(route, method, user)) {
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
		}
		chain.doFilter(request, response);
	}

	private boolean isAllowedRoute(String route, String method, User user) {
		Rota r = new Rota();
		r.setMethod(method);
		r.setUrl(route);
		for (UserProfile p : user.getPerfis()) {
			List<Rota> rotas = p.getRotas().stream().filter(rota -> rota.getTipo().equals(RouteType.REQUISICAO)).collect(Collectors.toList());
			if (rotas.contains(r)) {
				return true;
			}
		}
		return false;
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (jwtUtil.isValidToken(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}
}
