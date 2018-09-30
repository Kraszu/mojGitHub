
package application;
//Maciej Kubiniec R00144142

import javafx.scene.layout.VBox;

final public class Tie extends VBox
{

    ClockPane clock;
    StopwatchPane stopwatch;

    public Tie()
    {
        clock = new ClockPane();
        stopwatch = new StopwatchPane();

        getChildren().addAll(clock.getPane(), stopwatch.getStopWatch());
    }

}
