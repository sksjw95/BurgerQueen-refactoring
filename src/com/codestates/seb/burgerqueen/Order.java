package com.codestates.seb.burgerqueen;

import com.codestates.seb.burgerqueen.discount.discountCondition.CozDiscountCondition;
import com.codestates.seb.burgerqueen.discount.discountCondition.KidDiscountCondition;
import com.codestates.seb.burgerqueen.discount.discountPolicy.FixedRateDiscountPolicy;
import com.codestates.seb.burgerqueen.product.ProductRepository;

public class Order {
    private Cart cart;

    public Order(Cart cart){
        this.cart = cart;
    }
    public void makeOrder(){
        // 여기부터
        CozDiscountCondition cozDiscountCondition = new CozDiscountCondition(new FixedRateDiscountPolicy(10));
        KidDiscountCondition kidDiscountCondition = new KidDiscountCondition(new FixedRateDiscountPolicy(500));

        cozDiscountCondition.checkDiscountCondition();
        kidDiscountCondition.checkDiscountCondition();
        // 여기까지 추가

        int totalPrice = cart.calculateTotalPrice();

        // 여기부터
        int finalPrice = totalPrice;
        if (cozDiscountCondition.isSatisfied()){
            finalPrice = cozDiscountCondition.applyDiscount(finalPrice);
        }
        if (kidDiscountCondition.isSatisfied()){
            finalPrice = kidDiscountCondition.applyDiscount(finalPrice);
        }
        // 여기까지 추가
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
