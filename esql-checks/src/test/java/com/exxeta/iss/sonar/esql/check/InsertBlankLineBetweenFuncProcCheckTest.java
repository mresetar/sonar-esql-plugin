/*
 * Sonar ESQL Plugin
 * Copyright (C) 2013-2018 Thomas Pohl and EXXETA AG
 * http://www.exxeta.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.exxeta.iss.sonar.esql.check;

import java.io.File;

import org.junit.Test;

import com.exxeta.iss.sonar.esql.api.EsqlCheck;
import com.exxeta.iss.sonar.esql.checks.verifier.EsqlCheckVerifier;

/**
 * @author C50679
 *
 */
public class InsertBlankLineBetweenFuncProcCheckTest {
	@Test
	public void test() {
		EsqlCheck check = new InsertBlankLineBetweenFuncProcCheck();
		EsqlCheckVerifier.issues(check, new File("src/test/resources/InsertBlankLineBetweenFuncProc.esql"))
		.next().atLine(7).withMessage("Insert one blank line between functions and procedures.")
		.next().atLine(12).withMessage("Insert one blank line between functions and procedures.").noMore();
	}

}
