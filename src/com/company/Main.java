package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Stream<Person> stream1 = persons.stream();
        long count = stream1.filter(person -> person.getAge() < 18).count();
        System.out.println(count);
        System.out.println("====================");

        Stream<Person> stream2 = persons.stream();
        List<String> surname = stream2.filter(person -> person.getAge() >= 18 && person.getAge() <= 27).map(Person::getFamily).collect(Collectors.toList());
        System.out.println(surname);
        System.out.println("====================");

        Stream<Person> stream3 = persons.stream(); //стрим по мужчинам
        List<String> surnameMan = stream3.filter(person -> person.getAge() >= 18)
                .filter(person -> (person.getSex() == Sex.MAN && person.getAge() <= 65))
                .filter(person -> person.getEducation()==Education.HIGHER)
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for (String s : surnameMan) {
            System.out.println(s);
        }
        System.out.println("====================");
        Stream<Person> stream4 = persons.stream(); //стрим по женщинам
        List<String> surnameWoman = stream4.filter(person -> person.getAge() >= 18)
                .filter(person -> (person.getSex() == Sex.WOMEN && person.getAge() <= 60))
                .filter(person -> person.getEducation()==Education.HIGHER)
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for (String s : surnameWoman) {
            System.out.println(s);
        }
    }
}
