package com.epam.androidlab.task4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;

    private int currentGravityValue = Gravity.TOP | Gravity.START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);

        int imageViewSize = getResources().getInteger(R.integer.image_view_size);
        Picasso.with(this).load(getResources().getString(R.string.image_url)).resize(imageViewSize, imageViewSize).into(imageView);

        imageView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                currentGravityValue = changeTextViewGravity();
                return true;
            }
        });
    }

    private int changeTextViewGravity(){
        int result = Gravity.TOP | Gravity.START;

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        switch (currentGravityValue) {
            case Gravity.TOP | Gravity.START:
                result= Gravity.TOP | Gravity.END;
                layoutParams.gravity = Gravity.TOP | Gravity.END;
                break;
            case Gravity.TOP | Gravity.END:
                result= Gravity.BOTTOM | Gravity.END;
                layoutParams.gravity = Gravity.BOTTOM | Gravity.END;
                break;
            case Gravity.BOTTOM | Gravity.END:
                result= Gravity.BOTTOM | Gravity.START;
                layoutParams.gravity = Gravity.BOTTOM | Gravity.START;
                break;
            case Gravity.BOTTOM | Gravity.START:
                result= Gravity.TOP | Gravity.START;
                layoutParams.gravity = Gravity.TOP | Gravity.START;
                break;
            default:
                break;
        }
        textView.setLayoutParams(layoutParams);
        return result;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("textViewGravityValue",currentGravityValue);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        currentGravityValue = savedInstanceState.getInt("textViewGravityValue");
        layoutParams.gravity = currentGravityValue;
        textView.setLayoutParams(layoutParams);
    }
}
