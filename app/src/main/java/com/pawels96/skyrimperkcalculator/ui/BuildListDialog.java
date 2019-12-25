package com.pawels96.skyrimperkcalculator.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pawels96.skyrimperkcalculator.Database;
import com.pawels96.skyrimperkcalculator.R;
import com.pawels96.skyrimperkcalculator.enums.PerkSystem;
import com.pawels96.skyrimperkcalculator.models.Build;

import java.util.List;

public class BuildListDialog extends DialogFragment {

    private PerkSystem system;
    private BuildAdapter adapter;
    private String currentBuildName;
    private BuildListCallback callback;
    private Database database;
    private InputMethodManager imm;

    public interface BuildListCallback {
        void onSelected(Build build);
    }

    public static BuildListDialog create(PerkSystem system, String currentBuildName) {

        BuildListDialog dialog = new BuildListDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("system", system);
        bundle.putString("name", currentBuildName);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        database = Database.get();

        if (context instanceof BuildListCallback)
            callback = (BuildListCallback) context;

        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        system = (PerkSystem) getArguments().getSerializable("system");
        currentBuildName = getArguments().getString("name");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        CustomDialogBuilder dialogBuilder = new CustomDialogBuilder(getContext());
        View root = View.inflate(getContext(), R.layout.dialog_list_builds, null);

        final RecyclerView recycler = root.findViewById(R.id.recycler);

        List<Build> list = database.getAllBuilds(system);

        adapter = new BuildAdapter(list, getContext());
        adapter.setCallback(new BuildAdapter.BuildAdapterCallback() {

            @Override
            public void onRename(Build build) {
                showSavePopup(build, true);
            }

            @Override
            public void onDelete(Build build, boolean canDelete) {
                if (canDelete)
                    showDelete(build);
                else showMessage(R.string.msg_cant_delete);
            }

            @Override
            public void showDescription(Build build) {
                showDescriptionPopup(build);
            }

            @Override
            public void onClick(Build build) {
                callback.onSelected(build);
                dismiss();
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
        adapter.setCurrentBuildName(currentBuildName);

        dialogBuilder.setView(root);
        dialogBuilder.setPositiveButton(R.string.new_build, null);

        return dialogBuilder.create();
    }

    @Override
    public void onStart() {
        super.onStart();

        AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button positiveButton = d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(v -> showSavePopup(new Build(system), false));
        }
    }

    private void showDelete(final Build buildToDelete) {

        CustomDialogBuilder dialogBuilder = new CustomDialogBuilder(getContext());

        dialogBuilder.setTitle(getString(R.string.delete_build));
        dialogBuilder.setCancelable(true);

        dialogBuilder.setPositiveButton(getString(R.string.delete),
                (dialog, id) -> {

                    boolean current = buildToDelete.getName().equals(currentBuildName);// == build;
                    boolean deleted = database.deleteBuild(buildToDelete);

                    if (deleted) {
                        adapter.delete(buildToDelete);
                        if (current) {
                            Build selected = adapter.getItem(0);
                            adapter.setCurrentBuildName(selected.getName());
                            callback.onSelected(selected);
                        }
                        adapter.notifyDataSetChanged();
                        showMessage(R.string.msg_build_deleted);
                    } else showMessage(R.string.msg_error);
                });

        dialogBuilder.setNegativeButton(R.string.cancel,(dialog, id) -> dialog.dismiss());

        dialogBuilder.create().show();
    }

    private void showMessage(int res) {
        if (getActivity() != null)
            Toast.makeText(getActivity(), res, Toast.LENGTH_SHORT).show();
    }

    private void showDescriptionPopup(final Build build) {

        CustomDialogBuilder builder = new CustomDialogBuilder(getContext());
        final View root = View.inflate(getContext(), R.layout.popup_build_description, null);

        TextView tv = root.findViewById(R.id.build_desc);

        if (build.getDescription() != null)
            tv.setText(build.getDescription());

        builder.setTitle(build.getName());
        builder.setCancelable(true);
        builder.setView(root);

        builder.setPositiveButton(getString(R.string.edit),
                (dialog, id) -> {

                    CustomDialogBuilder dialogBuilder2 = new CustomDialogBuilder(getContext());
                    final View root2 = View.inflate(getContext(), R.layout.popup_edittext, null);
                    final EditText et = root2.findViewById(R.id.build_desc);

                    dialogBuilder2.setView(root2);
                    et.setText(build.getDescription());
                    et.setSelection(et.getText().length());

                    dialogBuilder2.setPositiveButton(getString(R.string.save), (dialog12, which) -> {
                        build.setDescription(et.getText().toString());
                        database.updateBuild(build);
                        adapter.notifyDataSetChanged();
                        hideKeyboard(root2);
                        showMessage(R.string.msg_saved);
                        // dialog.dismiss();
                    });

                    dialogBuilder2.setNegativeButton(getString(R.string.cancel), (dialog13, which) -> {
                        hideKeyboard(root2);
                        dialog13.dismiss();
                    });

                    AlertDialog dialog1 = dialogBuilder2.create();

                    Window window = dialog1.getWindow();
                    if (window != null)
                        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

                    dialog1.show();
                });

        builder.create().show();
    }

    private void showSavePopup(final Build buildToSave, final boolean rename) {

        CustomDialogBuilder dialogBuilder = new CustomDialogBuilder(getContext());

        final View root = View.inflate(getContext(), R.layout.popup_save, null);

        final EditText et = root.findViewById(R.id.build_name);
        final TextView tv = root.findViewById(R.id.textView);
        final CheckBox copyCurrent = root.findViewById(R.id.checkbox);

        if (rename) {
            et.setText(buildToSave.getName());
            et.setSelection(et.getText().length());
            tv.setVisibility(View.GONE);
            copyCurrent.setVisibility(View.GONE);
        }
        dialogBuilder.setView(root);
        dialogBuilder.setCancelable(true);

        dialogBuilder.setTitle(getResources().getString(R.string.msg_name_your_build));
        dialogBuilder.setPositiveButton(getString(R.string.save), null);
        dialogBuilder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> hideKeyboard(root));

        final AlertDialog dialog = dialogBuilder.create();

        dialog.setOnShowListener(di -> dialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener(v -> {

            String name = et.getText().toString().trim();

            if (name.equals("")) {
                showMessage(R.string.msg_name_empty);
                return;
            }

            if (!database.isNameAvailable(name, system)) {
                showMessage(R.string.msg_name_in_use);
                return;
            }

            if (rename) {
                String oldName = buildToSave.getName();
                database.renameBuild(buildToSave, name);
                buildToSave.setName(name);
                if (oldName.equals(adapter.getCurrentBuildName()))
                    adapter.setCurrentBuildName(name);
                showMessage(R.string.msg_name_changed);
                adapter.notifyDataSetChanged();
                di.dismiss();

            } else {

                boolean copy = copyCurrent.isChecked();

                Build currentBuild = database.getByName(currentBuildName, system);
                Build newBuild = copy ? Build.clone(currentBuild) : buildToSave;
                newBuild.setName(name);
                boolean success = database.saveBuild(newBuild);

                if (success) {
                    showMessage(R.string.msg_build_saved);
                    callback.onSelected(newBuild);
                    di.dismiss();
                    dismiss();

                } else showMessage(R.string.msg_error);
            }
            hideKeyboard(root);

        }));


        if (dialog.getWindow() != null)
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        dialog.show();
    }

    private void hideKeyboard(View v) {
        imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
