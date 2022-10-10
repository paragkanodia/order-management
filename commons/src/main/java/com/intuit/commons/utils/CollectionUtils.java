package com.intuit.commons.utils;

import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@UtilityClass
public class CollectionUtils {

    public boolean isEmpty(Collection<?> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }

    public boolean isEmpty(Map<?, ?> map) {
        return Objects.isNull(map) || map.isEmpty();
    }

    /**
     * This method does the job of collection.contains but returns the found object.
     * Implementation of this method does an simple Objects.equal comparison
     *
     * @param collection - source collection to find the required object
     * @param source     - source object for comparison
     * @return Return the desired object if found else returns null
     */
    public <T> T findIfPresent(final Collection<T> collection, final T source) {
        if (Objects.nonNull(collection) && Objects.nonNull(source) && !collection.isEmpty()) {
            List<T> filtered = filterByPredicate(collection, isEqual(source));
            if (!filtered.isEmpty()) {
                return filtered.get(0);
            }
        }
        return null;
    }

    /**
     * This method does the job of collection.contains but returns all the found objects.
     * Implementation of this method does an simple Objects.equal comparison
     *
     * @param collection - source collection to find the required object
     * @param source     - source object for comparison
     * @return Return the desired list if matches are found else returns an empty list
     */
    public <T> List<T> findAllIfPresent(final Collection<T> collection, final T source) {
        if (Objects.nonNull(collection) && Objects.nonNull(source) && !collection.isEmpty()) {
            return filterByPredicate(collection, isEqual(source));
        }
        return Collections.emptyList();
    }

    /**
     * This method does the job of collection.contains but returns the found object.
     * Implementation of this method does a object comparison using the provided predicate
     *
     * @param collection - source collection to find the required object
     * @param predicate  - predicate for comparison
     * @return Return the desired object if found else returns null
     */
    public <T> T findIfPresent(final Collection<T> collection, Predicate<T> predicate) {
        if (Objects.nonNull(collection) && Objects.nonNull(predicate) && !collection.isEmpty()) {
            List<T> filtered = filterByPredicate(collection, predicate);
            if (!filtered.isEmpty()) {
                return filtered.get(0);
            }
        }
        return null;
    }

    /**
     * This method does the job of collection.contains but returns all the found objects.
     * Implementation of this method does a object comparison using the provided predicate
     *
     * @param collection
     * @param predicate  - predicate for comparison
     * @return Return the desired list if matches are found else returns am empty list
     */
    public <T> List<T> findAllIfPresent(final Collection<T> collection, Predicate<T> predicate) {
        if (Objects.nonNull(collection) && Objects.nonNull(predicate) && !collection.isEmpty()) {
            return filterByPredicate(collection, predicate);
        }
        return Collections.emptyList();
    }

    /* Internal methods for comparison*/
    private <T> List<T> filterByPredicate(Collection<T> collection, Predicate<T> predicate) {
        if (Objects.nonNull(collection)) {
            return collection.parallelStream()
                    .filter(predicate).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private <T> Predicate<T> isEqual(T source) {
        return obj -> obj.equals(source);
    }

    public <T> List<T> removeNullElements(Collection<T> c) {
        if (Objects.nonNull(c)) {
            return c.parallelStream()
                    .filter(Objects::nonNull).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
