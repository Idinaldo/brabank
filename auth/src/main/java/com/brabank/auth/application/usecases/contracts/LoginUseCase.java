package com.brabank.auth.application.usecases.contracts;

import com.brabank.auth.adapters.in.dtos.LoginRequestDTO;
import com.brabank.auth.adapters.out.dtos.LoginResponseDTO;

public interface LoginUseCase {
    LoginResponseDTO execute(LoginRequestDTO loginRequestDTO);
}
