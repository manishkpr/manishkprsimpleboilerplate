package com.manishkprsimpleboilerplate;

import android.app.Activity;

import com.manishkprboilerplate.base.BasePresenter;
import com.manishkprboilerplate.web_services.RxUtil;
import com.manishkprboilerplate.web_services.WebClient;
import com.manishkprsimpleboilerplate.models.Suggestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;


/**
 * Created by Munish Kapoor on 10/2/17.
 */

public class DemoListingPresenter extends BasePresenter<DemoListingUiView> {

    Activity activity;
    private Subscription mSubscription;

    public DemoListingPresenter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void attachView(DemoListingUiView mvpView) {
        super.attachView(mvpView);
    }
    //### This is just example of how to use the methods
    void getList(String name){
        checkViewAttached();
        RxUtil.unSubscribe(mSubscription);
        mSubscription = WebClient.getInstance()
                //.getClient(null) null mean default server http://webheavens.com/  You can also replace null with your url
                .getClient(null)
                .create(APIService.class)
                .getSuggestions(getParams(name))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Suggestions>() {

                    @Override
                    public void onCompleted() {
                        Timber.i("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("onError " + e.getMessage());
                        getMvpView().showError((e!=null ? e.getMessage() : activity.getString(R.string.there_is_problem_in_server)));
                    }

                    @Override
                    public void onNext(Suggestions allListings) {
                        if(allListings!=null) {
                            Timber.i("onNext " + allListings.getResults().size());
                        }
                        getMvpView().showData(allListings);
                    }

                });
    }

    Map<String,String> getParams(String name){

        Map<String, String> params = new HashMap<>();

        params.put("name", name);
        Timber.d("search params "+ Arrays.asList(params).toString());

        return params;

    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }
}
