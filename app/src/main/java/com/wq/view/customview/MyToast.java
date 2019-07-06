package com.wq.view.customview;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wq.allandroid.R;

public class MyToast {

    private Context mContext;

    public MyToast(Context context) {
        this.mContext = context;
    }

    /**
     * 设置Toast在中间
     */

    private void setCenterToast() {
        Toast toast = Toast.makeText(mContext, "Toast 在中间", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 完全自定义
     */
    public void init() {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.dialog_layout, null);
        TextView title = (TextView) layout.findViewById(R.id.tv_custom_dialog_message);
        title.setText("this is a Toast");
        Toast toast = new Toast(mContext.getApplicationContext());
        toast.setGravity(Gravity.RIGHT | Gravity.TOP, 12, 40);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
