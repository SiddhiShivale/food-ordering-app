package com.aurionpro.menu;

public class MenuSeeder {
    public static void main(String[] args) {
        Menu menu = new Menu();

     // INDIAN
        menu.addItem(new FoodItem("Butter Chicken", 180, new MainCourseFood(), new IndianMenu()));
        menu.addItem(new FoodItem("Gulab Jamun", 50, new DessertFood(), new IndianMenu()));
        menu.addItem(new FoodItem("Paneer Tikka", 160, new StarterFood(), new IndianMenu()));
        menu.addItem(new FoodItem("Biryani", 200, new MainCourseFood(), new IndianMenu()));
        menu.addItem(new FoodItem("Tandoori Chicken", 190, new StarterFood(), new IndianMenu()));
        menu.addItem(new FoodItem("Dal Makhani", 150, new MainCourseFood(), new IndianMenu()));
        menu.addItem(new FoodItem("Rasgulla", 60, new DessertFood(), new IndianMenu()));
        menu.addItem(new FoodItem("Samosa", 25, new StarterFood(), new IndianMenu()));

        // ITALIAN
        menu.addItem(new FoodItem("Margherita Pizza", 250, new MainCourseFood(), new ItalianMenu()));
        menu.addItem(new FoodItem("Spaghetti Carbonara", 300, new MainCourseFood(), new ItalianMenu()));
        menu.addItem(new FoodItem("Bruschetta", 120, new StarterFood(), new ItalianMenu()));
        menu.addItem(new FoodItem("Tiramisu", 180, new DessertFood(), new ItalianMenu()));
        menu.addItem(new FoodItem("Lasagna", 280, new MainCourseFood(), new ItalianMenu()));
        menu.addItem(new FoodItem("Caprese Salad", 140, new StarterFood(), new ItalianMenu()));
        menu.addItem(new FoodItem("Cannoli", 160, new DessertFood(), new ItalianMenu()));
        menu.addItem(new FoodItem("Panna Cotta", 170, new DessertFood(), new ItalianMenu()));

        // CHINESE
        menu.addItem(new FoodItem("Kung Pao Chicken", 220, new MainCourseFood(), new ChineseMenu()));
        menu.addItem(new FoodItem("Spring Rolls", 100, new StarterFood(), new ChineseMenu()));
        menu.addItem(new FoodItem("Fried Rice", 180, new MainCourseFood(), new ChineseMenu()));
        menu.addItem(new FoodItem("Mango Pudding", 150, new DessertFood(), new ChineseMenu()));
        menu.addItem(new FoodItem("Sweet and Sour Pork", 240, new MainCourseFood(), new ChineseMenu()));
        menu.addItem(new FoodItem("Dumplings", 120, new StarterFood(), new ChineseMenu()));
        menu.addItem(new FoodItem("Chow Mein", 200, new MainCourseFood(), new ChineseMenu()));
        menu.addItem(new FoodItem("Sesame Balls", 130, new DessertFood(), new ChineseMenu()));


        // Save to file
        menu.saveToFile("menu");
        System.out.println("Sample menu saved to menu.");
    }
}
