package com.login.valeriya.galleryyandex;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
//активити с подробный описанием приложения через фрагменты
public class About extends AppCompatActivity {


    private BottomNavigationView mBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // опр навигацию кнопок внизу экрана
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                // опр Фрагмент менеджер
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.item_1:
                        fragment = FragmentOne.getInstance(getString(R.string.about),R.drawable.galleryicon);
                        break;
                    case R.id.item_2:
                        fragment = FragmentTwo.getInstance(getString(R.string.author),R.drawable.me);
                        break;

                }
                fragmentTransaction.replace(R.id.fragment_container , fragment);
                fragmentTransaction.commit();

                return false;
            }
        });

        // ставим первый фрагмент - фрагментом по умолчанию
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment  fragment = FragmentOne.getInstance(getString(R.string.about),R.drawable.galleryicon);

        fragmentTransaction.replace(R.id.fragment_container , fragment);
        fragmentTransaction.commit();
    }
}
