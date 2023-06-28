package com.codestates.seb.burgerqueen;

import com.codestates.seb.burgerqueen.discount.Discount;
import com.codestates.seb.burgerqueen.discount.discountCondition.CozDiscountCondition;
import com.codestates.seb.burgerqueen.discount.discountCondition.DiscountCondition;
import com.codestates.seb.burgerqueen.discount.discountCondition.KidDiscountCondition;
import com.codestates.seb.burgerqueen.discount.discountPolicy.FixedRateDiscountPolicy;
import com.codestates.seb.burgerqueen.product.ProductRepository;

public class Order {
    private Cart cart;
   // private DiscountCondition[] discountConditions;
    private Discount discount;

    public Order(Cart cart, /*/DiscountCondition[] discountConditions*/Discount discount) {
        this.cart = cart;
        this.discount = discount;
        //this.discountConditions = discountConditions;
    }
    public void makeOrder(){
        // 여기부터
        //CozDiscountCondition cozDiscountCondition = new CozDiscountCondition(new FixedRateDiscountPolicy(10));
        //KidDiscountCondition kidDiscountCondition = new KidDiscountCondition(new FixedRateDiscountPolicy(500));

        //cozDiscountCondition.checkDiscountCondition();
        //kidDiscountCondition.checkDiscountCondition();
        // 여기까지 추가

        //int totalPrice = cart.calculateTotalPrice();

        // 여기부터
        /*int finalPrice = totalPrice;
        if (cozDiscountCondition.isSatisfied()){
            finalPrice = cozDiscountCondition.applyDiscount(finalPrice);
        }
        if (kidDiscountCondition.isSatisfied()){
            finalPrice = kidDiscountCondition.applyDiscount(finalPrice);
        }
        // 여기까지 추가 - 리팩토링전 */
        int totalPrice = cart.calculateTotalPrice();
        int finalPrice = discount.discount(totalPrice);
       /* for (DiscountCondition discountCondition : discountConditions){
            discountCondition.checkDiscountCondition();
            if(discountCondition.isSatisfied()){
                finalPrice = discountCondition.applyDiscount(finalPrice);
            }
        }*/

        System.out.println("[📢] 주문이 완료되었습니다.");
        System.out.println("[📢] 주문 내역은 다음과 같습니다.");
        System.out.println("-".repeat(60));


        // 상품 상세 내역 출력
        cart.printCartItemDetails();

        System.out.println("-".repeat(60));
        System.out.printf("금액 합계    :%d원\n", totalPrice);
        System.out.printf("할인 적용 금액  :%d원\n", finalPrice); // 추가
    }
}
