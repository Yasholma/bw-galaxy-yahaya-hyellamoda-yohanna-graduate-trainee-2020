package com.company;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;
import static java.lang.System.nanoTime;

abstract class Event {
    private long eventTime;
    protected final long delayTime;

    protected Event(long delayTime) {
        this.delayTime = delayTime;
        start();
    }

    public void start() {
        eventTime = nanoTime() + this.delayTime;
    }

    public boolean ready() {
        return nanoTime() >= eventTime;
    }

    public abstract void action();

}

class Controller {
    private List<Event> eventList = new ArrayList<>();
    public void addEvent(Event e) {eventList.add(e);}
    public void run() {
        while(eventList.size() > 0) {
            for(Event e: new ArrayList<>(eventList)) {
                if (e.ready()) {
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
            }
        }
    }
}

class GreenHouseControls extends Controller {
    public class Bell extends Event {
        protected Bell(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            addEvent(new Bell(delayTime));
        }

        @Override
        public String toString() {
            return "Bing!";
        }
    }

    public class Restart extends Event {
        private Event[] eventList;
        protected Restart(long delayTime, Event[] eventList) {
            super(delayTime);
            this.eventList = eventList;
            for (Event e: eventList) {
                addEvent(e);
            }
        }

        @Override
        public void action() {
            for (Event e: eventList) {
                e.start();
                addEvent(e);
            }
            start();
            addEvent(this);
        }

        @Override
        public String toString() {
            return "Restarting System";
        }
    }

    public static class Terminate extends Event {

        protected Terminate(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            exit(0);
        }

        @Override
        public String toString() {
            return "Terminating";
        }
    }

    private boolean fan = false;
    public class FanOn extends Event {

        protected FanOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            fan = true;
        }

        @Override
        public String toString() {
            return "Fan is turn on.";
        }
    }

    public class FanOff extends Event {

        protected FanOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            fan = false;
        }

        @Override
        public String toString() {
            return "Fan is turn off.";
        }
    }

}

public class ExerciseTwentyFour {
    public static void main(String[] args) {
        GreenHouseControls greenHouseControls = new GreenHouseControls();

        greenHouseControls.addEvent(greenHouseControls.new Bell(1000));
        Event[] events = {
                greenHouseControls.new FanOn(500),
                greenHouseControls.new FanOn(700)
        };

        greenHouseControls.addEvent(greenHouseControls.new Restart(2000, events));
        if (args.length == 1) {
            greenHouseControls.addEvent(new GreenHouseControls.Terminate(new Integer(args[0])));
        }

        greenHouseControls.run();
    }
}
