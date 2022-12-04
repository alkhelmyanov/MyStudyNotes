package com.gb.alkhelm.mystudynotes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyAlertDialogFragment extends DialogFragment {

    private OnDialogListener onDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        onDialogListener = ((MainActivity) getActivity());

        return new AlertDialog.Builder(requireContext())
                .setTitle("Закрытие приложения")
                .setMessage("Вы уверены что хотите закрыть приложение?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().finish(); // вызываем активити, и закрываем ее с помощью finish()
                        //Toast.makeText(requireActivity(), "Спасибо за использование приложения \"Заметки\"", Toast.LENGTH_LONG).show();
                        onDialogListener.onDialogResult("Спасибо за использование приложения \"Заметки\"");
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        requireActivity().getSupportFragmentManager().popBackStack(); // то же самое что и нажимаем кнопку назад
                    }
                })
                .show();
    }
}
