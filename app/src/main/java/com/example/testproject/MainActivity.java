package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Switch;

import com.example.testproject.Adapter.ImageAdapter;
import com.example.testproject.Model.ImageDetail;
import com.example.testproject.databinding.ActivityMainBinding;
import com.suke.widget.SwitchButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<ImageDetail> imageList = new ArrayList<>();
    String[] imageName = {"orange flower", "petal flower", "purple flower","white flower","lotus","red flower","blue flower","pink flower","white flower","multi color","red rose","yellowish flower"};
    int[] image = {R.drawable.a, R.drawable.b, R.drawable.c,R.drawable.d,
            R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.k,R.drawable.l,R.drawable.m};
    String switchStatus = "0";
    ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        by using data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setData();

    }

    private void setData() {
        imageList.clear();
        for (int i = 0; i < imageName.length; i++) {
            ImageDetail imageDetail = new ImageDetail();
            imageDetail.setImage(image[i]);
            imageDetail.setImageName(imageName[i]);
            imageList.add(imageDetail);
        }
        if (switchStatus.equalsIgnoreCase("0")) {
            binding.rvImages.setHasFixedSize(true);
            binding.rvImages.setLayoutManager(new GridLayoutManager(this, 3));
            imageAdapter = new ImageAdapter(this, imageList);
            binding.rvImages.setAdapter(imageAdapter);
        } else {
            binding.rvImages.setHasFixedSize(true);
            binding.rvImages.setLayoutManager(new LinearLayoutManager(this));
            imageAdapter = new ImageAdapter(this, imageList);
            binding.rvImages.setAdapter(imageAdapter);
        }
        binding.switchbutton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    switchStatus = "1";
                    setData();
                } else {
                    switchStatus = "0";
                    setData();
                }


            }
        });
        searchCall();
    }

    private void searchCall() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
                                                    @Override
                                                    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                                                        try {

                                                            if (binding.etSearch.hasFocus() ) {                                                         //    customResourceListAdapter.getFilter().filter(cs.toString());
                                                                imageAdapter.getFilter().filter(cs.toString());
                                                                // Do whatever
                                                            }
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                        // When user changed the Text


                                                    }


                                                    @Override
                                                    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                                                                  int arg3) {
                                                        // TODO Auto-generated method stub

                                                    }

                                                    @Override
                                                    public void afterTextChanged(Editable arg0) {
                                                        // TODO Auto-generated method stub
                                                    }
                                                }

        );
    }


}
