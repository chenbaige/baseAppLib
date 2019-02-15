package com.example.mylibrary.net.rx;

import android.app.Dialog;
import android.content.Context;

/**
 * Title: basicmvpframwork
 * <p>
 * Description:
 * <p>
 * Author:baigege (baigegechen@gmail.com)
 * <p>
 * Date:2017-05-25
 */
public class ProgressDialogHandler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 0;

//    private SweetAlertDialog mDialog;

    Dialog mSpotsDialog;

    private Context mContext;

    private boolean cancelable;

    private onProgressCancelistener mCancelistener;

    public ProgressDialogHandler(Context context) {
        this(context, true, null);
    }

    public ProgressDialogHandler(Context context, boolean cancelable, onProgressCancelistener cancelistener) {
        mContext = context;
        this.cancelable = cancelable;
        mCancelistener = cancelistener;
        initDialog();
    }

    public interface onProgressCancelistener {
        void onProgressCancel();
    }

    public void initDialog() {

        if (null == mSpotsDialog) {
//            mSpotsDialog = new SpotsDialog(mContext, "Loading", R.style.Custom);
            mSpotsDialog = new Dialog(mContext);
        }
    }

    public void showDialog() {
//        if (mDialog != null && !mDialog.isShowing())
//            mDialog.show();
        if (null != mSpotsDialog && !mSpotsDialog.isShowing())
            mSpotsDialog.show();
    }

    public void dismissDialog() {
//        if (mDialog != null)
//            mDialog.dismiss();
        if (null != mSpotsDialog && mSpotsDialog.isShowing())
            mSpotsDialog.dismiss();

    }
}
