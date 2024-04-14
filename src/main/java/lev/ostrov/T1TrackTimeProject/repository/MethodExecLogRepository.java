package lev.ostrov.T1TrackTimeProject.repository;

import lev.ostrov.T1TrackTimeProject.model.MethodExecLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodExecLogRepository extends JpaRepository<MethodExecLog, Long> {
}
