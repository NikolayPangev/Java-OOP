package WorkingWithAbstraction_Exercise.TrafficLights;

public class TrafficLights {
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public TrafficLights(Color color) {
        this.color = color;
    }

    public void changeColor(){
        switch (color){
            case RED:
                color = Color.GREEN;
                break;
            case YELLOW:
                color = Color.RED;
                break;
            case GREEN:
                color = Color.YELLOW;
                break;
        }
    }
}
