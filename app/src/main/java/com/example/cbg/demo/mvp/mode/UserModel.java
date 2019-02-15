package com.example.cbg.demo.mvp.mode;

import com.example.cbg.demo.mvp.contract.UserContract;
import com.example.cbg.demo.mvp.mode.api.service.UserService;
import com.example.cbg.demo.mvp.mode.entity.User;
import com.example.mylibrary.di.ActivityScope;
import com.example.mylibrary.itegretion.IRepositoryManager;
import com.example.mylibrary.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class UserModel extends BaseModel implements UserContract.Model {

    @Inject
    public UserModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<String> getUser() {
        return mRepositoryManager.obtainRetrofitService(UserService.class).getUsers();
    }
}
