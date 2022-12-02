package com.gb.alkhelm.mystudynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                getSupportFragmentManager().beginTransaction().replace(R.id.listNote, new AboutFragment()).addToBackStack("").commit();
                return true;
            }
            case (R.id.action_close): {
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}