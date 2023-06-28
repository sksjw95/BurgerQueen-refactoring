package com.codestates.seb.burgerqueen;

import com.codestates.seb.burgerqueen.discount.Discount;
import com.codestates.seb.burgerqueen.discount.discountCondition.CozDiscountCondition;
import com.codestates.seb.burgerqueen.discount.discountCondition.DiscountCondition;
import com.codestates.seb.burgerqueen.discount.discountCondition.KidDiscountCondition;
import com.codestates.seb.burgerqueen.discount.discountPolicy.FixedAmountDiscountPolicy;
import com.codestates.seb.burgerqueen.discount.discountPolicy.FixedRateDiscountPolicy;
import com.codestates.seb.burgerqueen.product.Product;
import com.codestates.seb.burgerqueen.product.ProductRepository;

import java.util.Scanner;

public class OrderApp {
    public void start() {
        Scanner scanner = new Scanner(System.in);

        ProductRepository productRepository = new ProductRepository();
        Product[] products = productRepository.getAllProducts();
        Menu menu = new Menu(products);
        Cart cart = new Cart(productRepository, menu);
        Order order = new Order(cart, new Discount(new DiscountCondition[]{
                new CozDiscountCondition(new FixedRateDiscountPolicy(10)),
                new KidDiscountCondition(new FixedAmountDiscountPolicy(500))
        })); //리팩토링
        System.out.println("🍔 BurgerQueen Order Service");

        while (true) {
            menu.printMenu();
            String input = scanner.nextLine();
//
            if (input.equals("+")) {
               order.makeOrder();// 주문 내역 출력
                break;
            } else {
                int menuNumber = Integer.parseInt(input);
                if (menuNumber == 0) {
                    cart.printCart();
                } else if (1 <= menuNumber && menuNumber <= products.length) {
                    cart.addToCart(menuNumber);
                }
            }
        }
    }
}