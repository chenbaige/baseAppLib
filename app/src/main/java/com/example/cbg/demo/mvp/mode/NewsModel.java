package com.example.cbg.demo.mvp.mode;

import com.example.cbg.demo.mvp.contract.NewsContract;
import com.example.mylibrary.di.FragmentScope;
import com.example.mylibrary.itegretion.IRepositoryManager;
import com.example.mylibrary.mvp.BaseModel;

import javax.inject.Inject;

@FragmentScope
public class NewsModel extends BaseModel implements NewsContract.Model {
    @Inject
    public NewsModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
