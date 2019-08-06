package com.example.tournamentbracketcreator.utility;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.tournamentbracketcreator.R;

public class AddNewPlayerDialog extends AppCompatDialogFragment {
    public static final String TAG = "AppDialog";

    public static final String DIALOG_ID = "id";
    public static final String DIALOG_MESSAGE = "message";
    public static final String DIALOG_POSITIVE_RID = "positive_rid";
    public static final String DIALOG_NEGATIVE_RID = "negative_rid";

    interface DialogEvents {
        void onPositiveDialogResult(int dialogId, Bundle args);
        void onNegativeDialogResult(int dialogId, Bundle args);
        void onDialogCancelled(int dialogId);
    }
    private DialogEvents mDialogEvents;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof DialogEvents)){
            throw new ClassCastException(context.toString()
                    + "need AppDialog.DialogEvents interface");
        }
        mDialogEvents = (DialogEvents) context;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mDialogEvents = null;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: starts");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //may or may not need?
        final Bundle arguments = getArguments();
        final int dialogId;
        String messageString;
        int positiveStringId;
        int negativeStringId;

        if (arguments != null){
            dialogId = arguments.getInt(DIALOG_ID);
            messageString = arguments.getString(DIALOG_MESSAGE);
            if (dialogId == 0 || messageString == null){
                throw new IllegalArgumentException("DIALOG ID or DIALOG MESSAGE not present");
            }

            positiveStringId = arguments.getInt(DIALOG_POSITIVE_RID);
            if (positiveStringId == 0){
                positiveStringId = R.string.Ok;
            }

            negativeStringId = arguments.getInt(DIALOG_NEGATIVE_RID);
            if (negativeStringId == 0){
                negativeStringId = R.string.Cancel;
            }
        } else {
            throw new IllegalArgumentException("Must pass DIALOG ID AND DIALOG MESSAGE" +
                    "in the bundle");
        }
        builder.setMessage(messageString)
                .setPositiveButton(positiveStringId, (dialog, which) -> {
                    if (mDialogEvents != null){
                        mDialogEvents.onPositiveDialogResult(dialogId, arguments);
                    }
                })
    .setNegativeButton(negativeStringId, (dialog, which) -> {
        if (mDialogEvents != null){
            mDialogEvents.onNegativeDialogResult(dialogId, arguments);
        }
    });

        return builder.create();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if (mDialogEvents != null){
            int dialogId = getArguments().getInt(DIALOG_ID);
            mDialogEvents.onDialogCancelled(dialogId);
        }
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
