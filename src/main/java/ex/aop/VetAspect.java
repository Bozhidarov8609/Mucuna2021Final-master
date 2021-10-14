package ex.aop;


import ex.model.service.VetServiceModel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Aspect
@Component
public class VetAspect {

    @Pointcut("execution(* ex.service.impl.VetServiceImpl.findAll())")
    public void trackNotApprove() {}

    @AfterReturning(pointcut = "trackNotApprove()", returning = "vetServiceModel")
    public void loggingAfterReturning(JoinPoint joinPoint, Object vetServiceModel) throws IOException {
        List<VetServiceModel> vetsCount = (List<VetServiceModel>) vetServiceModel;
        System.out.println("Have "+ vetsCount.size()+" vets");
    }
}