package model;

public enum Moneda {
    ARGENTINA ("ARS"), BRASIL ("BRL"), COLOMBIA ("COP"), ESTADOSUNIDOS ("USD");


    private final String codigo;

    Moneda(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
