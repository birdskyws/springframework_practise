package mooc.birdsky.springframework.aop.api.Introduction;

public interface Lockable {
    void lock();
    void unlock();
    boolean locked();
}
