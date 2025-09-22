package hotel.repository;

import hotel.util.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenericRepository<T> {
    private final List<T> items = new ArrayList<>();
    private final IdentityExtractor<T> extractor;

    public GenericRepository(IdentityExtractor<T> extractor) {
        this.extractor = extractor;
    }

    public void add(T item) {
        items.add(item);
        Logger.info("Added: " + item);
    }

    public boolean remove(T item) {
        boolean removed = items.remove(item);
        if (removed) Logger.info("Removed: " + item);
        return removed;
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public Optional<T> findByIdentity(String id) {
        return items.stream()
                .filter(item -> extractor.getId(item).equals(id))
                .findFirst();
    }
}
