package com.surrealdb;

import com.google.gson.reflect.TypeToken;
import com.surrealdb.driver.model.SignUp;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

public abstract class BaseTest {

    protected static CompletableFuture<Object> buildCompletedFuture() {
        return new CompletableFuture<>().completedFuture(new Object());
    }

    protected static Type getStringType() {
        return TypeToken.getParameterized(String.class).getType();
    }

    protected Credentials getCredentials() {
        return new Credentials("localhost", 8080, "root", "root", "test", "test", "", "allusers");
    }

    protected SignUp buildSignup(Credentials credentials) {
        return new SignUp(
            credentials.namespace(),
            credentials.database(),
            credentials.scope(),
            credentials.username(),
            credentials.password());
    }
}
