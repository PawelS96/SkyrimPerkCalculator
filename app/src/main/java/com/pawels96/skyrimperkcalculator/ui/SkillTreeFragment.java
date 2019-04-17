package com.pawels96.skyrimperkcalculator.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.pawels96.skyrimperkcalculator.R;
import com.pawels96.skyrimperkcalculator.enums.SkillEnum;
import com.pawels96.skyrimperkcalculator.models.Perk;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.pawels96.skyrimperkcalculator.Utils.getPerkDescription;
import static com.pawels96.skyrimperkcalculator.Utils.getPerkName;


public class SkillTreeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private GraphView graphView;

    public static final String ARG_1 = "arg1";

    private MainActivity parentActivity;
    private TextView activePerks, reqSkill;

    public SkillTreeFragment() {}

    private SkillEnum skillEnum;

    public interface OnFragmentInteractionListener {
        void onPerkChanged(SkillEnum skill, Perk perk, int state);
    }

    public static SkillTreeFragment newInstance(SkillEnum param1) {
        SkillTreeFragment fragment = new SkillTreeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public String getPerkCount() {
        return graphView.getSkill().getPerksCount();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skillEnum = (SkillEnum)getArguments().getSerializable(ARG_1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_skilltree, container, false);
        graphView = rootView.findViewById(R.id.graph);

        activePerks = rootView.findViewById(R.id.skillPerks);
        reqSkill = rootView.findViewById(R.id.skillLevel);

        graphView.setListener(listener);
        graphView.setSkill(parentActivity.getBuild().getSkill(skillEnum), parentActivity.getBuild().getPerkSystem());
        updateSkillInfo();

        return rootView;
    }

    public void refresh() {
        graphView.setSkill(parentActivity.getBuild().getSkill(skillEnum), parentActivity.getBuild().getPerkSystem());
        updateSkillInfo();
        graphView.invalidate();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = (MainActivity) context;
        mListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private GraphView.OnNodeClickedListener listener = new GraphView.OnNodeClickedListener() {
        @Override
        public void onNodeClicked(Perk perk) {
            mListener.onPerkChanged(skillEnum, perk, perk.getState());
            updateSkillInfo();
        }

        @Override
        public void onNodePressed(Perk perk) {
            showPerkDescription(perk);
        }
    };

    private void showPerkDescription(Perk perk){

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme);

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.popup_perk_description, null);
        dialogBuilder.setView(customView);

        TextView perkDesc = customView.findViewById(R.id.perk_desc);
        TextView perkSkill = customView.findViewById(R.id.perk_skill);

        String skillText = getString(R.string.required_skill) + ": " + perk.getAllSkillLevels();
        perkSkill.setText(skillText);
        perkDesc.setText(getPerkDescription(getActivity(), perk.getPerk()));

        dialogBuilder.setTitle(getPerkName(getActivity(), perk.getPerk()));
        dialogBuilder.setCancelable(true);

        final AlertDialog dialog = dialogBuilder.create();

        Window window = dialog.getWindow();
        if (window != null)
        window.setGravity(Gravity.BOTTOM);

        dialog.show();
    }

    private void updateSkillInfo() {

        String perksText = getString(R.string.active_perks) + ": " + getPerkCount();
        String skillText = getString(R.string.required_skill) + ": "  + graphView.getSkill().getRequiredSkillLevel();

        activePerks.setText(perksText);
        reqSkill.setText(skillText);
    }

}
