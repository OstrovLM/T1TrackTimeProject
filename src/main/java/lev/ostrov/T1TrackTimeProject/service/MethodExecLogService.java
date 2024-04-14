package lev.ostrov.T1TrackTimeProject.service;

import jakarta.transaction.Transactional;
import lev.ostrov.T1TrackTimeProject.model.MethodExecLog;
import lev.ostrov.T1TrackTimeProject.repository.MethodExecLogRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MethodExecLogService {
    private final MethodExecLogRepository methodExecLogRepository;

    @Transactional
    public void logMethodExec(String methodName, Date executionTime, Long executionDuration, boolean asyncSave) {
        MethodExecLog log = new MethodExecLog();
        log.setMethodName(methodName);
        log.setExecutionTime(executionTime);
        log.setExecutionDuration(executionDuration);
        log.setAsyncSave(asyncSave);
        methodExecLogRepository.save(log);
    }

    public List<MethodExecLog> getAllLogs() {
        return methodExecLogRepository.findAll();
    }

    public double getAverageExecTime() {
        List<MethodExecLog> logs = methodExecLogRepository.findAll();
        double totalDuration = logs.stream().mapToLong(MethodExecLog::getExecutionDuration).sum();
        return totalDuration / logs.size();
    }

    public double getTotalExecutionTime() {
        List<MethodExecLog> logs = methodExecLogRepository.findAll();
        return logs.stream().mapToLong(MethodExecLog::getExecutionDuration).sum();
    }
}

