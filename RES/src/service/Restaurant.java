package service;

import model.Customer;
import model.Product;


import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Restaurant {
    private String nameRestaurant;
    private String tel;
    private String Address;
    private int count = 0;
    private List<Product> productList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();
    private Product product;
    private Customer customer;
    private String SelectProduct;

    public Restaurant(){

    }

    public Restaurant(String nameRestaurant, String tel, String Address) {
        this.nameRestaurant = nameRestaurant;
        this.tel = tel;
        this.Address = Address;
    }

    public void inputProduct() {
            Product product = new Product();
            while(true){
            product.input();
            int count = 0;
            if(productList.size() > 0){
                count = productList.size();
                product.setIdProduct(count + 1);
            }else{
                count = 1;
                product.setIdProduct(count);
            }
            for(int i = 0; i < productList.size(); i++){
                if(productList.get(i).equals(product)){
                    System.out.println("Name product is exist!");
                    }
                }
                productList.add(product);
                break;
            }

        writeFile();

    }
    public void writeFile(){
        File infile = new File("F:\\JAVA\\Module2\\Week1\\Res\\src\\ListProduct.csv");
        try{
            FileWriter fw = new FileWriter(infile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i <  productList.size(); i++){
                bw.write(productList.get(i).getId() + "," +
                        productList.get(i).getNameProduct() +
                        "," +productList.get(i).getPriceProduct() + "\n");
            }
            bw.close();
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readFile(){
        String line = "";
        String splitBy = ",";
        if (productList.size() == 0){
            System.out.printf("Menu don't have any product!\n\n");
        }
//                System.out.printf("size %d",productList.size());
        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("src\\ListProduct.csv"));
            int i = 0;
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] product = line.split(splitBy);// use comma as separator
                Product product1 = new Product();
                product1.setIdProduct(Integer.parseInt(product[0]));
                product1.setNameProduct(product[1]);
                product1.setPriceProduct(Integer.parseInt(product[2]));
                productList.add(product1);

            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void showMenu(){
        System.out.println("||=========================Menu==========================||");
        for (int i = 0; i < productList.size(); i++) {
            System.out.printf("|| %2s | %25s | " + String.format("%,15d  VND", productList.get(i).getPriceProduct() )  +
                    " ||\n",productList.get(i).getId(), productList.get(i).getNameProduct() );
        }
        System.out.println("||=======================================================||\n");
            }

    public void Order(){
        Scanner input = new Scanner(System.in);
        int priceProduct = 0;
        int totalPayment = 0;
        int number;
        boolean checkIdtable = true;
        Customer customer = new Customer();
        customer.input();
        for(int i = 0; i < customerList.size(); i++){
            if(customerList.get(i).getIdTable() == customer.getIdTable()){
                System.out.println("Table is occupied");
                return;
                }
            }

        customerList.add(customer);
        boolean check = true;
        boolean checkproduct = false;
        String ch;
        do{
            do {
                System.out.printf("Enter your choose to order product: \n");

                SelectProduct = new Scanner(System.in).nextLine();
                check = false;

                for (int i = 0; i < productList.size(); i++) {

                    if (productList.get(i).getNameProduct().equalsIgnoreCase(SelectProduct)) {
                        System.out.printf("Enter the number:\n");
                        while (true) {
                            try {
                                number = new Scanner(System.in).nextInt();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid Value!");
                            }
                        }
                        Product product = productList.get(i);
                        customer.setOrder(product);
                        priceProduct = product.getPriceProduct() * number;
//                    customer.setTotalPayment(priceProduct);
                        totalPayment += priceProduct;
                        checkproduct = true;

                    }
                }if(!checkproduct){
                    System.out.println("Product isn't available");
                    System.out.println("Please choose another product!");
                    check = true;
                }
            }while(check);

           do{
               System.out.println("Do you want to continue order? (Y/N)");
                ch = new Scanner(System.in).nextLine();
           }while(!(ch.equals("Y")) && !(ch.equals("N")) );

        }while(!ch.equals("N"));

        if(!checkproduct){
            System.out.printf("model.Product isn't available\n");
        }
//        totalPayment += priceProduct;
        customer.setTotalPayment(totalPayment);
        System.out.printf("Order Finish\n");
        System.out.printf("Payment is :\n" );
        System.out.println(String.format("%,d", totalPayment));

    }

    public void PrintOrder(){
        for (int i = 0; i < customerList.size(); i++){
            customerList.get(i).print();
        }
    }

    public void Payment(){
        int IdTable;
        boolean check = true;
        System.out.printf("Enter the numberID of table:\n");
        IdTable = new Scanner(System.in).nextInt();
        int index = -1;
        for (int i = 0; i < customerList.size(); i++){
            if(customerList.get(i).getIdTable() == IdTable){
                customerList.get(i).print() ;
                index = i;
                check = false;
            }
        }
        File infile = new File("F:\\JAVA\\Module2\\Week1\\Res\\src\\Bill.csv");
        try{
            FileWriter fw = new FileWriter(infile);
            BufferedWriter bw = new BufferedWriter(fw);

                    bw.write(nameRestaurant + "\n" + tel + "\n" + Address + "\n" +
                            customerList.get(index).getIdTable() + ", " +
                            customerList.get(index).getNameCustomer() +
                            customerList.get(index).productListOrder.get(index).getNameProduct() + ", " +
                            customerList.get(index).getTotalPayment() + "\n");


            bw.close();
            fw.close();
            System.out.println("Successful!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(check){
            System.out.printf("No table " + IdTable);
        }else {
            customerList.remove(index);
        }
    }

    public static boolean checkNameProduct(String str) {
        String regex = "^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean checkNumber(String num) {
        String regex = "^[0-9\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(num);
        return matcher.matches();
    }

    public static boolean checkYorN(String str) {
        String regex = "([Y|N])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public String editNameProduct(){

        while(true){
             System.out.printf("Enter name edit:\n");
             String name = new Scanner(System.in).nextLine();
         if(checkNameProduct(name)){
             return name;
         }else {
             System.out.println("Product isn't a number in String");
             System.out.println("Please try again!");
            }
         }
    }

    public int editPriceProduct(){
        while(true){
         try{
             while (true) {
                 System.out.printf("Enter price edit:\n");

                 int price = new Scanner(System.in).nextInt();
                 if(price > 0){
                    return price;
                 }else{
                     System.out.println("Price product isn't greater than 0!");
                     continue;
                 }
             }

        }catch(InputMismatchException e){
             System.out.println("Invalid value.");
             System.out.println("Back Home!\n");
             return 0;
         }
        }
    }

    public void EditProduct(){
        String nameProduct;
        boolean check = true;
        System.out.printf("Enter the name product to edit:\n");
        try{
            nameProduct = new Scanner(System.in).nextLine();
        }catch (InputMismatchException e){
            System.out.println("Invalid value.");
            System.out.println("Back Home!\n");
            return;
        }
        for (int i = 0; i < productList.size(); i++){
            if(productList.get(i).getNameProduct().equalsIgnoreCase(nameProduct)){
                check = false;
                productList.get(i).setNameProduct(editNameProduct());
                productList.get(i).setPriceProduct(editPriceProduct());
                System.out.println("Edit product successful!");
//                productList.add(i,product);
                writeFile();
                break;
            }
        }
        if(check){
            System.out.printf("No product name is:\n ",nameProduct);
        }

    }

    public void DeleteProduct(){
        String DeleteProduct;
        int index = -1;
        boolean check = false;
        while(true) {
            try {
                System.out.println("Enter the name product to delete");
                DeleteProduct = new Scanner(System.in).nextLine();
                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid value.");
                System.out.println("Back Home!\n");
                continue;
            }
        }
        for (int i = 0; i < productList.size(); i++){
            if(productList.get(i).getNameProduct().equalsIgnoreCase(DeleteProduct)){
                index = i;
                System.out.println(productList.get(i).toString());
                productList.remove(index);
                System.out.printf("Delete successful\n");
                for(int j = i ; j < productList.size(); j++){
                    productList.get(j).setIdProduct(j+1);
                }
                check = true;
            }

            writeFile();
        }
        if(index == -1){
            System.out.printf("Product is inavaible \n");
        }
    }

    public void searchProduct(){
        System.out.println("Enter the product want to search in menu");
        String nameProduct = new Scanner(System.in).nextLine();
        for (int i = 0; i < productList.size(); i++){
            if (productList.get(i).getNameProduct().equalsIgnoreCase(nameProduct)){
                System.out.println(productList.get(i).toString()+"\n");
            }
        }
    }

    public void ManageProduct() {
        System.out.println("||=============Manage Product=============||");
        System.out.println("|| 0. Back Home                           ||");
        System.out.println("|| 1. Add Product                         ||");
        System.out.println("|| 2. Edit Product                        ||");
        System.out.println("|| 3. Delete Product                      ||");
        System.out.println("|| 4. Show Menu!                          ||");
        System.out.println("|| 5. Search Menu!                        ||");
        System.out.println("||========================================||");
        System.out.println("Your select");

    }

    public void ManageOrder() {
        System.out.println("||===============Manage Order============||");
        System.out.println("|| 0. Back Home                          ||");
        System.out.println("|| 1. Show menu product and order        ||");
        System.out.println("|| 2. Show table Order                   ||");
        System.out.println("|| 3. Payment                            ||");
        System.out.println("||=======================================||");
        System.out.println("Your Select");
    }

    public void Menu() {
        System.out.println("||========================================||");
        System.out.printf("||      Welcome to %s Restaurant     ||\n",this.nameRestaurant);
        System.out.println("||==================Menu==================||");
        System.out.printf("|| 1. Manage Product                      ||\n");
        System.out.printf("|| 2. Manage Order                        ||\n");
        System.out.printf("|| 3. Exit                                ||\n");
        System.out.println("||========================================||");
        System.out.println("Your Select:");
    }

}
