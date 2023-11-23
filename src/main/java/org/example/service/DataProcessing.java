package org.example.service;

import org.example.model.Phone;
import org.example.model.Phones;
import org.example.model.Specifications;

import javax.swing.plaf.basic.BasicTreeUI;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DataProcessing {
    private final Phones phones;

    public DataProcessing(Phones phones) {
        this.phones = phones;
    }

    public List<Phone> sortDataByPrice() {
        try {
            return this.phones.getPhones().stream()
                    .sorted(Comparator.comparingInt(Phone::getPrice)).toList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Phone> filterByPrice(Integer price) {
        try {
            return this.phones.getPhones().stream().filter(x -> x.getPrice() < price).toList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Integer amountOfModels(List<String> models) {
        try {
            return this.phones.getPhones().stream().mapToInt(x -> models.contains(x.getModel()) ? x.getPrice() : 0).sum();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public Set<String> getListOFBrands() {
        try {
            return this.phones.getPhones().stream().map(Phone::getBrand)
                    .collect(Collectors.toSet());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Specifications> getSpecificationsOfModel(String model) {
        try {
            return this.phones.getPhones().stream().filter(x -> x.getModel().equals(model))
                    .map(Phone::getSpecifications)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public String listOfPhonesToString(List<Phone> phones) {
        StringBuilder result = new StringBuilder();
        phones.forEach(x -> result.append("Бренд: " + x.getBrand() + "\n" +
                "Модель: " + x.getModel() + "\n" +
                "Цвет: " + x.getColor() + "\n" +
                "Цена: " + x.getPrice() + "\n" +
                "Процессор: " + x.getSpecifications().getCpu() + "\n" +
                "Память: " + x.getSpecifications().getStorage() + "\n\n"));
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        this.phones.getPhones().forEach(x -> result.append("Бренд: " + x.getBrand() + "\n" +
                "Модель: " + x.getModel() + "\n" +
                "Цвет: " + x.getColor() + "\n" +
                "Цена: " + x.getPrice() + "\n" +
                "Процессор: " + x.getSpecifications().getCpu() + "\n" +
                "Память: " + x.getSpecifications().getStorage() + "\n\n"));
        return result.toString();
    }
}
