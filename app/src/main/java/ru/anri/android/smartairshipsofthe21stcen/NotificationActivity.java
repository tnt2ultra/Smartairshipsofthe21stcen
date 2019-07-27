package ru.anri.android.smartairshipsofthe21stcen;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    private static final TimeInterpolator GAUGE_ANIMATION_INTERPOLATOR = new LinearInterpolator();
    private static final int MAX_LEVEL = 900;
    private static final long GAUGE_ANIMATION_DURATION = 10000;
    private SeekBar mSeekBar;
    private TextView textViewDron;
    private TextView textViewDron2;
    private TextView textViewRest;
    private boolean toastShowed;
    private Button buttonDown;
    private Button buttonReceive;
    private Button buttonReview;
    private TextView editTextReview;
    private String needShowDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Intent intent = getIntent();

        needShowDown = intent.getStringExtra("needShowDown");

        mSeekBar = findViewById(R.id.seekBar);
        textViewDron = findViewById(R.id.textViewDron);
        textViewDron2 = findViewById(R.id.textViewDron2);
        textViewRest = findViewById(R.id.textViewRest);
        buttonDown = findViewById(R.id.buttonDown);
        buttonReceive = findViewById(R.id.buttonReceive);
        buttonReview = findViewById(R.id.buttonReview);
        editTextReview = findViewById(R.id.editTextReview);
        toastShowed = false;

        buttonDown.setVisibility(GONE);
        buttonReceive.setVisibility(GONE);
        buttonReview.setVisibility(GONE);
        editTextReview.setVisibility(GONE);

        mSeekBar.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                int minute = (30 - progress / 30);
                textViewRest.setText( minute + " " + getString(R.string.textViewRest));
                if( (minute == 5) && !toastShowed) {
                    Toast.makeText(getApplicationContext(), getString(R.string.itsTime), Toast.LENGTH_LONG).show();
                    toastShowed = true;
                }
                else if(minute == 0) {
                    textViewDron.setText(getString(R.string.textViewDron_new));
                    textViewDron2.setText(getString(R.string.textViewDron2_new));
                    textViewRest.setText("");
                    textViewRest.setVisibility(GONE);

                    if ("1".equals(needShowDown)) {
                        buttonDown.setVisibility(VISIBLE);
                        buttonReceive.setVisibility(GONE);
//                        buttonReceive.setEnabled(false);
                    } else {
                        buttonDown.setEnabled(false);
                        buttonDown.setVisibility(VISIBLE);
//                        buttonDown.setVisibility(GONE);
                        buttonReceive.setVisibility(VISIBLE);
                    }

                }
            }
        });

        ObjectAnimator animator = ObjectAnimator.ofInt(mSeekBar, "progress", 0, MAX_LEVEL);
        animator.setInterpolator(GAUGE_ANIMATION_INTERPOLATOR);
        animator.setDuration(GAUGE_ANIMATION_DURATION);
        animator.start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDown:
                buttonReceive.setVisibility(VISIBLE);
//                buttonDown.setVisibility(GONE);
                buttonDown.setEnabled(false);
                break;
            case R.id.buttonReceive:
                buttonReview.setVisibility(VISIBLE);
                editTextReview.setVisibility(VISIBLE);
//                buttonReceive.setVisibility(GONE);
                buttonReceive.setEnabled(false);
                break;
            case R.id.buttonReview:
                buttonReview.setEnabled(false);
                Toast.makeText(getApplicationContext(), getString(R.string.thanks), Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

}
