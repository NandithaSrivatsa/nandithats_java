package com.tns.fooddeliverysystem.application;

import java.util.*;

class FoodItem {
    int id;
    String name;
    double price;

    FoodItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

class Restaurant {
    int id;
    String name;
    List<FoodItem> menu = new ArrayList<>();

    Restaurant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    void addFoodItem(FoodItem item) {
        menu.add(item);
    }

    void removeFoodItem(int foodId) {
        menu.removeIf(f -> f.id == foodId);
    }
}

class DeliveryPerson {
    int id;
    String name;
    long contact;

    DeliveryPerson(int id, String name, long contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }
}

class Order {

    int orderId;
    int customerId;
    Map<FoodItem,Integer> items = new HashMap<>();
    String status = "Pending";
    DeliveryPerson deliveryPerson = null;
	public String customerName;

    Order(int orderId,int customerId)
    {
        this.orderId = orderId;
        this.customerId = customerId;
    }
}

public class FoodDeliverySystem {
	
	static Map<FoodItem,Integer> cart = new HashMap<>();
    static double totalCost = 0;

    static List<Restaurant> restaurants = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();
    static List<DeliveryPerson> deliveryPersons = new ArrayList<>();
    static int orderCounter = 1;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println("\n1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            switch(choice){

                case 1:
                    adminMenu(sc);
                    break;

                case 2:
                    customerMenu(sc);
                    break;

                case 3:
                    System.out.println("Exiting System...");
                    return;
            }
        }
    }

    static void adminMenu(Scanner sc){

        while(true){

            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Restaurant");
            System.out.println("2. Add Food Item to Restaurant");
            System.out.println("3. Remove Food Item from Restaurant");
            System.out.println("4. View Restaurants and Menus");
            System.out.println("5. View Orders");
            System.out.println("6. Add Delivery Person");
            System.out.println("7. Assign Delivery Person to Order");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int ch=sc.nextInt();

            switch(ch){

                case 1:
                    System.out.print("Enter Restaurant ID: ");
                    int id=sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Restaurant Name: ");
                    String name=sc.nextLine();

                    restaurants.add(new Restaurant(id,name));
                    System.out.println("Restaurant added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Restaurant ID: ");
                    int rid=sc.nextInt();

                    Restaurant r=null;
                    for(Restaurant res:restaurants)
                        if(res.id==rid) r=res;

                    if(r==null){
                        System.out.println("Restaurant not found");
                        break;
                    }

                    System.out.print("Enter Food Item ID: ");
                    int fid=sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Food Item Name: ");
                    String fname=sc.nextLine();

                    System.out.print("Enter Food Item Price: ");
                    double price=sc.nextDouble();

                    r.addFoodItem(new FoodItem(fid,fname,price));
                    System.out.println("Food item added successfully!");
                    break;

                case 3:
                    System.out.print("Enter Restaurant ID: ");
                    int rr=sc.nextInt();

                    for(Restaurant res:restaurants){
                        if(res.id==rr){

                            System.out.print("Enter Food Item ID to remove: ");
                            int removeId=sc.nextInt();
                            res.removeFoodItem(removeId);

                            System.out.println("Food item removed successfully!");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Restaurants and Menus:");

                    for(Restaurant res:restaurants){

                        System.out.println("Restaurant ID: "+res.id+", Name: "+res.name);

                        for(FoodItem f:res.menu){

                            System.out.println("- Food Item ID: "+f.id+
                                    ", Name: "+f.name+
                                    ", Price: Rs. "+f.price);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Orders:");
                    for(Order o:orders){

                        String dp=(o.deliveryPerson==null)?"Not Assigned":o.deliveryPerson.name;

                        System.out.println("Order{orderId="+o.orderId+
                                ", customer="+o.customerName+
                                ", status='Pending', deliveryPerson="+dp+"}");
                    }
                    break;

                case 6:
                    System.out.print("Enter Delivery Person ID: ");
                    int did=sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Delivery Person Name: ");
                    String dname=sc.nextLine();

                    System.out.print("Enter Contact No.: ");
                    long contact=sc.nextLong();

                    deliveryPersons.add(new DeliveryPerson(did,dname,contact));
                    System.out.println("Delivery person added successfully!");
                    break;

                case 7:
                    System.out.print("Enter Order ID: ");
                    int oid=sc.nextInt();

                    System.out.print("Enter Delivery Person ID: ");
                    int dp=sc.nextInt();

                    Order order=null;
                    for(Order o:orders)
                        if(o.orderId==oid) order=o;

                    DeliveryPerson person=null;
                    for(DeliveryPerson d:deliveryPersons)
                        if(d.id==dp) person=d;

                    if(order!=null && person!=null){
                        order.deliveryPerson=person;
                        System.out.println("Delivery person assigned to order successfully!");
                    }
                    break;

                case 8:
                    System.out.println("Exiting Admin Module");
                    return;
            }
        }
    }

    static void customerMenu(Scanner sc){

        while(true){

            System.out.println("\nCustomer Menu:");
            System.out.println("1. Add Customer");
            System.out.println("2. View Food Items");
            System.out.println("3. Add Food to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Place Order");
            System.out.println("6. View Orders");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int ch=sc.nextInt();

            switch(ch){

                case 1:
                	System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();   // clear buffer

                    System.out.print("Enter Username: ");
                    String username = sc.nextLine();

                    System.out.print("Enter Contact No.: ");
                    long contactNo = sc.nextLong();

                    System.out.println("Customer created successfully!");

                    break;

                case 2:
                    System.out.println("Restaurants and Menus:");
                    for(Restaurant r:restaurants){
                        System.out.println("Restaurant ID: "+r.id+", Name: "+r.name);
                        for(FoodItem f:r.menu){
                            System.out.println("- Food Item ID: "+f.id+
                                    ", Name: "+f.name+
                                    ", Price: Rs. "+f.price);
                        }
                    }
                    break;
                    
                case 3:

                	System.out.print("Enter Customer ID: ");
                	int customerId = sc.nextInt();

                	System.out.print("Enter Restaurant ID: ");
                	int restaurantId = sc.nextInt();

                	System.out.print("Enter Food Item ID: ");
                	int foodItemId = sc.nextInt();

                	System.out.print("Enter Quantity: ");
                	int quantity = sc.nextInt();

                	for(Restaurant r : restaurants)
                	{
                	    if(r.id == restaurantId)
                	    {
                	        for(FoodItem f : r.menu)
                	        {
                	            if(f.id == foodItemId)
                	            {
                	                cart.put(f, quantity);   // storing in cart
                	                totalCost += f.price * quantity;

                	                System.out.println("Food item added to cart!");
                	            }
                	        }
                	    }
                	}

                	break;
                    
                case 4:

                	System.out.println("Cart:");

                	for(FoodItem f : cart.keySet())
                	{
                	    int qty = cart.get(f);
                	    double cost = f.price * qty;

                	    System.out.println("Food Item: " + f.name +
                	            ", Quantity: " + qty +
                	            ", Cost: Rs. " + cost);
                	}

                	System.out.println("Total Cost: Rs. " + totalCost);

                	break;
                	
                case 5:

                	System.out.print("Enter Customer ID: ");
                	int customerId1 = sc.nextInt();

                	Order order = new Order(orderCounter++, customerId1);

                	for(FoodItem f : cart.keySet())
                	{
                	    order.items.put(f, cart.get(f));
                	}

                	orders.add(order);

                	System.out.println("Order placed successfully! Your order ID is: " + order.orderId);

                	cart.clear();
                	totalCost = 0;

                	break;

                case 6:

                	System.out.println("Orders:");

                	for (Order o : orders) {
                	    System.out.println(o);
                	}

                	break;

                case 7:
                    System.out.println("Exiting Customer Module");
                    return;
            }
        }
    }
}