package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static service.Restaurant.checkNameProduct;

public class Customer {
    private int IdTable;
    private String nameCustomer;
    private int totalPayment;
    public List<Product> productListOrder = new ArrayList<>();


    public Customer(){

    }

    public void setNameCustomer(String nameCustomer){
        this.nameCustomer = nameCustomer;
    }

    public String getNameCustomer(){
        return nameCustomer;
    }

    public int getIdTable(){
        return IdTable;
    }

    public void setTotalPayment(int totalPayment){
        this.totalPayment = totalPayment;
    }

    public int getTotalPayment(){
        return totalPayment;
    }

    public void input(){
            while(true){
                try{
                    System.out.printf("Number table:\n");
                    IdTable = new Scanner(System.in).nextInt();
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Invalid value!");
                }
            }

        while(true){
            System.out.printf("Enter the name Customer:");
            this.nameCustomer = new Scanner(System.in).nextLine();
            if(checkNameProduct(nameCustomer)){
                System.out.println("success");
                break;
            }else{
                System.out.println("Please try again!");
            }
        }


    }

    public void print(){
        System.out.println("=============================");
        System.out.println("number table: " + this.IdTable + "\n" + "Name:  " + nameCustomer);
        for (Product p : this.productListOrder){
            System.out.printf("Select product: " + p.getNameProduct() + "\n");
        }
        System.out.println("Total: " + String.format("%,d",getTotalPayment()) + " VND");
        System.out.println();
    }

    public void setOrder(Product product){
        productListOrder.add(product);
    }


}
