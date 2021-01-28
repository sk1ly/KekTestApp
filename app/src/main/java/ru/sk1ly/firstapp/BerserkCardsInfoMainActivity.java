package ru.sk1ly.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Активность со списком карт
 */
public class BerserkCardsInfoMainActivity extends AppCompatActivity implements BerserkCardsInfoListFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berserk_cards_info_main);
    }

    @Override
    public void itemClicked(long id) {
        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            BerserkCardsInfoDetailFragment details = new BerserkCardsInfoDetailFragment();
            details.setCardId(id);
            ft.replace(R.id.fragment_container, details);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        } else {
            Intent intent = new Intent(this, BerserkCardsInfoDetailActivity.class);
            intent.putExtra(Constants.Keys.BERSERK_CARD_ID, (int) id);
            startActivity(intent);
        }
    }
}