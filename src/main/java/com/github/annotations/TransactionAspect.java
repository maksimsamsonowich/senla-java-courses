package com.github.annotations;

import com.github.database.ConnectionHolder;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Aspect
@Component
@AllArgsConstructor
public class TransactionAspect {

    private final ConnectionHolder connectionHolder;

    @Around("@annotation(Transaction)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        final Connection connection = connectionHolder.getConnection();
        try {
            connection.setAutoCommit(false);
            final Object proceed = joinPoint.proceed();
            connection.commit();
            return proceed;
        } catch (RuntimeException ex) {
            connection.rollback();
            throw ex;
        } catch (Exception ex) {
            connection.commit();
            throw ex;
        } finally {
            connection.setAutoCommit(true);
        }
    }


}
