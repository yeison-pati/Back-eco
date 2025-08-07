package com.itm.ecosurprise.domain.port.in;

public interface JwtService {
    String generateToken(String username, String role, int userId);
    boolean validateToken(String token);
    String getRolFromToken(String token);
    int getIdFromToken(String token);
    String renewToken(String oldToken);
}
