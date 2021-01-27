package ru.sk1ly.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BerserkCardsInfoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berserk_cards_info_detail);
        BerserkCardsInfoDetailFragment frag = (BerserkCardsInfoDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.berserk_cards_info_detail_fragment);
        int cardId = (int) getIntent().getExtras().get(Constants.Keys.BERSERK_CARD_ID);
        frag.setCardId(cardId);
    }
}