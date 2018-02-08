package com.example.a12jg1.profits;

/**
 * Created by 12jg1 on 12/1/2017.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import static android.R.attr.author;

public class DetailActivity extends AppCompatActivity {
    private TextView mCoinTextView;
    private TextView mPriceTextView;
    private TextView mChangeView;
    private ImageView mImageView;
    private ImageView mGraphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mCoinTextView = (TextView) findViewById(R.id.coinTextView);
        mPriceTextView = (TextView) findViewById(R.id.priceTextView);
    }
}
