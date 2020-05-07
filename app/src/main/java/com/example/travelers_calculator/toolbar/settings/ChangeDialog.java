package com.example.travelers_calculator.toolbar.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.jakewharton.processphoenix.ProcessPhoenix;

public class ChangeDialog extends AppCompatDialogFragment
{
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Restart App?")
                .setMessage("Color change requires app restart.")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //restart app
                       ProcessPhoenix.triggerRebirth(getContext());
                       Toast.makeText(getActivity().getApplicationContext(), "App Restarted", Toast.LENGTH_SHORT).show();
                        //getActivity().recreate();

                    }
                });
        return builder.create();
    }

}
