package es.eoi.mundobancario.enums;

public enum TipoMovimientoEnum {
    PRESTAMO(1), INGRESO(2), AMORTIZACION(3), PAGO(4), INTERES(5);

    private final int id;

    TipoMovimientoEnum(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }
}
