package com.company;

interface Selector {
    boolean end();
    Object current();
    void next();
}

class Holder {
    private String value;
    Holder(int value) {
        this.value = Integer.toString(value);
    }

    @Override
    public String toString() {
        return this.value;
    }
}

public class Sequence {
    private Object[] items;
    private int next = 0;

    public Sequence(int size) {
        items = new Object[size];
    }

    public void add(Object x) {
        if (next < items.length) items[next++] = x;
    }

    private class ReverseSelector implements Selector {
        private int i = items.length - 1;
        @Override
        public boolean end() {
            return i == -1;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if (!end()) i--;
        }

    }

    public Selector reverseSelector() {
        return new ReverseSelector();
    }

    private class SequenceSelector implements Selector {

        private int i = 0;

        @Override
        public boolean end() {
            return i == items.length;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if (i < items.length) i++;
        }

        // Exercise Four (Producing the ref to outer class (Sequence)
        public Sequence sequenceRef() {
            return Sequence.this;
        }
    }

    public Selector selector() {
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);

        // Get Ref to Inner class
        // Sequence.SequenceSelector sq = sequence.new SequenceSelector();

        for (int i = 1; i <= sequence.items.length; i++) {
            sequence.add(new Holder(i)); // After Creating Holder class
//            sequence.add(Integer.toString(i)); // Before
        }
        Selector selector = sequence.selector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }

        for (int i = sequence.items.length; i >= 0; i--) {
            sequence.add(new Holder(i));
        }

        Selector selector1 = sequence.reverseSelector();
        System.out.print("Reverse Selector: ");
        while(!selector1.end()) {
            System.out.print(selector1.current() + " ");
            selector1.next();
        }


    }

}
