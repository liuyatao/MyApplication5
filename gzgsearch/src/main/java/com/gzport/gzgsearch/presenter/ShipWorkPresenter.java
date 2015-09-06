package com.gzport.gzgsearch.presenter;

import com.gzport.gzgsearch.model.ErrorMsg;
import com.gzport.gzgsearch.model.IShipWorkImp;
import com.gzport.gzgsearch.model.IShipWorkModel;
import com.gzport.gzgsearch.model.OnResultListener;
import com.gzport.gzgsearch.model.bean.ShipWorkBean;
import com.gzport.gzgsearch.view.IShipWorkView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘亚涛 on 2015/8/12.
 */
public class ShipWorkPresenter {

    private IShipWorkView iShipWorkView;
    private IShipWorkModel iShipWorkModel;
    private List<ShipWorkBean> shipWorkBeans = new ArrayList<>();

    public ShipWorkPresenter(IShipWorkView iShipWorkView) {
        this.iShipWorkView = iShipWorkView;
        iShipWorkModel = new IShipWorkImp();
    }

    public List<ShipWorkBean> getData(int pageCount, int start, int companyID) {
        iShipWorkModel.getData(pageCount, start, companyID, new OnResultListener() {

            @Override
            public void onSucess(String s) {

            }

            @Override
            public void onSucess(List list) {
                shipWorkBeans=list;
                iShipWorkView.endLoading(list);
            }

            @Override
            public void onFail(ErrorMsg errorMsg) {
                iShipWorkView.showErroeMsg(errorMsg);
            }
        });
        return shipWorkBeans;
    }
}
