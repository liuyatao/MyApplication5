package com.gzport.gzgsearch.model;

/**
 * Created by 刘亚涛 on 2015/8/12.
 */
public class ErrorMsg {
    private int errorCode;
    private String errorInfor;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfor() {
        return errorInfor;
    }

    public void setErrorInfor(String errorInfor) {
        this.errorInfor = errorInfor;
    }

    public ErrorMsg(int errorCode, String errorInfor) {
        this.errorCode = errorCode;
        this.errorInfor = errorInfor;
    }
}
