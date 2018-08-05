package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.list;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.smartdeveloper.ru.testrxmvvmapplication.R;
import android.smartdeveloper.ru.testrxmvvmapplication.databinding.ActivityUserListBinding;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.BaseMVVMActivity;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import io.reactivex.subjects.PublishSubject;

public class UserListActivity extends BaseMVVMActivity<UserListViewModel,ActivityUserListBinding,UserListRouter> {
    private static final String TAG = "UserListActivity";
    private SearchView searchView;
    private PublishSubject<String> searchSubject = PublishSubject.create();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "------------------onCreate:------------------- ");
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(viewModel.adapter);
        binding.recyclerView.setHasFixedSize(false);
        //binding.recyclerView.setNestedScrollingEnabled(false);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);

        if(savedInstanceState == null){
            viewModel.load();
            viewModel.initObservers();
            viewModel.observeSearch(searchSubject);
        }
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    protected UserListViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UserListViewModel.class);
    }

    @Override
    protected UserListRouter provideRouter() {
        return new UserListRouter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                searchSubject.onNext(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                searchSubject.onNext(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

}
