package com.ssafy.trip.interceptor;

import com.ssafy.trip.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {

	private final JWTUtil jwtUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header");
		}

		String token = authorizationHeader.substring(7);

		if (!jwtUtil.checkToken(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is expired or invalid");
		}

		String userId = jwtUtil.getUserId(token);
		request.setAttribute("userId", userId);
		return true;
	}
}

