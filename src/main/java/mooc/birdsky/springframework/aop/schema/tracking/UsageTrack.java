package mooc.birdsky.springframework.aop.schema.tracking;

public interface UsageTrack {
    void incrementUsecount();
    void printUsecount();
}
