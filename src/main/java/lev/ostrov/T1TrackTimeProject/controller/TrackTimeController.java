package lev.ostrov.T1TrackTimeProject.controller;

import io.swagger.v3.oas.annotations.Operation;
import lev.ostrov.T1TrackTimeProject.model.MethodExecLog;
import lev.ostrov.T1TrackTimeProject.service.MethodExecLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tracktime")
@Slf4j
public class TrackTimeController {
    private final MethodExecLogService methodExecLogService;

    @GetMapping
    @Operation(summary = "Get all logs")
    public List<MethodExecLog> getAllLogs(){
        log.trace("Call getAllLogs");
        return methodExecLogService.getAllLogs();
    }

    @GetMapping("/average")
    @Operation(summary = "Get average execution time")
    public double getAverageExecutionTime() {
        log.trace("Call getAverageExecutionTime");
        return methodExecLogService.getAverageExecTime();
    }

    @GetMapping("/total")
    @Operation(summary = "Get total execution time")
    public double getTotalExecutionTime() {
        log.trace("Call getTotalExecutionTime");
        return methodExecLogService.getTotalExecutionTime();
    }
}
