package ec.lab.aop;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AroundMethod implements MethodInterceptor {

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {

		System.out.println("Method name : " + methodInvocation.getMethod().getName());
		
		System.out.println("Method arguments : " + Arrays.toString(methodInvocation.getArguments()));

		// same with MethodBeforeAdvice
		System.out.println("Around/before method call");

		try {
			// proceed to original method call
			Object result = methodInvocation.proceed();

			// same with AfterReturningAdvice
			System.out.println("Around/after method call");

			return result;

		} catch (IllegalArgumentException e) {
			System.out.println("Throw exception");
			throw e;
		}
	}
}