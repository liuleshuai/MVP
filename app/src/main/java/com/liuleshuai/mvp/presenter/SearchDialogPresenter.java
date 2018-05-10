package com.liuleshuai.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.liuleshuai.common.base.BasePresenter;
import com.liuleshuai.mvp.app.Constants;
import com.liuleshuai.mvp.bean.MovieEntity;
import com.liuleshuai.mvp.http.MovieLoader;
import com.liuleshuai.mvp.model.SearchDialogContract;

import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by LiuKuo at 2018/3/23
 */

public class SearchDialogPresenter extends BasePresenter<SearchDialogContract.View> implements SearchDialogContract.Presenter {

    @Override
    public void getMovieData() {
        MovieLoader.builder(Constants.MOVIE_URL).getMovie(0, 10)
                .subscribe(new Consumer<List<MovieEntity.SubjectsBean>>() {
                    @Override
                    public void accept(List<MovieEntity.SubjectsBean> subjectsBeen) throws Exception {
                        mView.showMovieData(JSON.toJSONString(subjectsBeen));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showToast(throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        // complete
                    }
                });
    }

}
