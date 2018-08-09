package android.smartdeveloper.ru.data.database;

import android.content.Context;
import android.smartdeveloper.ru.data.network.RestService;
import android.smartdeveloper.ru.data.repositories.UserRepositoryImpl;
import android.smartdeveloper.ru.domain.entity.Gender;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.domain.repositories.UserRepository;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockWebServer;


@RunWith(AndroidJUnit4.class)
public class DbTest {

    Db database;
    UserRepository repository;
    RestService restService;
    MockWebServer mockWebServer;

    @Before
    public void setUp() throws IOException {
        Context context = InstrumentationRegistry.getContext();
        database = Db.getInstanceInMemory(context);
        mockWebServer = new MockWebServer();
        restService = RestService.getInstance();
        repository = new UserRepositoryImpl(restService, database);
        mockWebServer.start();
    }
    @After
    public void release() throws IOException {
        mockWebServer.shutdown();
    }
    @Test
    public void testDb(){
        repository
                .add(new User("TestName", "TestSurname", "TestAvatar", Gender.M, 25));

    }
}
