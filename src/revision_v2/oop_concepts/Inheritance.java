package revision_v2.oop_concepts;

public class Inheritance {
    public static void main(String[] args) {
        /**It is a mechanism by which one class is allowed
         to acquire the features(fields and methods) of another class.*/

        //super/parent/base class -> The class whose features are inherited

        //sub class
        //class that inherit the super class.
        MountainBike mb = new MountainBike(3, 100, 25);
        System.out.println(mb.toString());
        mb.applyBrake(1);
        System.out.println(mb.toString());

    }

    // Java program to illustrate the
// concept of inheritance

    // base class
    public static class Bicycle {
        // the Bicycle class has two fields
        public int gear;
        public int speed;

        // the Bicycle class has one constructor
        public Bicycle(int gear, int speed) {
            this.gear = gear;
            this.speed = speed;
        }

        // the Bicycle class has three methods
        public void applyBrake(int decrement) {
            speed -= decrement;
        }

        public void speedUp(int increment) {
            speed += increment;
        }

        // toString() method to print info of Bicycle
        public String toString() {
            return ("No of gears are " + gear + "\n"
                    + "speed of bicycle is " + speed);
        }
    }

    // derived class
    static class MountainBike extends Bicycle {

        // the MountainBike subclass adds one more field
        public int seatHeight;

        // the MountainBike subclass has one constructor
        public MountainBike(int gear, int speed,
                            int startHeight) {
            super(gear, speed);
            // invoking base-class(Bicycle) constructor

            seatHeight = startHeight;
        }

        // the MountainBike subclass adds one more method
        public void setHeight(int newValue) {
            seatHeight = newValue;
        }

        // overriding toString() method
        // of Bicycle to print more info
        @Override
        public String toString() {
            return (super.toString() + "\nseat height is "
                    + seatHeight);
        }
    }

}
