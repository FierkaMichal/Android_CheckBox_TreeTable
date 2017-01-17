package com.example.hubertfabisiak.checkbox_treetable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by Pawel on 14.01.2017.
 */

public abstract class TristateCheckBox extends CheckBox {

    public static final int UNCHECKED = 0;
    public static final int UNKNOWN = 1;
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

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (state){
                    case UNCHECKED:
                        state  = UNKNOWN;
                        onChangedToUnknown();
                        break;
                    case UNKNOWN:
                        state = CHECKED;
                        onChangedToChecked();
                        break;
                    case CHECKED:
                        state = UNCHECKED;
                        onChangedToUnchecked();
                        break;
                }
                update();
            }
        });
//        setOnCheckedChangeListener(new OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                switch (state){
//                    case UNCHECKED:
//                        state  = UNKNOWN;
//                        onChangedToUnknown();
//                        break;
//                    case UNKNOWN:
//                        state = CHECKED;
//                        onChangedToChecked();
//                        break;
//                    case CHECKED:
//                        state = UNCHECKED;
//                        onChangedToUnchecked();
//                        break;
//                }
//                update();
//            }
//        });
    }

    private void update(){
        int draw = R.drawable.ic_check_box_outline_blank_white_24dp;

        switch (state) {
            case UNCHECKED:
                draw = R.drawable.ic_check_box_outline_blank_white_24dp;
                break;
            case UNKNOWN:
                draw = R.drawable.ic_indeterminate_check_box_white_24dp;
                break;
            case CHECKED:
                draw = R.drawable.ic_check_box_white_24dp;
                break;
        }
        setButtonDrawable(draw);
    }

    public abstract void onChangedToChecked();
    public abstract void onChangedToUnchecked();
    public abstract void onChangedToUnknown();

    public int getState(){
        return  state;
    }

    public void setState(int state){
        this.state = state;
        update();
    }

}
