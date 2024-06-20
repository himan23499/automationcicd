package TestFramework.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	int count=0;
	int maxTry=1;

	//IRetryAnalyzer used to rerun failed test cases again after failure
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<maxTry) {
			count++;
			return true;
		}else {
			return false;
		}
//		return (count++<maxTry);
//		return false;
	}

}
