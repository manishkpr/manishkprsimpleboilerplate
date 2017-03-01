package com.manishkprsimpleboilerplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.manishkprsimpleboilerplate.models.Suggestions;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 *  Created by Munish Kapoor on 10/2/17.
 *  DemoListingActivity, DemoListPresenter, DemoListingView files are example of how to use
 *  MVP of this Library manishkprsimpleboilerplate
 *
 *  For POJO use : http://www.jsonschema2pojo.org/
 */

public class DemoListingActivity extends AppCompatActivity implements DemoListingUiView {

    @BindView(R.id.results)     TextView results;
    @BindView(R.id.progressBar) ProgressBar progressBar;


    DemoListingPresenter demoListingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init(){
        ButterKnife.bind(this);
        demoListingPresenter    =   new DemoListingPresenter(this);
        demoListingPresenter.attachView(this);
        demoListingPresenter.getList("m");
        results.setText("");
    }

    @Override
    public void showError(String s) {
        results.setText(s);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showData(Suggestions data) {
        progressBar.setVisibility(View.GONE);
        appendList(data);
    }

    void appendList(Suggestions data){
        if(data!=null){
            if(data.getResults()!=null){
                if(data.getResults().size()!=0){
                    for(int i=0;i<data.getResults().size();i++){
                        results.append(data.getResults().get(i).getName()+", ");
                    }
                    return;
                }
            }
        }
        results.setText("Error");
    }
}
