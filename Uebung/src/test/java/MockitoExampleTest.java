import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MockitoExampleTest {

    interface Database {
        String fetchData();
    }

    class Service {
        private final Database database;

        public Service(Database database) {
            this.database = database;
        }

        public String getData() {
            return database.fetchData();
        }
    }

    @Test
    void testServiceWithMockDatabase() {
        // Mock erstellen
        Database mockDatabase = mock(Database.class);

        // Verhalten des Mocks definieren
        when(mockDatabase.fetchData()).thenReturn("mockedData");

        // Service testen
        Service service = new Service(mockDatabase);
        String result = service.getData();

        // Überprüfen, ob die Logik korrekt ist
        assertEquals("mockedData", result);

        // Verifizieren, dass fetchData() genau einmal aufgerufen wurde
        verify(mockDatabase, times(1)).fetchData();
    }
}