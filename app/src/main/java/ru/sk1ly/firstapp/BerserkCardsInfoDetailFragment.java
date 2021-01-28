package ru.sk1ly.firstapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Фрагмент, с подробным описанием карты.
 * Будет находиться в {@link BerserkCardsInfoDetailActivity}, если приложение запущено на устройстве
 * с небольшим размером экрана. В случае, если приложение было запущена на устройстве с большим
 * размером экрана (напр. планшет), то данный фрагмент будет находиться в {@link BerserkCardsInfoMainActivity}.
 */
public class BerserkCardsInfoDetailFragment extends Fragment {

    private long cardId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            cardId = savedInstanceState.getLong("cardId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_berserk_cards_info_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            TextView title = (TextView) view.findViewById(R.id.berserk_cards_info_detail_text_title);
            BerserkCard berserkCard = BerserkCard.TEST_BERSERK_CARDS[(int) cardId];
            title.setText(berserkCard.getName());
            TextView cost = (TextView) view.findViewById(R.id.berserk_cards_info_detail_text_cost);
            cost.setText(getString(R.string.berserk_card_cost_text, berserkCard.getCost()));
            ImageView image = (ImageView) view.findViewById(R.id.berserk_cards_info_detail_image);
            image.setImageResource(berserkCard.getImageResourceId());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong("cardId", cardId);
    }

    public void setCardId(long id) {
        this.cardId = id;
    }
}