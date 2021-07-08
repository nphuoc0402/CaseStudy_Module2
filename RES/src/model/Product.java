package model;

import java.util.InputMismatchException;
import java.util.Scanner;

import static service.Restaurant.checkNameProduct;

public class Product {
    private int idProduct;
    private String nameProduct;
    private int priceProduct;

    public Product(){
        idProduct++;
    }

    public Product(int idProduct,String nameProduct, int priceProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        if (priceProduct > 0){
            this.priceProduct = priceProduct;

        }else {
            throw new IllegalArgumentException("Invalid value!");
        }
    }

    public void input(){

        while(true){
            System.out.printf("Enter the name Product:");
            this.nameProduct = new Scanner(System.in).nextLine();
                if(checkNameProduct(nameProduct)){
                   System.out.println("success");
                      break;
                 }else{
                     System.out.println("Please try again!");
            }
        }
        while(true) {
            try{
            System.out.printf("Enter the price:");
            this.priceProduct = new Scanner(System.in).nextInt();
            if(priceProduct <= 0){
                System.out.println("Price product isn't greathan 0 ");
                continue;
            }else{
                break;
            }
            }catch (InputMismatchException e){
                System.out.println("Please try again!");
            }
        }

    }

    public void Payment(){
        System.out.printf("Price: %d",priceProduct);
    }

    public String detailProduct(){
        return nameProduct + " "+ priceProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }


    public int getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        if(priceProduct <= 0){
            System.out.println("Invalid Value!");
        }else {
            this.priceProduct = priceProduct;
        }
    }

    public int getId(){
        return idProduct;
    }

    public void setIdProduct(int idProduct){
        this.idProduct = idProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", priceProduct=" + priceProduct +
                '}';
    }

    //    public void setIdProduct(int idProduct){
//        this.idProduct = idProduct;
//    }
}
