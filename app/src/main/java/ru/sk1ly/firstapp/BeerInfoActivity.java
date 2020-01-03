package ru.sk1ly.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class BeerInfoActivity extends Activity {

    private Spinner mBeerGlobalTypeSpinner;
    private Spinner mBeerSubtypeSpinner;
    private TextView mBeerInfoTextOnPaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_info);

        mBeerGlobalTypeSpinner = findViewById(R.id.beer_global_type_spinner);
        mBeerSubtypeSpinner = findViewById(R.id.beer_subtype_spinner);
        mBeerInfoTextOnPaper = findViewById(R.id.beer_info_text_on_paper);

        mBeerGlobalTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String itemText = (String) ((TextView) selectedItemView).getText();
                ArrayAdapter<CharSequence> adapter;
                if (itemText.equals("Эль")) {
                    adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.ale_beer_types, android.R.layout.simple_spinner_item);
                } else if (itemText.equals("Лагер")) {
                    adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.lager_beer_types, android.R.layout.simple_spinner_item);
                } else {
                    adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.mixed_beer_types, android.R.layout.simple_spinner_item);
                }
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mBeerSubtypeSpinner.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // nothing to do
            }

        });
    }

    public void onClickShowBeerInfoButton(View view) {
        String selectedBeerGlobalType = String.valueOf(mBeerGlobalTypeSpinner.getSelectedItem());
        String selectedBeerSubtype = String.valueOf(mBeerSubtypeSpinner.getSelectedItem());
        if (selectedBeerGlobalType.equals("Эль")) {
            setAleBeerInfo(selectedBeerSubtype);
        } else if (selectedBeerGlobalType.equals("Лагер")) {
            setLagerBeerInfo(selectedBeerSubtype);
        } else {
            setMixedBeerInfo(selectedBeerSubtype);
        }
    }

    private void setAleBeerInfo(String selectedBeerSubtype) {
        switch (selectedBeerSubtype) {
            case "Пшеничное пиво":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_witbier);
                break;
            case "Берлинское белое":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_berliner_weisse);
                break;
            case "Блонд эль":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_belgian_blond_ale);
                break;
            case "Светлый эль":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_pale_ale);
                break;
            case "Кёльш":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_kolsch);
                break;
            case "Золотой эль":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_golden_ale);
                break;
            case "Трипель":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_tripel_ale);
                break;
            case "Индийский светлый эль":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_india_pale_ale);
                break;
            case "Старый эль":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_old_ale);
                break;
            case "Янтарный эль":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_amber_ale);
                break;
            case "Квадрупель":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_quadrupel);
                break;
            case "Мягкий эль":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_mild_ale);
                break;
            case "Старое коричневое":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_flanders_brown_ale);
                break;
            case "Коричневый эль":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_brown_ale);
                break;
            case "Портер":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_porter);
                break;
            case "Стаут":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_ale_stout);
                break;
        }
    }

    private void setLagerBeerInfo(String selectedBeerSubtype) {
        switch (selectedBeerSubtype) {
            case "Мюнхенское светлое":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_lager_munchner_helles_lager);
                break;
            case "Пильзнер":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_lager_pilsner);
                break;
            case "Экспорт (дортмундер)":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_lager_export);
                break;
            case "Венский лагер":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_lager_vienna_lager);
                break;
            case "Келлербир":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_lager_kellerbier);
                break;
            case "Бок":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_lager_bockbier);
                break;
            case "Темный лагер":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_lager_dark_lager);
                break;
            case "Черное пиво":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_lager_schwarzbier);
                break;
        }
    }

    private void setMixedBeerInfo(String selectedBeerSubtype) {
        switch (selectedBeerSubtype) {
            case "Сливочный эль":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_mixed_cream_ale);
                break;
            case "Ламбик":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_mixed_lambic);
                break;
            case "Мартовское пиво":
                getStringResourceAndSetHisToBeerInfoTextView(R.string.beer_info_mixed_marzen);
                break;
        }
    }

    private void getStringResourceAndSetHisToBeerInfoTextView(int stringResource) {
        mBeerInfoTextOnPaper.setText(getString(stringResource));
    }
}
