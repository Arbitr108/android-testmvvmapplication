package android.smartdeveloper.ru.data.net;

import android.smartdeveloper.ru.data.entity.UserResponse;
import android.smartdeveloper.ru.data.network.RestService;
import android.smartdeveloper.ru.domain.exception.ServiceNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class RestApiTest {

    public static final String BASE_URI = "/5D84CB49-4F56-4AD0-FF44-D3FC94518C00/BFBC3E8C-03A1-BECE-FF6F-C81110FB3400/";

    private RestService restService;
    private MockWebServer mockWebServer;
    private HttpUrl url;

    @Before
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
//        mockWebServer.setDispatcher(new MockServerDispatcher());
        mockWebServer.start();
        url = mockWebServer.url(BASE_URI);
        restService = RestService.getInstance(url.toString());
    }

    @After
    public void release() throws IOException {
        mockWebServer.shutdown();
    }

    /**
     * CLASS NOTES: how to get data from file
       RestApiTest.class.getClassLoader().getResourceAsStream("response.json")
     */

    @Test
    public void testNotFoundException() throws IOException {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(404);
        mockWebServer.enqueue(mockResponse);
        TestObserver<List<UserResponse>> testSubscriber = TestObserver.create();
        restService
                .all()
                .subscribe(testSubscriber);
        //testSubscriber.awaitTerminalEvent(5, TimeUnit.SECONDS);
        testSubscriber.assertError(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {
                return throwable instanceof ServiceNotFoundException;
            }
        });
    }
}
