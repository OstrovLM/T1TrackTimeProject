package lev.ostrov.T1TrackTimeProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
@RequiredArgsConstructor
public class AsyncMethodExecLogServiceDecorator {
    private final MethodExecLogService methodExecLogService;

    public void asyncLogMethodExec(String methodName, Date executionTime, Long executionDuration) {
        methodExecLogService.logMethodExec(methodName, executionTime, executionDuration, true);
    }

}
