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

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;

import com.shashank.sony.fancyaboutpagelib.FancyAboutPage;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);



        FancyAboutPage fancyAboutPage=findViewById(R.id.fancyaboutpage);
        //fancyAboutPage.setCoverTintColor(Color.BLUE);  //Optional
        fancyAboutPage.setCover(R.drawable.allah); //Pass your cover image
        fancyAboutPage.setName("Faizal Khan(فیصل خان)");
        fancyAboutPage.setDescription("Passionate Android Developer. Currently pursuing B.Voc(Software Development).");
        fancyAboutPage.setAppIcon(R.mipmap.ic_launcher); //Pass your app icon image
        fancyAboutPage.setAppName("About This App");
        fancyAboutPage.setVersionNameAsAppSubTitle("1.0.1");
        fancyAboutPage.setAppDescription("This app built for providing all live updates of CORONA VIRUS.\n\n" +
                "Major motive of this app to spread only true facts and any related news!");
        fancyAboutPage.addEmailLink("faizalkhan8130@gmail.com");     //Add your email id
        fancyAboutPage.addFacebookLink("https://www.facebook.com/FaizalAndro");  //Add your facebook address url
        fancyAboutPage.addGitHubLink("https://github.com/faizalAndro");
    }
}
