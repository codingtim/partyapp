package be.tim.partyapp.model;

/**
 */
public class Consumption {

    private long id;
    private final String nfcId;
    private final String type;
    private final String timestamp;

    public Consumption(long id, String nfcId, String type, String timestamp) {
        this.id = id;
        this.nfcId = nfcId;
        this.type = type;
        this.timestamp = timestamp;
    }

    public Consumption(String nfcId, String type, String timestamp) {
        this.nfcId = nfcId;
        this.type = type;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public String getNfcId() {
        return nfcId;
    }

    public String getType() {
        return type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return type + " " + timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Consumption that = (Consumption) o;

        if (nfcId != null ? !nfcId.equals(that.nfcId) : that.nfcId != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nfcId != null ? nfcId.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

    public void setId(long id) {
        this.id = id;
    }
}
