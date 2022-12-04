package com.gb.alkhelm.mystudynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);

        if (savedInstanceState == null) { // если активити новая...
            ListNoteFragment listNoteFragment = ListNoteFragment.newInstance();   // создаем новый фрагмент
            getSupportFragmentManager().beginTransaction().replace(R.id.listNote, listNoteFragment).commit(); // вызываем supportManager, начинаем транзакцию (замени наш ListNote фрагмент, на listNoteFragment объект которого мы создали выше), закоммитим.


            // добавляем вариант с ландшафтной ориентацией
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) { // если (получить ресурсы.получить конфигурацию.ориентация равна Landscape) то
                //String [] listNote = getResources().getStringArray(R.array.listOfNoteArray); // TODO добавить элемент из массива в дефолт
                Note defaultNote = new Note(0);
                NoteFragment noteFragment = NoteFragment.newInstance(defaultNote);   // создаем еще один фрагмент
                getSupportFragmentManager().beginTransaction().replace(R.id.notes, noteFragment).commit();
            }
        }

        // Добавление кастомного тулбара
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        // Добавление "Бургера" (toggle)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.burger_open, R.string.burger_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.action_drawer_settings): {
                        Toast.makeText(MainActivity.this, "Настройки", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    case (R.id.action_drawer_about): {
                        getSupportFragmentManager().beginTransaction().replace(R.id.listNote, new AboutFragment()).addToBackStack("").commit(); // открытие нового фрагмента "fragment_about"
                        drawerLayout.close(); // Закрытие бокового меню после нажатия на "About"
                        return true;
                    }
                }
                return false;
            }
        });
    }

    // Добавление меню в тулбар

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu); //получить инфлейтор меню. надуть этим инфлейтером меню (куда, что)
        return super.onCreateOptionsMenu(menu);
    }

    // Обработчик кликов (выбирает что делать с определенныи item меню)

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_about): {
                getSupportFragmentManager().beginTransaction().replace(R.id.listNote, new AboutFragment()).addToBackStack("").commit(); // открытие нового фрагмента "fragment_about"
                return true;
            }
            case (R.id.action_close): {
                new MyDialogFragment().show(getSupportFragmentManager(),""); // вызываем фрагмент MyDialogFragment
            }
        }

        return super.onOptionsItemSelected(item);
    }
}