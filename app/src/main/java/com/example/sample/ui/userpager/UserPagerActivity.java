package com.example.sample.ui.userpager;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sample.base.BaseActivity;
import com.example.sample.databinding.ActivityUserBinding;
import com.example.sample.pager.ItemAdapter;

public class UserPagerActivity extends BaseActivity<ActivityUserBinding> implements UserPagerView {
    private ItemAdapter mUserAdapter;
    private UserPagerController mUserPagerController;
    @Override
    protected ActivityUserBinding getViewBinding() {
        return ActivityUserBinding.inflate(getLayoutInflater());
    }
    @Override
    protected void setupViews() {
        mUserPagerController = new UserPagerController();
        mUserPagerController.setView(this);

        mUserPagerController.getUserPager();
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mUserAdapter = new ItemAdapter(getContext());
        binding.recyclerview.setAdapter(mUserAdapter);
        mUserPagerController.getItemsPagedList().observe(this, items -> mUserAdapter.submitList(items));
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


}
