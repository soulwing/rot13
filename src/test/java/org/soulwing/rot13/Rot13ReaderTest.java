/*
 * File created on Apr 7, 2015
 *
 * Copyright (c) 2015 Carl Harris, Jr
 * and others as noted
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.soulwing.rot13;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * Unit tests for {@link Rot13Reader}.
 *
 * @author Carl Harris
 */
public class Rot13ReaderTest {

  @Test
  public void testRotatedFileWithoutHeader() throws Exception {
    Reader reader = newRot13Reader("rotatedFileWithoutHeader.txt");
    String input = IOUtils.toString(reader);
    assertThat(input,
        is(equalTo("Why did the chicken cross the road?\nTo get to the other side!\n")));
    reader.close();
  }

  @Test
  public void testRotatedFileWithHeader() throws Exception {
    Reader reader = newRot13Reader("rotatedFileWithHeader.txt", "#ROT13");
    String input = IOUtils.toString(reader);
    assertThat(input,
        is(equalTo("Why did the chicken cross the road?\nTo get to the other side!\n")));
    reader.close();
  }

  @Test
  public void testRotatedFileWithHeaderNoNewLine() throws Exception {
    Reader reader = newRot13Reader("rotatedFileWithHeaderNoNewLine.txt", "#ROT13");
    String input = IOUtils.toString(reader);
    assertThat(input,
        is(equalTo("#ROT13Jul qvq gur puvpxra pebff gur ebnq?\nGb trg gb gur bgure fvqr!\n")));
    reader.close();
  }

  @Test
  public void testPlainFile() throws Exception {
    Reader reader = newRot13Reader("plainFile.txt", "#ROT13");
    String input = IOUtils.toString(reader);
    assertThat(input,
        is(equalTo("Why did the chicken cross the road?\nTo get to the other side!\n")));
    reader.close();
  }

  @Test
  public void testPlainFileWithSimilarHeader() throws Exception {
    Reader reader = newRot13Reader("plainFileWithSimilarHeader.txt", "#ROT13");
    String input = IOUtils.toString(reader);
    assertThat(input,
        is(equalTo("#ROT42\nWhy did the chicken cross the road?\nTo get to the other side!\n")));
    reader.close();
  }



  private Reader newRot13Reader(String resource) {
    return newRot13Reader(resource, null);
  }

  private Reader newRot13Reader(String resource, String header) {
    return new Rot13Reader(new InputStreamReader(
        getClass().getClassLoader().getResourceAsStream(resource)), header);
  }

}
