package credit;

/**
 * @ClassName CreditCard
 * @Description TODO
 * @Author boy
 * @Date 2020/6/5 8:50 PM
 */
public class CreditCard {
    static int moth = 12;
    int card1Date = 4;

    static Card cardTmp = null;
    static int date;
    public static void main(String[] args){

        Card card = new Card();
        card.setCardName("card" + 1);
        card.setCardDate(4);

        cardTmp = card;
        for(int i=0;i<moth;i++){

            System.out.println("卡"+cardTmp.cardName+" 账单日 "+cardTmp.cardDate + " 还款日 " + (cardTmp.cardDate+20)%30);
            Card card1 = new Card();
            card1.cardName = "card" + (i+2);
            date = cardTmp.cardDate+20-1;
            card1.cardDate = date%30;
            cardTmp = card1;
        }
    }
}


class Card{
    String cardName;
    int cardDate;
    public void setCardName(String name){
        cardName = name;
    }
    public void setCardDate(int date){
        cardDate = date;
    }
}
//curl -H "Content-type: application/json" -X POST -d '{"userNo":"190626005251262144","merchantName":"南宁市第二人民医院（广西医科大学第三附属医院、南宁市颅脑床上研究所、南宁市骨科疾病研究所、南宁市儿童医院、南宁市妇产医院）","merchantShortName":"南宁市第二人民医院","servicePhone":"18878739250","business":"010100052","thridInst":"NETPAY","bizSceneType":"ONLINE","payChannel":"WXPAY"}' http://localhost:7001/tmp/sign/artificial