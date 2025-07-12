package com.aurionpro.menu;

public class MenuSeeder {
    public static void main(String[] args) {
        Menu menu = new Menu();

        // INDIAN
        menu.addItem(new FoodItem(1, "INDIAN", "Samosa", 20, FoodType.STARTER));
        menu.addItem(new FoodItem(2, "INDIAN", "Paneer Tikka", 60, FoodType.STARTER));
        menu.addItem(new FoodItem(3, "INDIAN", "Chicken Pakora", 70, FoodType.STARTER));
        menu.addItem(new FoodItem(4, "INDIAN", "Aloo Chaat", 40, FoodType.STARTER));
        menu.addItem(new FoodItem(5, "INDIAN", "Veg Spring Roll", 50, FoodType.STARTER));

        menu.addItem(new FoodItem(6, "INDIAN", "Butter Chicken", 180, FoodType.MAIN_COURSE));
        menu.addItem(new FoodItem(7, "INDIAN", "Paneer Butter Masala", 160, FoodType.MAIN_COURSE));
        menu.addItem(new FoodItem(8, "INDIAN", "Rogan Josh", 200, FoodType.MAIN_COURSE));
        menu.addItem(new FoodItem(9, "INDIAN", "Chole Bhature", 120, FoodType.MAIN_COURSE));
        menu.addItem(new FoodItem(10, "INDIAN", "Biryani", 150, FoodType.MAIN_COURSE));

        menu.addItem(new FoodItem(11, "INDIAN", "Gulab Jamun", 50, FoodType.DESSERT));
        menu.addItem(new FoodItem(12, "INDIAN", "Rasgulla", 45, FoodType.DESSERT));
        menu.addItem(new FoodItem(13, "INDIAN", "Jalebi", 40, FoodType.DESSERT));
        menu.addItem(new FoodItem(14, "INDIAN", "Kheer", 55, FoodType.DESSERT));
        menu.addItem(new FoodItem(15, "INDIAN", "Kulfi", 60, FoodType.DESSERT));

        // ITALIAN
        menu.addItem(new FoodItem(16, "ITALIAN", "Bruschetta", 100, FoodType.STARTER));
        menu.addItem(new FoodItem(17, "ITALIAN", "Caprese Salad", 120, FoodType.STARTER));
        menu.addItem(new FoodItem(18, "ITALIAN", "Stuffed Mushrooms", 130, FoodType.STARTER));
        menu.addItem(new FoodItem(19, "ITALIAN", "Arancini", 110, FoodType.STARTER));

        menu.addItem(new FoodItem(20, "ITALIAN", "Fettuccine Alfredo", 270, FoodType.MAIN_COURSE));
        menu.addItem(new FoodItem(21, "ITALIAN", "Lasagna", 300, FoodType.MAIN_COURSE));
        menu.addItem(new FoodItem(22, "ITALIAN", "Spaghetti Carbonara", 260, FoodType.MAIN_COURSE));
        menu.addItem(new FoodItem(23, "ITALIAN", "Risotto alla Milanese", 280, FoodType.MAIN_COURSE));

        menu.addItem(new FoodItem(24, "ITALIAN", "Panna Cotta", 150, FoodType.DESSERT));
        menu.addItem(new FoodItem(25, "ITALIAN", "Cannoli", 140, FoodType.DESSERT));
        menu.addItem(new FoodItem(26, "ITALIAN", "Gelato", 130, FoodType.DESSERT));
        menu.addItem(new FoodItem(27, "ITALIAN", "Zabaione", 145, FoodType.DESSERT));

        // CHINESE	
        menu.addItem(new FoodItem(28, "CHINESE", "Spring Rolls", 80, FoodType.STARTER));
        menu.addItem(new FoodItem(29, "CHINESE", "Hot and Sour Soup", 90, FoodType.STARTER));
        menu.addItem(new FoodItem(30, "CHINESE", "Chicken Lollipop", 120, FoodType.STARTER));
        menu.addItem(new FoodItem(31, "CHINESE", "Wonton Soup", 100, FoodType.STARTER));

        menu.addItem(new FoodItem(32, "CHINESE", "Fried Rice", 140, FoodType.MAIN_COURSE));
        menu.addItem(new FoodItem(33, "CHINESE", "Kung Pao Chicken", 180, FoodType.MAIN_COURSE));
        menu.addItem(new FoodItem(34, "CHINESE", "Hakka Noodles", 150, FoodType.MAIN_COURSE));
        menu.addItem(new FoodItem(35, "CHINESE", "Szechuan Tofu", 160, FoodType.MAIN_COURSE));

        menu.addItem(new FoodItem(36, "CHINESE", "Mango Pudding", 120, FoodType.DESSERT));

        // Save to file
        menu.saveToFile("menu");
        System.out.println("Sample menu saved to menu.");
    }
}
