package com.example.mpi.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenDto {
	
	private long id;
	
	private String userId;
	
	private String token;
	
	private Instant expiryDate;
	
}
