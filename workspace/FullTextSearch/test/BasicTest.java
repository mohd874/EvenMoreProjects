import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    public void basicTest() {
        Fixtures.load("test-data.yml");
        assertEquals(3, Post.findAll().size());
    }

}
