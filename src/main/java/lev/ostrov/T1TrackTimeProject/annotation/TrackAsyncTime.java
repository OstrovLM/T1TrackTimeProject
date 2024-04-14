package lev.ostrov.T1TrackTimeProject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для aсинхронного сохранения
 * данных о времени выполнения метода
 * работает в сочетании @TrackAsyncTime public void add*(..))
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TrackAsyncTime {
}
