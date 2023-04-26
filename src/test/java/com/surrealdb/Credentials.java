package com.surrealdb;

public record Credentials(
        String host,
        int port,
        String username,
        String password,
        String namespace,
        String database,
        String token,
        String scope) {}
