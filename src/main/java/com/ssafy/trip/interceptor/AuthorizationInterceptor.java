package com.ssafy.trip.interceptor;

import com.ssafy.trip.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {

	private final JWTUtil jwtUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String authorizationHeader = request.getHeader("Authorization");
		if (request.getMethod().equals("OPTIONS")) {
			return true;
		}
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			log.info("Missing or invalid Authorization header {}", authorizationHeader);
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header");
		}

		String token = authorizationHeader.substring(7);

		if (!jwtUtil.checkToken(token)) {
			log.info("Token is expired or invalid {}", token);
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is expired or invalid");
		}

		String userId = jwtUtil.getUserId(token);
		request.setAttribute("userId", userId);
		return true;
	}
}

