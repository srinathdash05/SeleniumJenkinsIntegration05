package commomFunctions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;


public class RetryAnalyzerListenerTest implements IAnnotationTransformer{
	
	public void transform(ITestAnnotation annotation, Class testclass, Constructor testConstructor,  Method testMethod ) {
		
		annotation.setRetryAnalyzer(RetryAnalyzerTest.class);
		
	}
	
	
	
	

}
