package com.example.sample.ui.user;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sample.app.AppController;
import com.example.sample.app.AppPreference;
import com.example.sample.base.BaseActivity;
import com.example.sample.data.navigator.Navigator;
import com.example.sample.data.response.userrealm.DataItems;
import com.example.sample.data.response.userrealm.UserResponses;
import com.example.sample.databinding.ActivityUserBinding;
import com.example.sample.utils.UiUtils;

import java.util.List;

public class UserActivity extends BaseActivity<ActivityUserBinding> implements UserView, UserAdapter.UserClickListener {

    private UserAdapter mUserAdapter;
    private UserController mUserController;
    private AppPreference mAppPreference;
    private Navigator mNavigator;
    private AlertDialog dialog;

    @Override
    protected ActivityUserBinding getViewBinding() {
        return ActivityUserBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupViews() {
        mAppPreference = AppController.getInstance().getAppPreference();
        mNavigator = AppController.getInstance().getNavigator();
        mUserController = new UserController();
        mUserController.setView(this);

        mUserController.getUser();
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mUserAdapter = new UserAdapter(getContext(), this);
        binding.recyclerview.setAdapter(mUserAdapter);
        fetchData();
    }

    @Override
    public void showLoading() {
        if (dialog == null) {
            dialog = UiUtils.showLoadingAlertDialog(this);
            assert dialog != null;
            dialog.show();
        } else dialog.show();
    }

    @Override
    public void hideLoading() {
        if (dialog != null && dialog.isShowing()) ;
        dialog.dismiss();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void OnUserResponse(UserResponses mUserResponse) {
        if (mUserResponse != null) {
            mUserAdapter.setUserAdapter(mUserResponse.getData());
        }
    }

    @Override
    public void onItemClick(int absoluteAdapterPosition) {

        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        mUserController.destroy();
    }


    private void fetchData() {
        new Handler().postDelayed(() -> {
            List<DataItems> items = mUserController.fetchDataFromRealm();
            mUserAdapter.setUserAdapter(items);
        }, 1000); // Delay to ensure data is saved and loaded from Realm
    }
}
