package scrumbledore.kinoproject;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import scrumbledore.kinoproject.security.TestUtils;

@SpringBootTest
class KinoProjectApplicationTests {

    @BeforeAll
    public static void iniSetUp(){
        System.setProperty(TestUtils.h2UrlName, TestUtils.h2UrlValue);
        System.setProperty(TestUtils.h2UsernameName, TestUtils.h2UsernameValue);
        System.setProperty(TestUtils.h2PassName, TestUtils.h2PassValue);
        System.setProperty(TestUtils.tokenSecretName, TestUtils.tokenSecretValue);
    }

    @AfterAll
    public static void tearDown(){
        System.clearProperty(TestUtils.h2UrlName);
        System.clearProperty(TestUtils.h2UsernameName);
        System.clearProperty(TestUtils.h2PassName);
        System.clearProperty(TestUtils.tokenSecretName);
    }

    @Test
    void contextLoads() {
    }

}
