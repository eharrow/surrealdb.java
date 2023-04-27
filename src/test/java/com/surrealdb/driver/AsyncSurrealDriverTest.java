package com.surrealdb.driver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.surrealdb.BaseTest;
import com.surrealdb.connection.SurrealConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class AsyncSurrealDriverTest extends BaseTest {

    private AsyncSurrealDriver driver;
    @Mock
    private SurrealConnection connection;

    @BeforeEach
    void setup() {
        driver = new AsyncSurrealDriver(connection);
    }

    @Test
    void happyPathSignup() {
        // given a mock connection and some expected values
        var completedFuture = buildCompletedFuture();
        var credentials = getCredentials();
        when(connection.rpc(any(), anyString(), any())).thenReturn(completedFuture);

        // when signup is called
        driver.signUp(credentials.namespace(),
            credentials.database(),
            credentials.scope(),
            credentials.username(),
            credentials.password());

        // then verify that the connection's rpc method is called with the expected args
        verify(connection).rpc(getStringType(), "signup", buildExpectedSignup(credentials));
    }
}
