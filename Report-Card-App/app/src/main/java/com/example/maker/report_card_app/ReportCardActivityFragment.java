package com.example.maker.report_card_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ReportCardActivityFragment extends Fragment {

    private static final String LOG_TAG = ReportCardActivityFragment.class.getSimpleName();

    public ReportCardActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_card, container,false);

        TextView textView = (TextView) rootView.findViewById(R.id.textview);

        ReportCard reportCard = new ReportCard(100, 100, 100, 100, 100, "Saifuddin", 1793);
        reportCard.setChemistryMarks(80);
        reportCard.setMathMarks(90);
        reportCard.setEnglishMarks(83);
        reportCard.setPhysicsMarks(85);
        reportCard.setSocialMarks(95);

        Log.d(LOG_TAG, reportCard.toString());

        textView.setText(reportCard.displayResult());

        return rootView;
    }
}
