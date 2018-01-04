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

import com.exxeta.iss.sonar.esql.api.tree.expression.CallExpressionTree;
import com.exxeta.iss.sonar.esql.api.tree.expression.IdentifierTree;
import com.exxeta.iss.sonar.esql.api.visitors.DoubleDispatchVisitorCheck;

public abstract class AbstractDoNotUseFunctinCheck extends DoubleDispatchVisitorCheck{

	public abstract String getMessage();
	public abstract String getFunctionName();
	
	@Override
	public void visitCallExpression(CallExpressionTree tree) {
		if (tree.functionName() instanceof IdentifierTree 
			&& getFunctionName().equalsIgnoreCase(((IdentifierTree)tree.functionName()).name())){
			addIssue(tree.functionName(), getMessage());
		}
		super.visitCallExpression(tree);
	}
	
}
