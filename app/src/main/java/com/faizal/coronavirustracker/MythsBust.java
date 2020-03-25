/*
 * Copyright 2020 FAIZAL.DEV. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.faizal.coronavirustracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.faizal.coronavirustracker.Adapter.SliderAdapter;
import com.faizal.coronavirustracker.Model.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class MythsBust extends AppCompatActivity {

    private ViewPager2 viewPager2;
    //private Handler sliderHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myths_bust);

        viewPager2 = findViewById(R.id.viewPagerSlider);
        final List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.a));
        sliderItems.add(new SliderItem(R.drawable.b));
        sliderItems.add(new SliderItem(R.drawable.c));
        sliderItems.add(new SliderItem(R.drawable.d));
        sliderItems.add(new SliderItem(R.drawable.e));
        sliderItems.add(new SliderItem(R.drawable.f));
        sliderItems.add(new SliderItem(R.drawable.g));
        sliderItems.add(new SliderItem(R.drawable.h));
        sliderItems.add(new SliderItem(R.drawable.i));
        sliderItems.add(new SliderItem(R.drawable.j));
        sliderItems.add(new SliderItem(R.drawable.k));
        sliderItems.add(new SliderItem(R.drawable.l));
        sliderItems.add(new SliderItem(R.drawable.m));
        sliderItems.add(new SliderItem(R.drawable.n));
        sliderItems.add(new SliderItem(R.drawable.o));
        sliderItems.add(new SliderItem(R.drawable.p));
        sliderItems.add(new SliderItem(R.drawable.q));
        sliderItems.add(new SliderItem(R.drawable.r));
        sliderItems.add(new SliderItem(R.drawable.s));
        sliderItems.add(new SliderItem(R.drawable.t));
        sliderItems.add(new SliderItem(R.drawable.u));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r =1 - Math.abs(position);
                page.setScaleY(0.85f + r + 0.15f);
                page.setScaleX(0.25f + r + 0.25f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
//        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                sliderHandler.removeCallbacks(slideRunnable);
//                sliderHandler.postDelayed(slideRunnable, 4000);
//            }
//        });
    }
//    private  Runnable slideRunnable = new Runnable() {
//        @Override
//        public void run() {
//            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
//        }
//    };
}
