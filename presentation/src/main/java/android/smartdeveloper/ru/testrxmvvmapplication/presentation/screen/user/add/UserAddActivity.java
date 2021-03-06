package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.add;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.smartdeveloper.ru.testrxmvvmapplication.R;
import android.smartdeveloper.ru.testrxmvvmapplication.databinding.AcitvityUserAddBinding;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseMVVMActivity;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.utils.LayoutWatcher;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.NumberPicker;
import android.widget.ScrollView;

public class UserAddActivity extends BaseMVVMActivity<UserAddViewModel, AcitvityUserAddBinding, UserAddRouter> {


    private NumberPicker age;
    private ScrollView scrollContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewStub stub = findViewById(R.id.backgroundCover);
        View inflated = stub.inflate();
        ViewGroup.LayoutParams layoutParams = inflated.getLayoutParams();
        layoutParams.height = 200;
        layoutParams.width = 200;
        inflated.setLayoutParams(layoutParams);

        age = (NumberPicker)findViewById(R.id.ageText);
        age.setMinValue(16);
        age.setMaxValue(100);

        scrollContainer = (ScrollView)findViewById(R.id.scrollContainer);
        scrollContainer.addOnLayoutChangeListener(new LayoutWatcher());

        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Добавить пользователя");

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.acitvity_user_add;
    }

    @Override
    protected UserAddViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UserAddViewModel.class);
    }

    @Override
    protected UserAddRouter provideRouter() {
        return new UserAddRouter(this);
    }

    public static Intent getIntent(Activity activity) {
        Intent intent = new Intent(activity, UserAddActivity.class);
        return intent;
    }
}
