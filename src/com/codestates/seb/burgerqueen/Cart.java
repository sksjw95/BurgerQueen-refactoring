package com.codestates.seb.burgerqueen;

import com.codestates.seb.burgerqueen.product.Product;
import com.codestates.seb.burgerqueen.product.ProductRepository;
import com.codestates.seb.burgerqueen.product.subproduct.BurgerSet;
import com.codestates.seb.burgerqueen.product.subproduct.Drink;
import com.codestates.seb.burgerqueen.product.subproduct.Hamburger;
import com.codestates.seb.burgerqueen.product.subproduct.Side;

import java.util.Scanner;

public class Cart {
    private ProductRepository productRepository;
    private Menu menu;

    public Cart(ProductRepository productRepository, Menu menu){

        this.productRepository = productRepository;
        this.menu = menu;
    }
    private Product[] items = new Product[0];
    private Scanner scanner = new Scanner(System.in);
    public void printCart(){
        System.out.println("🧺 장바구니");
        System.out.println("-".repeat(60));

        // 여기에 장바구니 상품들을 옵션 정보와 함께 출력
        printCartItemDetails(); // 내가 임의 수정
        System.out.println("-".repeat(60));
        System.out.printf("합계 : %d원\n", calculateTotalPrice());

        System.out.println("이전으로 돌아가려면 엔터를 누르세요.");
        scanner.nextLine();
    }
    //printCartItemDetails()의 의사코드
    private void  printCartItemDetails(){
      for (Product product : items){
        if (product instanceof BurgerSet){
            BurgerSet burgerSet = (BurgerSet) product;
            System.out.printf(" %s %6d원 (%s(케첩 %d개), %s(빨대 %s))\n",
                    product.getName(),
                    product.getPrice(),
                    burgerSet.getSide().getName(),
                    burgerSet.getSide().getKetchup(),
                    burgerSet.getDrink().getName(),
                    burgerSet.getDrink().hasStraw() ? "있음" : "없음");
        }
          else if(product instanceof Hamburger){
            System.out.printf(" %-8s %6d원 (단품)\n",
                    product.getName(),
                    product.getPrice());
        }
          else if (product instanceof Side){
            System.out.printf(" %-8s %6d원 (케첩 %d개)\n",
                    product.getName(),
                    product.getPrice(),
                    ((Side)product).getKetchup()
            );
        }
            else if (product instanceof Drink){
            System.out.printf(" %-8s %6d (빨대 %S)\n",
                    product.getName(),
                    product.getPrice(),
                    ((Drink) product).hasStraw() ? "있음" : "없음" );
        }
      }
    }

    private  int calculateTotalPrice(){
        int totalPrice = 0;
        for (Product product : items){
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
    public void addToCart(int productId){

        Product product = productRepository.findById(productId);
        // 여기부터
        Product newProduct;
        if (product instanceof  Hamburger) newProduct = new Hamburger((Hamburger) product);
        else if (product instanceof Side) newProduct = new Side((Side) product);
        else newProduct = new Drink((Drink) product);
        // 여기까지 추가

        // 여기부터
        chooseOption(newProduct);
        if (newProduct instanceof Hamburger){
            Hamburger hamburger = (Hamburger) newProduct;
            if (hamburger.isBurgerSet()) newProduct = composeSet(hamburger);
        }
         // 여기까지 기존의 product를 newProduct로 변경
        Product[] newItems = new Product[items.length + 1];
        System.arraycopy(items, 0, newItems, 0, items.length);
        newItems[newItems.length -1] = newProduct; //여기 수정
        items = newItems;
        System.out.printf("[📢] %s를(을) 장바구니에 담았습니다.\n", product.getName());
    }
    private void chooseOption(Product product){
        String input;
        if(product instanceof Hamburger){
            System.out.printf("단품으로 주문하시겠어요? (1)_단품(%d원) (2)_세트(%d원)\n",
                    product.getPrice(),((Hamburger) product).getBurgerSetPrice());
                input = scanner.nextLine();
                    if(input.equals("2")){
                        ((Hamburger)product).setIsBurgerSet(true);
                    }

        }
        else if (product instanceof Side){
            System.out.println("케첩은 몇 개가 필요하신가요?");
                input = scanner.nextLine();
            ((Side) product).setKetchup(Integer.parseInt(input));
        }

        else if (product instanceof Drink){
            System.out.println("빨대가 필요하신가요? (1)_예 (2)_아니요");
            input = scanner.nextLine();
                    if (input.equals("2")){
                        ((Drink) product).setHasStraw(false);
                    }
        }
    }
    private BurgerSet composeSet(Hamburger hamburger){
        System.out.println("사이드를 골라주세요");

            menu.printSides(false);
                String sideId = scanner.nextLine();
                Side side = (Side) productRepository.findById(Integer.parseInt(sideId));
                Side newSide = new Side(side); // 여기 추가
                chooseOption(newSide);//여기 변경

        System.out.println("음료를 골라주세요");
        menu.printDrinks(false);

        String drinkId = scanner.nextLine();
        Drink drink = (Drink)productRepository.findById(Integer.parseInt(drinkId));
        Drink newDrink = new Drink(drink);//여기 추가
        chooseOption(newDrink);//여기 변경

        String name = hamburger.getName()+"세트";
        int price = hamburger.getBurgerSetPrice();
        int kcal = hamburger.getKcal()+ side.getKcal()+drink.getKcal();

        return new BurgerSet(name,price,kcal,hamburger,newSide,newDrink);//여기 변경
    }

}
