package be.tim.partyapp.model;

public enum ConsumptionType {

    BEER("bier"),
    MALIBU("malibu"),
    COLA("cola"),
    RUM("rum"),
    VODKA("vodka"),
    BREEZER("breezer"),
    PASSOA("passoa"),
    PISSANG("pissang"),
    BOSWANDELING("boswandeling"),
    GP("gp");

    private String name;

    private ConsumptionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
