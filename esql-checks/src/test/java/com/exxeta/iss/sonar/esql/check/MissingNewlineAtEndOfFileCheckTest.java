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

import com.exxeta.iss.sonar.esql.checks.verifier.EsqlCheckVerifier;

public class MissingNewlineAtEndOfFileCheckTest {

  private static final String DIRECTORY = "src/test/resources/MissingNewlineAtEndOfFileCheck";
  private static final MissingNewlineAtEndOfFileCheck check = new MissingNewlineAtEndOfFileCheck();

  @Test
  public void nok() {
    EsqlCheckVerifier.issues(check, new File(DIRECTORY, "newlineAtEndOfFile_nok.esql"))
      .next()
      .noMore();
  }

  @Test
  public void ok() {
    EsqlCheckVerifier.issues(check, new File(DIRECTORY, "newlineAtEndOfFile_ok.esql"))
      .noMore();
  }

  @Test
  public void empty_lines() {
    EsqlCheckVerifier.issues(check, new File(DIRECTORY, "empty_lines.esql"))
      .noMore();
  }

  @Test
  public void empty() {
    EsqlCheckVerifier.issues(check, new File(DIRECTORY, "empty.esql"))
      .noMore();
  }

}
