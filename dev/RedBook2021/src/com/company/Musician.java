package com.company;

import java.util.Comparator;

public class Musician {
    /*
    * we are creating comparator objects that have the method compare()
    * then pass the object as the second argument to a sort method.
    * the sort method will callback the compare() function to carry out the compare.
    * */
    public static final Comparator<Musician> BY_NAME = new ByName();
    public static final Comparator<Musician> BY_AGE = new ByAge();
    public static final Comparator<Musician> BY_SKILLLEVEL = new BySkillLevel();
    private final String name;
    private final int age;
    private final int skillLevel;

    public Musician(String n, int a, int s) {
        name = n;
        age = a;
        skillLevel = s;
    }

    private static class ByName implements Comparator<Musician> {
        public int compare(Musician o1, Musician o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    private static class ByAge implements Comparator<Musician> {
        public int compare(Musician o1, Musician o2) {
            return o1.age - o2.age; // < or > 0 is all we're checking
        }
    }

    private static class BySkillLevel implements Comparator<Musician> {
        public int compare(Musician o1, Musician o2) {
            return o1.skillLevel - o2.skillLevel;
        }
    }

    public void print() {
        System.out.println("name: " + name + ", age: " + age + ", skill level: " + skillLevel);
    }
}
