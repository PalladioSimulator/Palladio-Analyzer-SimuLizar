/**
 * 
 */
package de.mdelab.eurema.interpreter.condition.test;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.mdelab.eurema.interpreter.condition.ConditionExpParser;
import de.mdelab.eurema.interpreter.condition.QueryExecutionInformation;

/**
 * @author thomas
 * 
 */
public class ConditionExpParserTest {

	@BeforeClass
	public static void initTest() {

		parser = new ConditionExpParser();

		qei = new QueryExecutionInformation() {

			@Override
			public long retrieveTime(String query) {
				return System.currentTimeMillis() - 3600;
			}

			@Override
			public long retrieveCount(String query) {
				return 6;
			}
		};
	}

	static QueryExecutionInformation qei;
	static ConditionExpParser parser;

	static String exp_1 = "C_SINCE(a::t) = 6"; // "2 <= 2 AND 1=1";
	static boolean exp1_result = true;
	static String exp_2 = "CURRENT_TIME < 5";
	static boolean exp2_result = false;
	static String exp_3 = "(5=5 OR 2=2) AND 3>6";
	static boolean exp3_result = false;
	static String exp_4 = "5=5 OR (2=2 AND 3>6)";
	static boolean exp4_result = true;
	static String exp_5 = ("((2 * CURRENT_TIME) + 1) / 2 > 3600 + 3600");
	static boolean exp5_result = true;
	static String exp_6 = "(C_SINCE(op 1::na me) <= 5 AND CURRENT_TIME > ((122324 + 3600) + 3600) + 1) OR T_WHEN(op2::name) >= CURRENT_TIME - 3600";
	static boolean exp6_result = true;
	static String exp_7 = ("((1 = 1 AND 1*1+1*1 >= 2) OR 1=2) AND (1 = 1 OR 2 > 1+1*CURRENT_TIME)");
	static boolean exp7_result = true;
	static String exp_8 = "(C_SINCE(op 1) <= 5 AND CURRENT_TIME > ((122324 + 3600) + 3600) + 1) OR T_WHEN(op2) >= CURRENT_TIME - 3600";
	static boolean exp8_result = true;
	
	@Test
	public void testExp_1() {
		boolean result = parser.parseExp(exp_1, qei);
		Assert.assertEquals(exp1_result, result);
	}

	@Test
	public void testExp_2() {
		boolean result = parser.parseExp(exp_2, qei);
		Assert.assertEquals(exp2_result, result);
	}

	@Test
	public void testExp_3() {
		boolean result = parser.parseExp(exp_3, qei);
		Assert.assertEquals(exp3_result, result);
	}

	@Test
	public void testExp_4() {
		boolean result = parser.parseExp(exp_4, qei);
		Assert.assertEquals(exp4_result, result);
	}

	@Test
	public void testExp_5() {
		boolean result = parser.parseExp(exp_5, qei);
		Assert.assertEquals(exp5_result, result);
	}

	@Test
	public void testExp_6() {
		boolean result = parser.parseExp(exp_6, qei);
		Assert.assertEquals(exp6_result, result);
	}
	
	@Test
	public void testExp_7() {
		boolean result = parser.parseExp(exp_7, qei);
		Assert.assertEquals(exp7_result, result);
	}
	
	@Test
	public void testExp_8() {
		boolean result = parser.parseExp(exp_8, qei);
		Assert.assertEquals(exp8_result, result);
	}
	
	@Test
	public void testTAASConditions() {
		// C_SINCE(no failures) > 5
		Assert.assertEquals(true, parser.parseExp("C_SINCE(no failures) > 5", qei));
		// C_SINCE(RepairAP::Planned) = 0
		Assert.assertEquals(false, parser.parseExp("C_SINCE(RepairAP::Planned) = 0", qei));
	}

	@AfterClass
	public static void cleanUpTest() {
		parser = null;
		qei = null;
	}

}
