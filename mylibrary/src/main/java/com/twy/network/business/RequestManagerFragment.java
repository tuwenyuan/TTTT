package com.twy.network.business;

import android.os.Bundle;

import com.twy.network.interfaces.OnRecvDataListener;

import androidx.fragment.app.Fragment;


/**
 * Author by twy, Email 499216359@qq.com, Date on 2019/1/14.
 * PS: Not easy to write code, please indicate.
 */
public class RequestManagerFragment extends Fragment {
    private StartRequestData startRequestData;
    private Net.IUnsubscribe iUnsubscribe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(startRequestData!=null)
            startRequestData.unsubscribe();
        if(iUnsubscribe!=null)
            iUnsubscribe.unsubscribe();
    }

    public void startRequestData(Observable observable, OnRecvDataListener dataListener, Net.IUnsubscribe iUnsubscribe){
        this.iUnsubscribe = iUnsubscribe;
        if(startRequestData == null) {
            startRequestData = new StartRequestData();
        }
        startRequestData.startRequestNetData(this,observable,dataListener);
    }

}
