/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cbg.demo.di.component;

import com.example.cbg.demo.mvp.contract.UserContract;
import com.example.cbg.demo.mvp.ui.activity.UserActivity;
import com.example.cbg.demo.di.module.UserModule;
import com.example.cbg.demo.mvp.ui.fragment.RegisterFragment;
import com.example.mylibrary.di.ActivityScope;
import com.example.mylibrary.di.component.AppComponent;


import dagger.BindsInstance;
import dagger.Component;

/**
 * ================================================
 * 展示 Component 的用法
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.4.6">Component wiki 官方文档</a>
 * Created by JessYan on 09/04/2016 11:17
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@ActivityScope
@Component(modules = UserModule.class, dependencies = AppComponent.class)
public interface UserComponent {
    void inject(RegisterFragment fragment);
    @Component.Builder
    interface Builder {
        @BindsInstance
        UserComponent.Builder view(UserContract.View view);
        UserComponent.Builder appComponent(AppComponent appComponent);
        UserComponent build();
    }
}
