package timicasto.quantumbase.capability;

public class Implementation implements IMoisture{
    private int conV = 20;

    @Override
    public int getMoisture() {
        return this.conV;
    }

    @Override
    public void setMoisture(int conV) {
        this.conV = conV;
    }
}
