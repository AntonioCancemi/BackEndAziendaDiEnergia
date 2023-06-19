package com.buildweek.gestionale_anziendale_energia.security.service;

import com.buildweek.gestionale_anziendale_energia.security.payload.LoginDto;
import com.buildweek.gestionale_anziendale_energia.security.payload.RegisterDto;

public interface AuthService {

	String login(LoginDto loginDto);

	String register(RegisterDto registerDto);

}
