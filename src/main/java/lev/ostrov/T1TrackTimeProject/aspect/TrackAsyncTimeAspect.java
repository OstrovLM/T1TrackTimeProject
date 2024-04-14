package lev.ostrov.T1TrackTimeProject.aspect;

import lev.ostrov.T1TrackTimeProject.service.AsyncMethodExecLogServiceDecorator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Component
@Aspect
@EnableAspectJAutoProxy
@RequiredArgsConstructor
@Slf4j
public class TrackAsyncTimeAspect {
    private final AsyncMethodExecLogServiceDecorator asyncMethodExecLogServiceDecorator;

    @Pointcut("execution(@lev.ostrov.T1TrackTimeProject.annotation.TrackAsyncTime public void add*(..)) ")
    public void asyncTrackTimePointcut() {
    }

    @Around("asyncTrackTimePointcut()")
    public Object aroundExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        return CompletableFuture.runAsync(() -> {
            String methodName = joinPoint.getSignature().toShortString();
            Date executionTime = new Date();
            log.debug("Before method execution");
            Long beforeMethodTimeLong = System.currentTimeMillis();
            // Выполнение самого метода
            Object result = null;
            try {
                result = joinPoint.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            log.debug("After method execution");
            Long afterMethodTimeLong = System.currentTimeMillis();
            log.info("Время асинхронного выполнения метода {} составляет {} мс ", methodName, (afterMethodTimeLong - beforeMethodTimeLong));
            Long executionDuration = afterMethodTimeLong - beforeMethodTimeLong;

            // Сохранение информации в базу данных
            asyncMethodExecLogServiceDecorator.asyncLogMethodExec(methodName, executionTime, executionDuration);

        });
    }
}