package GUI;

public class Coordenada {

    private Double xRatio;
    private Double yRatio;

    public Coordenada(Double xRatio, Double yRatio) {
        this.xRatio = xRatio;
        this.yRatio = yRatio;
    }

    public void setxRatio(Double xRatio) {
        this.xRatio = xRatio;
    }

    public void setyRatio(Double yRatio) {
        this.yRatio = yRatio;
    }

    public Double getxRatio() {
        return xRatio;
    }

    public Double getyRatio() {
        return yRatio;
    }

    public int getx(int x) {
        return (int) (x * this.getxRatio());
    }

    public int gety(int y) {
        return (int) (y * this.getyRatio());
    }

}
