package com.example.libra.findsomeone;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.libra.findsomeone.AndroidVisionQRReader.QRActivity;

import static com.example.libra.findsomeone.MainActivity.QR_REQUEST;


public class QRScannerFragment extends Fragment {


    private View mView;

    public QRScannerFragment() {
    }

    public static QRScannerFragment newInstance() {
        QRScannerFragment fragment = new QRScannerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_qrscanner, container, false);

        Intent qrScanIntent = new Intent(getActivity(), QRActivity.class);
        startActivityForResult(qrScanIntent, QR_REQUEST);

        return mView;
    }

}
