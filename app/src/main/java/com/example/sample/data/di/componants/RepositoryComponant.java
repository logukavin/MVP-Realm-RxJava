package com.example.sample.data.di.componants;


import com.example.sample.data.di.modules.RepositoryModule;
import com.example.sample.ui.user.UserController;
import com.example.sample.ui.userpager.UserPagerController;

import dagger.Component;

@Component(modules = {RepositoryModule.class})
public interface RepositoryComponant {

    void inject(UserController userController);

    void inject(UserPagerController userPagerController);



}
