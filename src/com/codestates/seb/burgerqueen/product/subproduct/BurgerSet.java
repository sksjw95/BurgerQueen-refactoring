package com.codestates.seb.burgerqueen.product.subproduct;

import com.codestates.seb.burgerqueen.product.Product;

public class BurgerSet extends Product {
   private Hamburger hamburger;
   private Drink drink;
   private Side side;

   public BurgerSet(int id, String name, int price, int kcal, Hamburger hamburger, Side side, Drink drink){
       super(id, name, price, kcal);
       this.hamburger = hamburger;
       this.drink = drink;
       this.side = side;
   }
   public Hamburger getHamburger(){
       return hamburger;
   }
   public Side getSide(){
       return side;
   }
   public Drink getDrink(){
       return drink;
   }
}
