package com.surrealdb.driver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

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
        given(connection.rpc(any(), anyString(), any())).willReturn(completedFuture);

        // when the driver signup is called
        driver.signUp(credentials.namespace(),
            credentials.database(),
            credentials.scope(),
            credentials.username(),
            credentials.password());

        // then verify that the connection's rpc method is called with the expected args
        then(connection).should().rpc(getStringType(), "signup", buildExpectedSignup(credentials));
    }
}
