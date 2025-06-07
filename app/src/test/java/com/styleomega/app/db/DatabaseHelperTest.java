package com.styleomega.app.db;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

// Need to mock static DriverManager.getConnection
// This requires PowerMockito or a different approach if we strictly use Mockito.
// For simplicity in this subtask, we'll acknowledge this limitation.
// A true test of connect() might be harder without PowerMock or refactoring DatabaseHelper
// to inject the connection. Let's focus on insertCreditCard assuming connect() works or is mocked.

public class DatabaseHelperTest {

    @Mock
    Connection mockConnection;
    @Mock
    PreparedStatement mockPreparedStatement;

    DatabaseHelper dbHelper;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        dbHelper = new DatabaseHelper(); // Real instance

        // Mock the behavior of the connection and prepared statement
        // This setup is for when mockConnection is successfully injected or returned by connect()
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    }

    // Test for connect() is tricky due to static DriverManager.getConnection.
    // A simple test might just call it and expect null if DB is not available,
    // or rely on try-catch. Proper mocking needs more setup.
    @Test
    public void connect_shouldReturnNull_whenDbNotAvailable() {
        // This test assumes no DB is running at DB_URL, so getConnection throws SQLException
        // or returns null based on driver behavior (though it usually throws).
        // DatabaseHelper's connect() catches SQLException and returns null.
        // Note: This is more of an integration test for the connect method's error handling.
        // It also depends on the actual DB_URL, USER, PASS being invalid/unreachable.
        DatabaseHelper realDbHelper = new DatabaseHelper();
        assertNull("Connection should be null if DB is not available or creds are wrong", realDbHelper.connect());
    }


    @Test
    public void testInsertCreditCard_Success() throws SQLException {
        DatabaseHelper spiedDbHelper = spy(dbHelper);
        doReturn(mockConnection).when(spiedDbHelper).connect(); // Spy and mock the connect method

        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Simulate one row affected

        boolean result = spiedDbHelper.insertCreditCard("1234", "12/25", "123");
        assertTrue(result);
        verify(mockConnection).prepareStatement(anyString()); // Verify prepareStatement was called on the connection
        verify(mockPreparedStatement).setString(1, "1234");
        verify(mockPreparedStatement).setString(2, "12/25");
        verify(mockPreparedStatement).setString(3, "123");
        verify(mockPreparedStatement).executeUpdate();
        verify(mockConnection).close(); // Verify connection is closed in finally block
    }

    @Test
    public void testInsertCreditCard_Failure_due_to_SQLException() throws SQLException {
        DatabaseHelper spiedDbHelper = spy(dbHelper);
        doReturn(mockConnection).when(spiedDbHelper).connect();

        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException("Test DB error"));

        boolean result = spiedDbHelper.insertCreditCard("1234", "12/25", "123");
        assertFalse(result);
        verify(mockConnection).close(); // Verify connection is still closed in finally block
    }

    @Test
    public void testInsertCreditCard_Failure_NoConnection() {
        DatabaseHelper spiedDbHelper = spy(dbHelper);
        doReturn(null).when(spiedDbHelper).connect(); // Simulate connection returning null

        boolean result = spiedDbHelper.insertCreditCard("1234", "12/25", "123");
        assertFalse(result);
        // Connection was null, so prepareStatement and others shouldn't be called.
        // close() on a null connection won't be called from the finally block if conn was returned as null initially.
        // However, the connect() method itself has a finally block.
        // This specific test ensures that if connect() *returns* null, we don't proceed.
        verify(mockConnection, never()).prepareStatement(anyString());
    }
}
