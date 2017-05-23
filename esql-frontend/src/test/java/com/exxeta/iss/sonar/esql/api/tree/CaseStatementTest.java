/*
 * Sonar ESQL Plugin
 * Copyright (C) 2013-2017 Thomas Pohl and EXXETA AG
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
package com.exxeta.iss.sonar.esql.api.tree;

import static com.exxeta.iss.sonar.esql.utils.Assertions.assertThat;

import org.junit.Test;

import com.exxeta.iss.sonar.esql.api.tree.Tree.Kind;

public class CaseStatementTest {

	@Test
	public void whenClause() {
		assertThat(Kind.WHEN_CLAUSE).matches("WHEN minimum + 0 THEN").matches("WHEN minimum + 0 THEN SET a=1;")
				.matches("WHEN minimum + 0 THEN SET a=1; SET b=3;");
	}

	@Test
	public void caseStatement() {
		assertThat(Kind.CASE_STATEMENT)
				.matches("CASE size\n  WHEN minimum + 0 THEN\n"
						+ "    SET description = 'small';\n  WHEN minimum + 1 THEN\n"
						+ "    SET description = 'medium';\n" + "       \n  WHEN minimum + 2 THEN\n"
						+ "    SET description = 'large';\n    CALL handleLargeObject();\n"
						+ "  ELSE\n    SET description = 'unknown';\n" + "    CALL handleError();\nEND CASE;")
				.matches("CASE\n" + "	WHEN i <> 0 THEN\n" + "    CALL handleI(i);\n" + "  WHEN j> 1 THEN\n"
						+ "    CALL handleIZeroAndPositiveJ(j);\n" + "  ELSE\n" + "    CALL handleAllOtherCases(j);\n"
						+ "END CASE;");
	}
}
