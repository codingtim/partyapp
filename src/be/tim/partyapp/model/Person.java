package be.tim.partyapp.model;

/**
 */
public class Person {
    private long id;
    private final String name;
    private final String nfcId;

    public Person(long id, String name, String nfcId) {
        this.id = id;
        this.name = name;
        this.nfcId = nfcId;
    }

    public Person(String name, String nfcId) {
        this.name = name;
        this.nfcId = nfcId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNfcId() {
        return nfcId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (nfcId != null ? !nfcId.equals(person.nfcId) : person.nfcId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (nfcId != null ? nfcId.hashCode() : 0);
        return result;
    }
}
