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

import io.reactivex.Single;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateUserUseCaseTest {

    public static final String TEST_NAME = "Name";
    public static final String TEST_SURNAME = "Surname";
    public static final String TEST_AVATAR = "google.ru";
    public static final Gender TEST_MUSCULAR = Gender.M;
    public static final int TEST_AGE = 25;

    @Mock
    public UpdateUserUseCase updateUserUseCase;

    @Mock
    public UserRepository repository;
    public TestScheduler testScheduler;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testScheduler = new TestScheduler();

        updateUserUseCase = mock(UpdateUserUseCase.class);
    }

    @Test
    public void updateUser(){
        User user = new User(TEST_NAME, TEST_SURNAME, TEST_AVATAR, TEST_MUSCULAR, TEST_AGE);

        when(repository.update(user))
                .thenReturn(Single.just(user));

        when(updateUserUseCase.getExecutionThread())
                .thenReturn(testScheduler);

        when(updateUserUseCase.getPostExecutionThread())
                .thenReturn(testScheduler);

        when(updateUserUseCase.getUserRepository())
                .thenReturn(repository);

        when(updateUserUseCase.updateUser(user))
                .thenCallRealMethod();

        TestObserver<User> testObserver = new TestObserver<>();

        updateUserUseCase
                .updateUser(user)
                .subscribe(testObserver);

        testScheduler.triggerActions();

        testObserver.assertValue(new Predicate<User>() {
            @Override
            public boolean test(User user) throws Exception {

                return user.getName().equals(TEST_NAME)
                        && user.getSurname().equals(TEST_SURNAME)
                        && user.getAvatarUrl().equals(TEST_AVATAR)
                        && user.getAge() == TEST_AGE;
            }
        });
    }

}
