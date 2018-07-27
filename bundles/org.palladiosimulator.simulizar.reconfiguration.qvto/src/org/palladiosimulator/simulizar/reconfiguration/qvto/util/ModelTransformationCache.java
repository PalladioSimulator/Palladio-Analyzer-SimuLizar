package org.palladiosimulator.simulizar.reconfiguration.qvto.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.reconfiguration.qvto.QvtoModelTransformation;

/**
 * This cache implementation is used to store QVTo transformations (in terms of
 * {@link TransformationData} objects) that can be executed by QVTo executors
 * during reconfigurations. To store a transformation in the cache, its
 * corresponding {@link URI} is used as tag.
 * 
 * @author Florian Rosenthal
 *
 */
public class ModelTransformationCache {

	// cache is backed by map: use URI of transformation as key/tag
	private final Map<URI, QvtoModelTransformation> cache;
	private final ModelTransformationFactory modelTransformationFactory;

	private static final Logger LOGGER = Logger.getLogger(ModelTransformationCache.class);

	/**
	 * Initializes a new instance of the {@link ModelTransformationCache} class.
	 * 
	 * @param initialTransformations
	 *            A set of transformation {@link URI URIs} that shall be stored
	 *            in the cache, might be empty.
	 */
	@SafeVarargs
	public ModelTransformationCache(URI... initialTransformations) {
		this.cache = new HashMap<>();
		this.modelTransformationFactory = new ModelTransformationFactory();
		store(initialTransformations);
	}

	/**
	 * Stores the QVTo transformations specified by the given URIs in the cache.
	 * 
	 * @param transformationUris
	 *            A set of {@link URI URIs} that point to QVTo transformations.
	 * @throws NullPointerException
	 *             In case {@code transformationUris == null}.
	 * @throws IllegalArgumentException
	 *             In case any of the transformations is already present in the
	 *             cache.
	 */
	@SafeVarargs
	public final void store(URI... transformationUris) {
		URI[] uris = Objects.requireNonNull(transformationUris);
		for (URI transformationUri : uris) {
			if (contains(transformationUri)) {
				throw new IllegalArgumentException("Transformation " + transformationUri + " already in store.");
			}
			LOGGER.debug("Cache reconfiguration rule \"" + transformationUri + "\"");
			this.cache.put(transformationUri,
					this.modelTransformationFactory.createModelTransformation(transformationUri));
		}
	}

	/**
	 * Attempts to retrieve the QVTo transformation that is associated with the
	 * given URI from the cache.
	 * 
	 * @param transformationUri
	 *            A {@link URI} that points to a QVTo transformation.
	 * @return The {@link TransformationData} of the transformation, or
	 *         {@code null} if it is not present in the cache.
	 * @throws NullPointerException
	 *             In case the given URI is {@code null}.
	 * @see #contains(URI)
	 */
	public Optional<QvtoModelTransformation> get(URI transformationUri) {
		if (!this.contains(transformationUri)){
			this.store(transformationUri);
		}
		return Optional.ofNullable(this.cache.get(Objects.requireNonNull(transformationUri)));
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
	 * Gets whether the QVTo transformation which the given URI points to, is
	 * currently in the cache.
	 * 
	 * @param transformationUri
	 *            A {@link URI} that points to a QVTo transformation.
	 * @return {@code true}, iff the transformation is stored, {@code false}
	 *         otherwise
	 * 
	 * @throws NullPointerException
	 *             In case the given URI is {@code null}.
	 */
	public boolean contains(URI transformationUri) {
		return this.cache.containsKey(Objects.requireNonNull(transformationUri));
	}

	/**
	 * Gets all the transformations, in terms of the respective
	 * {@link TransformationData} currently stored in this cache.
	 * 
	 * @return An {@link Iterable} of all the stored transformations.
	 */
	public Iterable<QvtoModelTransformation> getAll() {
		return this.cache.values();
	}

	/**
	 * Clears the cache, that is, all content is discarded.
	 */
	public void clear() {
		this.cache.clear();
	}
}
