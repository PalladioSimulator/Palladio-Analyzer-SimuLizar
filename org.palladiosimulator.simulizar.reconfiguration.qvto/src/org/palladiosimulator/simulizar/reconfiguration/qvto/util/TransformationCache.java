package org.palladiosimulator.simulizar.reconfiguration.qvto.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;

/**
 * This cache implementation is used to store QVTo transformations (in terms of
 * {@link TransformationData} objects) that can be executed by QVTo executors during
 * reconfigurations. To store a transformation in the cache, its corresponding {@link URI} is used
 * as tag.
 * 
 * @author Florian Rosenthal
 *
 */
public class TransformationCache {

    // cache is backed by map: use URI of transformation as key/tag
    private final Map<URI, TransformationData> cache;
    private final TransformationDataFactory transformationDataFactory;

    private static final Logger LOGGER = Logger.getLogger(TransformationCache.class);

    /**
     * Initializes a new instance of the {@link TransformationCache} class.
     * 
     * @param initialTransformations
     *            A set of transformation {@link URI URIs} that shall be stored in the cache, might
     *            be empty.
     */
    @SafeVarargs
    public TransformationCache(URI... initialTransformations) {
        this.cache = new HashMap<>();
        this.transformationDataFactory = new TransformationDataFactory();
        store(initialTransformations);
    }

    /**
     * Copy constructor.
     * 
     * @param from
     *            The instance to copy.
     */
    private TransformationCache(TransformationCache from) {
        this.cache = new HashMap<>();
        this.cache.putAll(from.cache);
        this.transformationDataFactory = from.transformationDataFactory;
    }

    /**
     * Creates a snapshot of the current state of the cache.<br>
     * More precisely, this method creates an instance that contains the same transformations as
     * this one.
     * 
     * @return A {@link TransformationCache} which is a snapshot of the current state of this
     *         instance.
     */
    public TransformationCache snapshot() {
        return new TransformationCache(this);
    }

    /**
     * Stores the QVTo transformations specified by the given URIs in the cache.
     * 
     * @param transformationUris
     *            A set of {@link URI URIs} that point to QVTo transformations.
     * @throws NullPointerException
     *             In case {@code transformationUris == null}.
     * @throws IllegalArgumentException
     *             In case any of the transformations is already present in the cache.
     */
    @SafeVarargs
    public final void store(URI... transformationUris) {
        URI[] uris = Objects.requireNonNull(transformationUris);
        for (URI transformationUri : uris) {
            if (contains(transformationUri)) {
                throw new IllegalArgumentException("Transformation " + transformationUri + " already in store.");
            }
            LOGGER.info("Cache reconfiguration rule \"" + transformationUri + "\"");
            this.cache.put(transformationUri,
                    this.transformationDataFactory.createTransformationData(transformationUri));
        }
    }

    /**
     * Removes the QVTo transformations specified by the given URIs from this cache, if present.
     * 
     * @param transformationUris
     *            A set of {@link URI URIs} that point to QVTo transformations.
     * @throws NullPointerException
     *             In case any of the given uris is {@code null}.
     */
    @SafeVarargs
    public final void remove(URI... transformationUris) {
        URI[] uris = Objects.requireNonNull(transformationUris);
        Arrays.stream(uris).map(Objects::requireNonNull).forEach(this.cache::remove);
    }

    /**
     * Attempts to retrieve the QVTo transformation that is associated with the given URI from the
     * cache.
     * 
     * @param transformationUri
     *            A {@link URI} that points to a QVTo transformation.
     * @return An {@link Optional} containing the {@link TransformationData} of the transformation,
     *         which is empty if the transformation is not present in cache.
     * @throws NullPointerException
     *             In case the given URI is {@code null}.
     * @see #contains(URI)
     */
    public Optional<TransformationData> get(URI transformationUri) {
        return Optional.ofNullable(this.cache.get(Objects.requireNonNull(transformationUri)));
    }

    /**
     * Gets whether the QVTo transformation which the given URI points to, is currently in the
     * cache.
     * 
     * @param transformationUri
     *            A {@link URI} that points to a QVTo transformation.
     * @return {@code true}, iff the transformation is stored, {@code false} otherwise
     * 
     * @throws NullPointerException
     *             In case the given URI is {@code null}.
     */
    public boolean contains(URI transformationUri) {
        return this.cache.containsKey(Objects.requireNonNull(transformationUri));
    }

    /**
     * Gets all the transformations, in terms of the respective {@link TransformationData} currently
     * stored in this cache.
     * 
     * @return An {@link Iterable} of all the stored transformations.
     */
    public Iterable<TransformationData> getAll() {
        return this.cache.values();
    }

    /**
     * Clears the cache, that is, all content is discarded.
     */
    public void clear() {
        this.cache.clear();
    }
}
