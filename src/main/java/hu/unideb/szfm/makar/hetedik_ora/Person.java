package hu.unideb.szfm.makar.hetedik_ora;

import java.util.Objects;
//teszt
public class Person {
    private int id;
    private String name;

    public static Person scannedPerson(PersonDataReader pdr) {
        int id;
        String name;
        id = pdr.getId();
        name = pdr.getName();
        return new Person(id, name);
    }

    public Person(int id, String name) {
        if (id < 0) {
            throw new RuntimeException();
        }
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" + "id= " + id + ", name = " + name + '}';
    }

    public boolean nameContainsChar(char c)
    {
        return getName().contains("" + c);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * this.id;
        hash = 11 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
