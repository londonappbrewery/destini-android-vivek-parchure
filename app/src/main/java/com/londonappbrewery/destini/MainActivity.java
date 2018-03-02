package com.londonappbrewery.destini;

import android.content.DialogInterface;
import android.icu.text.AlphabeticIndex;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    TextView storyTextView;
    Button buttonTop;
    Button buttonBottom;

    public class StoryRecord {
        private int storyText;
        private int ans1Text;
        private int ans2Text;
        private int ans1Action;
        private int ans2Action;

        public StoryRecord(int storyText, int ans1Text, int ans2Text, int ans1Action, int ans2Action) {
            this.storyText = storyText;
            this.ans1Text = ans1Text;
            this.ans2Text = ans2Text;
            this.ans1Action = ans1Action;
            this.ans2Action = ans2Action;
        }

        public int getStoryText() {
            return storyText;
        }

        public void setStoryText(int storyText) {
            this.storyText = storyText;
        }

        public int getAns1Text() {
            return ans1Text;
        }

        public void setAns1Text(int ans1Text) {
            this.ans1Text = ans1Text;
        }

        public int getAns2Text() {
            return ans2Text;
        }

        public void setAns2Text(int ans2Text) {
            this.ans2Text = ans2Text;
        }

        public int getAns1Action() {
            return ans1Action;
        }

        public void setAns1Action(int ans1Action) {
            this.ans1Action = ans1Action;
        }

        public int getAns2Action() {
            return ans2Action;
        }

        public void setAns2Action(int ans2Action) {
            this.ans2Action = ans2Action;
        }
    }

    StoryRecord mStoryRecord;
    ArrayList<StoryRecord> mStoryRecordList;
    int mStoryRecordsIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        storyTextView = (TextView) findViewById(R.id.storyTextView);
        buttonTop = (Button) findViewById(R.id.buttonTop);
        buttonBottom = (Button) findViewById(R.id.buttonBottom);

        if (savedInstanceState != null) {
            mStoryRecordsIndex = savedInstanceState.getInt("StoryIndex");
        } else {
            mStoryRecordsIndex = 0;
        }

        initializeStory();

        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        buttonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("destini","buttonTop pressed!");
                updateStory(view.getId());
            }
        });

        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        buttonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("destini","buttonBottom pressed!");
                updateStory(view.getId());
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("StoryIndex",mStoryRecordsIndex);
    }

    public void initializeStory() {
        mStoryRecordList = new ArrayList<>();
        mStoryRecordList.add(new StoryRecord(R.string.T1_Story,R.string.T1_Ans1,R.string.T1_Ans2,2,1));
        mStoryRecordList.add(new StoryRecord(R.string.T2_Story,R.string.T2_Ans1,R.string.T2_Ans2,2,3));
        mStoryRecordList.add(new StoryRecord(R.string.T3_Story,R.string.T3_Ans1,R.string.T3_Ans2,5,4));
        mStoryRecordList.add(new StoryRecord(R.string.T4_End,0,0,0,0));
        mStoryRecordList.add(new StoryRecord(R.string.T5_End,0,0,0,0));
        mStoryRecordList.add(new StoryRecord(R.string.T6_End,0,0,0,0));

        mStoryRecord = mStoryRecordList.get(mStoryRecordsIndex);
        storyTextView.setText(mStoryRecord.getStoryText());
        if (mStoryRecord.getAns1Text() != 0) {
            buttonTop.setText(mStoryRecord.getAns1Text());
        } else {
            buttonTop.setVisibility(View.INVISIBLE);
        }
        if (mStoryRecord.getAns2Text() != 0) {
            buttonBottom.setText(mStoryRecord.getAns2Text());
        } else {
            buttonBottom.setVisibility(View.INVISIBLE);
        }
    }

    public void updateStory(int id) {
        mStoryRecord = mStoryRecordList.get(mStoryRecordsIndex);
        if (id == findViewById(R.id.buttonTop).getId()) {
            mStoryRecordsIndex = mStoryRecord.ans1Action;
        }
        if (id == findViewById(R.id.buttonBottom).getId()) {
            mStoryRecordsIndex = mStoryRecord.ans2Action;
        }
        mStoryRecord = mStoryRecordList.get(mStoryRecordsIndex);
        storyTextView.setText(mStoryRecord.getStoryText());
        if (mStoryRecord.getAns1Text() != 0) {
            buttonTop.setText(mStoryRecord.getAns1Text());
        } else {
            buttonTop.setVisibility(View.INVISIBLE);
            displayAlert();
        }
        if (mStoryRecord.getAns2Text() != 0) {
            buttonBottom.setText(mStoryRecord.getAns2Text());
        } else {
            buttonBottom.setVisibility(View.INVISIBLE);
            displayAlert();
        }

    }

    public void displayAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Story Over");
        alert.setMessage("!! Story Over !!");
        alert.setCancelable(false);
        alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();
    }

}
