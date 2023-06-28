package com.codestates.seb.burgerqueen;

import com.codestates.seb.burgerqueen.discount.Discount;
import com.codestates.seb.burgerqueen.discount.discountCondition.CozDiscountCondition;
import com.codestates.seb.burgerqueen.discount.discountCondition.DiscountCondition;
import com.codestates.seb.burgerqueen.discount.discountCondition.KidDiscountCondition;
import com.codestates.seb.burgerqueen.discount.discountPolicy.FixedAmountDiscountPolicy;
import com.codestates.seb.burgerqueen.discount.discountPolicy.FixedRateDiscountPolicy;
import com.codestates.seb.burgerqueen.product.ProductRepository;

public class AppConfigurer {

    private Cart cart = new Cart(productRepository(), menu()); //추가

    public ProductRepository productRepository(){
        return new ProductRepository();
    }
    public Menu menu(){
        return new Menu(productRepository().getAllProducts());
    }
    // 여기부터
    public Cart cart(){
        return cart ;
    }
    // 여기까지 수정
    public Discount discount(){
        return new Discount(new DiscountCondition[]{
                new CozDiscountCondition(new FixedRateDiscountPolicy(10)),
                new KidDiscountCondition(new FixedAmountDiscountPolicy(500))
        });
    }
    public Order order(){
        return new Order(cart(),discount());
    }
}
