package commomFunctions;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerTest implements IRetryAnalyzer{

	@Override
	public boolean retry(ITestResult result) {
		
		int count =1;
		int maxCount =2;
		if(count<maxCount){
			count++;
			return true;
		}
		
		return false;
	}

}
