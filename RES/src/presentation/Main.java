package presentation;

import model.Product;
import service.Restaurant;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        Restaurant restaurant = new Restaurant("Huracan","(+1)234-557-786","Silicon Valley");
        restaurant.readFile();
        int select ;
        int choose;
        boolean check = true;
        Scanner input = new Scanner(System.in);
        do {
            restaurant.Menu();
            while(true){
                try {
                    select = new Scanner(System.in).nextInt();
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Invalid Value:");
                    System.out.println("Please Try Again!");
                }
            }
            switch (select) {
                case 1:
                    do{
                    restaurant.ManageProduct();
                    while(true){
                        try {
                            choose = new Scanner(System.in).nextInt();
                            break;
                        }catch (InputMismatchException e){
                            System.out.println("Invalid Value:");
                            System.out.println("Please Try Again!");
                        }
                    }

                    switch (choose) {
                        case 1:
                            restaurant.inputProduct();
                            break;
                        case 2:
                            restaurant.showMenu();
                            restaurant.EditProduct();
                            break;
                        case 3:
                            restaurant.showMenu();
                            restaurant.DeleteProduct();
                            break;
                        case 4:
                            restaurant.showMenu();
                            break;
                        case 5:
                            restaurant.searchProduct();
                            break;
                        case 0:
                            System.out.printf("Back Home\n");
                            break;
                        default:
                            System.out.printf("Invalid Value\n");
                            break;
                    }
                    }while(choose != 0);
                break;

            case 2:
                do{
                    restaurant.ManageOrder();
                    while(true){
                        try {
                            choose = new Scanner(System.in).nextInt();
                            break;
                        }catch (InputMismatchException e){
                            System.out.println("Invalid Value:");
                            System.out.println("Please Try Again!");
                        }
                    }

                        switch (choose) {
                        case 1:
                            restaurant.showMenu();
                            restaurant.Order();
                            break;
                        case 2:
                            restaurant.PrintOrder();
                            break;
                        case 3:
                            restaurant.Payment();
                            break;
                        case 0:
                            System.out.printf("Back Home\n");
                            break;
                        default:
                            System.out.printf("Invalid Value\n");
                            break;
                        }
                    }while(choose != 0);

                    break;
                case 3:
                    System.out.printf("Thank you for use our service");
                    System.exit(0);
                    break;
                default:
                    System.out.printf("Invalid Value\n");
                    break;
            }
        } while (select != 3);
    }


}