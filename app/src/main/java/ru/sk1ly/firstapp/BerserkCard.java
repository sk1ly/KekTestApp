package ru.sk1ly.firstapp;

/**
 * Инкапсулирует сущность карты
 */
public class BerserkCard {

    /**
     * Тестовый набор карт
     */
    public final static BerserkCard[] TEST_BERSERK_CARDS = {
            new BerserkCard("Анкаб", 3, R.drawable.berserk_card_1),
            new BerserkCard("Агатервол", 5, R.drawable.berserk_card_2),
            new BerserkCard("Акванит", 3, R.drawable.berserk_card_3)
    };

    private String name;
    private int cost;
    private int imageResourceId;

    public BerserkCard(String name, int cost, int imageResourceId) {
        this.name = name;
        this.cost = cost;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

}
