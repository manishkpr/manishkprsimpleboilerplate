package com.manishkprsimpleboilerplate;

import com.manishkprboilerplate.base.UiView;
import com.manishkprsimpleboilerplate.models.Suggestions;


/**
 * Created by Munish Kapoor on 10/2/17.
 */

public interface DemoListingUiView extends UiView{

        void showData(Suggestions data);
        void showError(String error);

}
