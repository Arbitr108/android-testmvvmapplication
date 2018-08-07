package android.smartdeveloper.ru.domain.usecase;

import android.smartdeveloper.ru.domain.entity.Gender;
import android.smartdeveloper.ru.domain.entity.User;
import android.smartdeveloper.ru.domain.repositories.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetListUseCaseTest {
    private static final String TAG = "GetListUseCaseTest";

    public static final String TEST_NAME = "Name";
    public static final String TEST_SURNAME = "Surname";
    public static final String TEST_AVATAR = "google.ru";
    public static final Gender TEST_MUSCULAR = Gender.M;
    public static final int TEST_AGE = 25;

    public UserListUseCase userListUseCase;

    @Mock
    public UserRepository repository;
    public TestScheduler testScheduler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testScheduler = new TestScheduler();
        userListUseCase = new UserListUseCase(repository, testScheduler, testScheduler);
    }

    @Test
    public void validAllUserAreReturned() {
        List<User> userList = new ArrayList<>();

        userList.add(new User(TEST_NAME, TEST_SURNAME, TEST_AVATAR, TEST_MUSCULAR, TEST_AGE));

        TestObserver<List<User>> testObserver = TestObserver.create();

        when(repository.all())
                .thenReturn(Single.just(userList));

        userListUseCase
                .getUsers()
                .doOnEvent(new BiConsumer<List<User>, Throwable>() {
                    @Override
                    public void accept(List<User> users, Throwable throwable) throws Exception {
                       assertEquals(userList.size(), users.size());
                    }
                })
                .subscribe(testObserver);

        testScheduler.triggerActions();

        testObserver
                .assertValue(new Predicate<List<User>>() {
                    @Override
                    public boolean test(List<User> users) throws Exception {

                        User user = users.get(0);

                        return user.getName().equals(TEST_NAME)
                                && user.getSurname().equals(TEST_SURNAME)
                                && user.getAvatarUrl().equals(TEST_AVATAR)
                                && user.getAge() == TEST_AGE;
                    }
                })
                .assertValue(new Predicate<List<User>>() {
                    @Override
                    public boolean test(List<User> users) throws Exception {
                        return users.size() == userList.size();
                    }
                })
                .assertValue(new Predicate<List<User>>() {
                    @Override
                    public boolean test(List<User> users) throws Exception {
                        return users.size() < userList.size() + 10 ;
                    }
                });
    }

    public void userIsCorrectlyUpdated(){

    }
}
