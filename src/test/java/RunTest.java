import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RunTest {
    //its RunTest origin

    @Test
    public void test1(){
        System.out.println("Successful test");
        Assertions.assertEquals("Test", "Test");
    }

    @Test
    public void test2(){
        System.out.println("Error test");
        Assertions.assertEquals("AAA", "aaa");
    }

    @BeforeEach
    public void writeTitle(){
        System.out.println("Before each test");
    }

    @AfterAll
    public static void doConclude(){
        System.out.println("This is the end");
    }
}
