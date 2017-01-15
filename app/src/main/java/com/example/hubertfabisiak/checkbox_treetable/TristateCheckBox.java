package com.example.hubertfabisiak.checkbox_treetable;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by Pawel on 14.01.2017.
 */

public class TristateCheckBox extends CheckBox {

    public static final int UNCHECKED = 0;
    public static final int UNKNOW = 1;
    public static final int CHECKED = 2;

    private int state;

    public TristateCheckBox(Context context){
        super(context);
        init();
    }

    public TristateCheckBox(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        init();
    }

    public TristateCheckBox(Context context, AttributeSet attributeSet, int defStyleAttr){
        super(context, attributeSet, defStyleAttr);
        init();
    }

    private void init(){
        state = UNCHECKED;
        update();

        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (state){
                    case 0:
                        state  = UNKNOW;
                        break;
                    case 1:
                        state = CHECKED;
                        break;
                    case 2:
                        state = UNCHECKED;
                        break;
                }
                update();
            }
        });
    }

    private void update(){
        int draw = R.drawable.ic_check_box_outline_blank_white_24dp;

        switch (state) {
            case UNCHECKED:
                draw = R.drawable.ic_check_box_outline_blank_white_24dp;
                break;
            case UNKNOW:
                draw = R.drawable.ic_indeterminate_check_box_white_24dp;
                break;
            case CHECKED:
                draw = R.drawable.ic_check_box_white_24dp;
                break;
        }
        setButtonDrawable(draw);
    }

    public int getState(){
        return  state;
    }

    public void setState(int state){
        this.state = state;
    }
}
