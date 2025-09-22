package hotel.repository;

@FunctionalInterface
public interface IdentityExtractor<T> {
    String getId(T object);
}
