package com.example.sample.ui.user;

import com.example.sample.base.LoadBaseView;
import com.example.sample.data.response.userrealm.UserResponses;

public interface UserView extends LoadBaseView {
    void OnUserResponse(UserResponses userResponse);
}
