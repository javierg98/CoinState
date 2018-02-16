package com.example.a12jg1.profits;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by 12jg1 on 12/3/2017.
 */

public class AssetAddActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mAssetCreaationReference;
    private FirebaseAuth mAuth;
    private String creator;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asset_creation);

        mDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AssetAddActivity.this, MainActivity.class);
        AssetAddActivity.this.startActivity(intent);
        finish();
    }


}
