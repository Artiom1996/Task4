package com.epam.androidlab.task4;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;

public class MainActivity extends AppCompatActivity {

    private static final String X_COORDINATE = "com.epam.androidlab.X_COORDINATE";
    private static final String Y_COORDINATE = "com.epam.androidlab.Y_COORDINATE";
    private int height;
    private int width;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.tv_hello_world)
    TextView tvHelloWorld;

    private ArrayList<Float> position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        height = metrics.heightPixels;
        width = metrics.widthPixels;

        if (savedInstanceState != null) {

            position = new ArrayList<>(2);
            position.add(0, savedInstanceState.getFloat(X_COORDINATE));
            position.add(1, savedInstanceState.getFloat(Y_COORDINATE));
            tvHelloWorld.setX(position.get(0));
            tvHelloWorld.setY(position.get(1));

            if (position.get(0) < 200 && position.get(1) < 200) {
                tvHelloWorld.setX(0.0f);
                tvHelloWorld.setY(0.0f);
                position.add(0, 0.0f);
                position.add(0, 0.0f);

            } else if (position.get(0) > 200 && position.get(1) < 200) {
                tvHelloWorld.setX(width - 300.0f);
                tvHelloWorld.setY(0.0f);
                position.add(0, width - 300.0f);
                position.add(1, 0.0f);

            } else if (position.get(0) > 200 && position.get(1) > 200) {
                tvHelloWorld.setX(width - 400);
                tvHelloWorld.setY(height - 500.0f);
                position.add(0, width - 400.0f);
                position.add(1, height - 500.0f);

            } else if (position.get(0) < 200 && position.get(1) > 200) {
                tvHelloWorld.setX(50.0f);
                tvHelloWorld.setY(height - 500f);
                position.add(0, 50.0f);
                position.add(1, height - 500f);
            }

        }


        Picasso.with(this).load("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/European_grey_wolf_in_Prague_zoo.jpg/800px-European_grey_wolf_in_Prague_zoo.jpg").into(imageView);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        if (savedInstanceState == null) {
            position = new ArrayList<>(2);
            position.add(0, 0.0f);
            position.add(1, 0.0f);
        }

    }


    @OnLongClick(R.id.image_view)
    public boolean onClick() {

        if (position.get(0) < 200 && position.get(1) < 200) {
            TranslateAnimation animation = new TranslateAnimation(position.get(0), width - 300.0f, position.get(1), 0.0f); // new TranslateAnimation (float fromXDelta,float toXDelta, float fromYDelta, float toYDelta)
            animation.setDuration(2000); // animation duration
            animation.setFillEnabled(true);
            animation.setFillAfter(true);
            position.add(0, width - 300.0f);
            position.add(1, 0.0f);

            tvHelloWorld.startAnimation(animation);
        } else if (position.get(0) > 200 && position.get(1) < 200) {
            TranslateAnimation animation = new TranslateAnimation(position.get(0), width - 300.0f, position.get(1), height - 500.0f); // new TranslateAnimation (float fromXDelta,float toXDelta, float fromYDelta, float toYDelta)
            animation.setDuration(2000); // animation duration
            animation.setFillEnabled(true);
            animation.setFillAfter(true);
            position.add(0, width - 300.0f);
            position.add(1, height - 500.0f);
            tvHelloWorld.startAnimation(animation);
        } else if (position.get(0) > 200 && position.get(1) > 200) {
            TranslateAnimation animation = new TranslateAnimation(position.get(0), 50f, position.get(1), height - 500.0f); // new TranslateAnimation (float fromXDelta,float toXDelta, float fromYDelta, float toYDelta)
            animation.setDuration(2000); // animation duration
            animation.setFillEnabled(true);
            animation.setFillAfter(true);
            position.add(0, 50.0f);
            position.add(1, height - 500.0f);
            tvHelloWorld.startAnimation(animation);
        } else if (position.get(0) < 200 && position.get(1) > 200) {
            TranslateAnimation animation = new TranslateAnimation(position.get(0), 50.0f, position.get(1), 0.0f); // new TranslateAnimation (float fromXDelta,float toXDelta, float fromYDelta, float toYDelta)
            animation.setDuration(2000); // animation duration
            animation.setFillEnabled(true);
            animation.setFillAfter(true);
            position.add(0, 50.0f);
            position.add(1, 0.0f);
            tvHelloWorld.startAnimation(animation);
        }


        return true;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat(X_COORDINATE, position.get(0));
        outState.putFloat(Y_COORDINATE, position.get(1));

    }


}
