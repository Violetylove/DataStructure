package test.others;

import java.util.Objects;

/**
 * @author Winter Yuan
 * @version 1.0
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person person1 = new Person(10, "张飞");
        Person person2 = (Person) person1.clone();

        System.out.println(person1);
        System.out.println(person2);
    }
}


class Person implements Cloneable{
    int age;
    String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

/*    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}