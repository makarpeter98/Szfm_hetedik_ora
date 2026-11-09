package hu.unideb.szfm.makar.hetedik_ora;

import hu.unideb.szfm.makar.hetedik_ora.Person;
import hu.unideb.szfm.makar.hetedik_ora.PersonDataReader;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class PersonTest {
    @Mock
    private PersonDataReader pdr;
    private Person underTest1, underTest2;
    private AutoCloseable mocks;
    @BeforeEach
    public void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        given(pdr.getId()).willReturn(4, 6);
        given(pdr.getName()).willReturn(
                "George Smith",
                "Tom Smith"
        );
        underTest1 = Person.scannedPerson(pdr);
        underTest2 = Person.scannedPerson(pdr);
        verify(pdr, times(2)).getId();
        verify(pdr, times(2)).getName();
    }
    @AfterEach
    public void tearDown() throws Exception {
        if (mocks != null) {
            mocks.close();
        }
    }

    @Test
    public void scannedPersonTest() {
        Person pExpected = new Person(4, "George Smith");
        assertEquals(pExpected, underTest1);
        pExpected = new Person(6, "Tom Smith");
        assertEquals(pExpected, underTest2);
    }

    @Test
    public void PersonConstructorShouldThrowExceptionForWrongID() {
        assertThrows(RuntimeException.class, () -> {
            new Person(-10, "Adam");
        });
    }

    @Test
    public void toStringShouldReturnString()
    {
        String expected = "Person{" + "id= " + 4 + ", name = George Smith" + '}';
        assertEquals(expected, underTest1.toString());
    }

    @Test
    public void hashCodeShouldBeEqualForEqualPersons() {
        Person p1 = new Person(4, "George Smith");
        Person p2 = new Person(4, "George Smith");
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    @Timeout(value = 1500, unit = TimeUnit.MILLISECONDS)
    void test_this() throws InterruptedException {
        assertTimeout(
                Duration.ofMillis(5000),
                () -> Thread.sleep(1000)
        );
    }

    @ParameterizedTest
    @ValueSource(chars = {'T', 'o', 'm', 'S', 'm', 'i', 't', 'h'})
    void containsCharShouldReturnTrueForContainedChars(char input) {
        assertTrue(
                underTest2.nameContainsChar(input),
                "char: " + input
        );
    }

    @ParameterizedTest
    @ValueSource()
    void onallo_parameterezett_test(char input) {
        /*
        * az underTest1-re futtassatok le az x,y,z karaktereket, hogy szerepel-e az adott person neveben.
        * */
    }
}
