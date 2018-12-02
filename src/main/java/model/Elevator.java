package model;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class Elevator {

    private final int id;
    private AtomicInteger currentFloor;
    private AtomicBoolean isAvailable;

    public Elevator(int id) {
        currentFloor = new AtomicInteger(0);
        isAvailable.set(true);
        this.id = id;
    }

    public void moveUp() {
        transitTime();
        // condition top floor is enforced outside this class
        currentFloor.incrementAndGet();
    }

    public void moveDown() {
        transitTime();
        // condition lowest floor is enforced outside this class
        currentFloor.decrementAndGet();
    }

    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable.get();
    }

    public void setAvailable(boolean available) {
        isAvailable.set(available);
    }

    private void transitTime() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "id=" + id +
                ", currentFloor=" + currentFloor.get() +
                ", isAvailable=" + isAvailable.get() +
                '}';
    }
}
