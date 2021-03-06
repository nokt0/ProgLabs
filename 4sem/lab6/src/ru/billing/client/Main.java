package ru.billing.client;

import java.util.Date;
import ru.billing.stocklist.*;
import ru.itmo.client.CatalogFileLoader;
import ru.itmo.exceptions.CatalogLoadException;
import ru.itmo.exceptions.ItemAlreadyExistsException;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws CatalogLoadException, ItemAlreadyExistsException {
        // Заполнение каталога
        GenericItem milkItem = new GenericItem();
        milkItem.setName("milk");
        milkItem.setPrice((float) 52.13);

        GenericItem waterItem = new GenericItem();
        waterItem.setName("water");
        waterItem.setPrice((float) 31);

        GenericItem meatItem = new GenericItem();
        meatItem.setName("meat");
        meatItem.setPrice((float) 300);

        FoodItem chipsItem = new FoodItem();
        chipsItem.setName("chips");
        chipsItem.setPrice((float) 49.9);
        chipsItem.setExpires((short) 80);
        chipsItem.setDateOfIncome(new Date());

        TechnicalItem iphoneItem = new TechnicalItem();
        iphoneItem.setName("Iphone");
        iphoneItem.setPrice(75000);
        iphoneItem.setWarrantyTime((short) 365);

        FoodItem marsItem = new FoodItem("mars", 63, (short) 70);
        FoodItem twixItem = new FoodItem("twix", 63, (short) 70);
        FoodItem pastaItem = new FoodItem("pasta", 73, (short) 100);
        FoodItem saltItem = new FoodItem("salt", 31, (short) 400);
        FoodItem pepsiItem = new FoodItem("pepsi", 120, (short) 100);

        GenericItem[] itemsArray = { marsItem, twixItem, pastaItem, saltItem, pepsiItem, iphoneItem, chipsItem,
                meatItem, waterItem, milkItem };
        ItemCatalog catalog = new ItemCatalog();
        TaskItemCatalog catalog1 = new TaskItemCatalog();

        for (var i : itemsArray) {
            catalog.addItem(i);
            catalog1.add(i);
        }

//        // Сравнение времени выполнения
//        long begin = new Date().getTime();
//        for (int i = 0; i < 100000; i++)
//            catalog.findItemByID(10);
//        long end = new Date().getTime();
//        System.out.println("In HashMap: " + (end - begin));
//        begin = new Date().getTime();
//        for (int i = 0; i < 100000; i++)
//            catalog.findItemByIDAL(10);
//        end = new Date().getTime();
//        System.out.println("In ArrayList: " + (end - begin));

        // загрузка лоадером
        CatalogLoader loader = new CatalogStubLoader();
        loader.load(catalog);
        //catalog.printItems();

        var set = catalog1.findByCategoryAndPrice(ItemCategory.FOOD,false);
        System.out.println(set);

        var fls = new CatalogFileLoader("./products.txt");
        fls.load(catalog);

    }
}