package com.codestates.seb.burgerqueen;

import com.codestates.seb.burgerqueen.product.Product;
import com.codestates.seb.burgerqueen.product.subproduct.Drink;
import com.codestates.seb.burgerqueen.product.subproduct.Hamburger;
import com.codestates.seb.burgerqueen.product.subproduct.Side;

public class Menu {
    private Product[] products;
    public  Menu(Product[] products){
        this.products = products;
    }

    public void printMenu(){
        System.out.println("[🔻]메뉴");
        System.out.println("-".repeat(60));

        printHamburgers();

        printSides();

        printDrinks();

        System.out.println();
        System.out.println("🧺 (0) 장바구니");
        System.out.println("🎁 (+) 주문하기");
        System.out.println("-".repeat(60));
        System.out.println("[📢]메뉴를 선택해주세요 :");
    }

    protected void printDrinks() {
        System.out.println("🍹 음료");
        for (Product product : products){
            if (product instanceof Drink){
                printEachMenu("   (%d) %s %5dKcal %5d원\n", product);
            }
        }
        System.out.println();
    }

    protected void printSides() {
        System.out.println("🍟 사이드");
        for (Product product : products){
            if (product instanceof Side){
                printEachMenu("  (%d) %s %5dKcal %5d원\n", product);
            }
        }
        System.out.println();
    }

    private void printHamburgers() {
        System.out.println("🍔 햄버거");
        for(Product product : products){
            if (product instanceof Hamburger){
                printEachMenu("  (%d) %s %5dKcal %5d원\n", product);
            }
        }
        System.out.println();
    }

    private static void printEachMenu(String format, Product product) {
        System.out.printf(
                format,
                product.getId(), product.getName(), product.getKcal(), product.getPrice());
    }
}
