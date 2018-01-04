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

import java.util.List;
import java.util.Set;

import org.sonar.check.Rule;

import com.exxeta.iss.sonar.esql.api.EsqlNonReservedKeyword;
import com.exxeta.iss.sonar.esql.api.tree.PathElementNameTree;
import com.exxeta.iss.sonar.esql.api.tree.Tree;
import com.exxeta.iss.sonar.esql.api.tree.Tree.Kind;
import com.exxeta.iss.sonar.esql.api.visitors.SubscriptionVisitorCheck;
import com.exxeta.iss.sonar.esql.lexer.EsqlReservedKeyword;
import com.exxeta.iss.sonar.esql.tree.impl.lexical.InternalSyntaxToken;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * This Java Class Is created to ensure that all the keywords in esql file
 * should be in UPPER CASE
 * 
 * @author sapna singh
 *
 */

@Rule(key = "KeyWordCaseCheck")
public class KeyWordCaseCheck extends SubscriptionVisitorCheck {
	private static final Set<String> nonReservedKeywords = ImmutableSet.copyOf(EsqlNonReservedKeyword.keywordValues());
	private static final Set<String> reservedKeywords = ImmutableSet.copyOf(EsqlReservedKeyword.keywordValues());
	private static final String MESSAGE = "This keyword should be in uppercase.";

	@Override
	public List<Kind> nodesToVisit() {
		return ImmutableList.of(Tree.Kind.TOKEN);
	}

	@Override
	public void visitNode(Tree tree) {
		String value = ((InternalSyntaxToken) tree).text();
		String upperCase = value.toUpperCase();
		if (!value.equals(upperCase)
				&& (reservedKeywords.contains(upperCase) || nonReservedKeywords.contains(upperCase))
				&& !(tree.parent() instanceof PathElementNameTree)) {
			addIssue(tree, MESSAGE);
		}
		super.visitNode(tree);
	}
}
