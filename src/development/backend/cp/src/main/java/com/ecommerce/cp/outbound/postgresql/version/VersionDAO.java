package com.ecommerce.cp.outbound.postgresql.version;

import com.ecommerce.cp.core.business.resources.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface VersionDAO extends JpaRepository<Version, String> {

    @Lock(LockModeType.NONE)
    @Query("select v from Version v where v.groupId = ?1")
    Optional<Version> findOneForRead(String id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select v from Version v where v.groupId = ?1")
    Optional<Version> findOneForUpdate(String id);
}
