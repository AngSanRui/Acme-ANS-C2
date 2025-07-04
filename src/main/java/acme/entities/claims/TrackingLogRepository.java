
package acme.entities.claims;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface TrackingLogRepository extends AbstractRepository {

	@Query("select MAX(t.percentage) from TrackingLog t WHERE t.claim.id = :claimId")
	Integer findMaxPercentage(int claimId);

	@Query("SELECT t FROM TrackingLog t WHERE t.claim.id = :claimId ORDER BY t.creationMoment DESC")
	List<TrackingLog> findTrackingLogsOrderedByTime(int claimId);

	@Query("SELECT t FROM TrackingLog t WHERE t.claim.id = :claimId ORDER BY t.percentage DESC")
	List<TrackingLog> findTrackingLogsOrderedByPercentage(int claimId);

	@Query("SELECT t FROM TrackingLog t WHERE t.claim.id = :claimId AND t.draftMode = false ORDER BY t.percentage DESC")
	List<TrackingLog> findPublishedTrackingLogsOrderedByPercentage(int claimId);

	@Query("SELECT t FROM TrackingLog t WHERE t.claim.id = :masterId")
	Collection<TrackingLog> findTrackingLogsByMasterId(int masterId);

	@Query("SELECT t FROM TrackingLog t WHERE t.claim.id = :masterId AND t.draftMode = false")
	Collection<TrackingLog> findPublishedTrackingLogsByMasterId(int masterId);
}
