package com.surrealdb.driver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.surrealdb.BaseTest;
import com.surrealdb.connection.SurrealConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AsyncSurrealDriverTest extends BaseTest {

    private AsyncSurrealDriver driver;
    @Mock
    private SurrealConnection connection;

    @BeforeEach
    void setup() {
        driver = new AsyncSurrealDriver(connection);
    }

    @Test
    void signUp() {
        // given
        var completedFuture = buildCompletedFuture();
        var credentials = getCredentials();
        when(connection.rpc(any(), anyString(), any())).thenReturn(completedFuture);

        // when
        driver.signUp(credentials.namespace(),
            credentials.database(),
            credentials.scope(),
            credentials.username(),
            credentials.password());


        // then
        verify(connection).rpc(getStringType(), "signup", buildExpectedSignup(credentials));
    }
}
