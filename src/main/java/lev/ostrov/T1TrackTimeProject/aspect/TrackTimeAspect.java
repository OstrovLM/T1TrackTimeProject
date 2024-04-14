package lev.ostrov.T1TrackTimeProject.aspect;

import lev.ostrov.T1TrackTimeProject.annotation.TrackTime;
import lev.ostrov.T1TrackTimeProject.service.MethodExecLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
@EnableAspectJAutoProxy
@RequiredArgsConstructor
@Slf4j
public class TrackTimeAspect {
    private final MethodExecLogService logRepositoryService;

    @Around("@annotation(trackTime)")
    public Object aroundExecution(ProceedingJoinPoint joinPoint, TrackTime trackTime) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Date executionTime = new Date();

        log.debug("Before method execution");
        Long beforeMethodTimeLong = System.currentTimeMillis();
        // Выполнение самого метода
        Object result = joinPoint.proceed();

        log.debug("After method execution");
        Long afterMethodTimeLong = System.currentTimeMillis();
        log.info("Время выполнения метода {} составляет {} мс ", methodName, (afterMethodTimeLong - beforeMethodTimeLong));
        Long executionDuration = afterMethodTimeLong - beforeMethodTimeLong;

        // Сохранение информации в базу данных
        logRepositoryService.logMethodExec(methodName, executionTime, executionDuration, false);

        return result;
    }
}
