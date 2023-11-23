package org.example;

import lombok.val;
import org.example.model.Phone;
import org.example.model.Phones;
import org.example.model.Specifications;
import org.example.service.DataProcessing;
import org.example.service.XmlToJsonConverter;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        val converter = new XmlToJsonConverter();
        DataProcessing dataProcessing = new DataProcessing(converter.getPhones("src\\main\\resources\\data.xml"));

        Scanner in = new Scanner(System.in);

        String choise = "1";
        while (!choise.equals("0")) {
            choise = showMenu(in);
            switch (Task.Option(choise)) {
                case SHOW_PHONES -> {
                    System.out.println(dataProcessing.toString());
                }
                case SORT_BY_PRICE -> {
                    List<Phone> phones = dataProcessing.sortDataByPrice();
                    System.out.println(dataProcessing.listOfPhonesToString(phones));
                }
                case FILTER_BY_PRICE -> {
                    System.out.print("Введите цену: ");
                    String price = in.nextLine();
                    try {
                        List<Phone> phones = dataProcessing.filterByPrice(Integer.parseInt(price));
                        System.out.println(dataProcessing.listOfPhonesToString(phones));
                    } catch (Exception ex) {
                        System.out.println("Введите корректное число!");
                    }
                }
                case AMOUNT_OF_MODELS -> {
                    List<String> models = new ArrayList<>();
                    String model = "2";
                    while (!model.equals("")) {
                        models.add(model);
                        model = in.nextLine();
                    }
                    models.remove(0);
                    System.out.print("Суммарная стоимость моделей(");
                    for (int i = 0; i < models.size() - 1; i++)
                        System.out.print(models.get(i) + ", ");
                    System.out.println(models.get(models.size() - 1) + ") : " + dataProcessing.amountOfModels(models));
                }
                case GET_BRANDS -> {
                    System.out.println("Бренды: ");
                    for (String brand : dataProcessing.getListOFBrands()) {
                        System.out.println(brand);
                    }
                }
                case GET_SPECIFICATIONS_OF_MODELS -> {
                    String model = in.nextLine();
                    List<Specifications> specifications = dataProcessing.getSpecificationsOfModel(model);
                    System.out.println("Модель: " + model);
                    int i = 1;
                    for (Specifications spec : specifications) {
                        System.out.println(i++ + ") Процессор: " + spec.getCpu() + ".Память: " + spec.getStorage() + ".");
                    }
                }
            }
        }
    }

    private static String showMenu(Scanner in) {
        System.out.println("""
                Меню:
                1) Вывести список телефонов.
                2) Отсортировать телефоны по цене.
                3) Фильтр телефонов по цене.
                4) Суммарная стоимость указанных моделей.
                5) Получить список брендов.
                6) Получить характеристики указанных моделей.
                0) Покинуть меню.""");
        System.out.print("Вариант: ");
        return in.nextLine();
    }
}