package com.surrealdb.driver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.surrealdb.BaseTest;
import com.surrealdb.connection.SurrealConnection;
import java.lang.reflect.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AsyncSurrealDriverTest extends BaseTest {

    private AsyncSurrealDriver driver;
    private SurrealConnection connection = mock();

    @BeforeEach
    void setup() {
        driver = new AsyncSurrealDriver(connection);
    }

    @Test
    void signUp() {
        // given
        Type type = getStringType();
        var value = buildCompletedFuture();

        // when
        when(connection.rpc(any(), anyString(), any())).thenReturn(value);

        driver.signUp(
                getCredentials().namespace(),
                getCredentials().database(),
                getCredentials().scope(),
                getCredentials().username(),
                getCredentials().password());

        // then
        verify(connection).rpc(type, "signup", buildSignup(getCredentials()));
    }
}
