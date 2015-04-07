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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link Rot13Writer}.
 *
 * @author Carl Harris
 */
public class Rot13WriterTest {

  private File file;

  @Before
  public void setUp() throws Exception {
    file = File.createTempFile("rot13", ".txt");
    file.deleteOnExit();
  }

  @After
  public void tearDown() throws Exception {
    assertThat(file.delete(), is(true));
  }

  @Test
  public void testWriteWithoutHeader() throws IOException {
    Rot13Writer writer = new Rot13Writer(new FileWriter(file));
    writer.write("Why did the chicken cross the road?\n");
    writer.flush();
    writer.close();
    BufferedReader reader = new BufferedReader(new FileReader(file));
    assertThat(reader.readLine(),
        is(equalTo("Jul qvq gur puvpxra pebff gur ebnq?")));
    reader.close();
  }

  @Test
  public void testWriteWithHeader() throws IOException {
    Rot13Writer writer = new Rot13Writer(new FileWriter(file), "#ROT13");
    writer.write("Why did the chicken cross the road?\n");
    writer.flush();
    writer.close();
    BufferedReader reader = new BufferedReader(new FileReader(file));
    assertThat(reader.readLine(),
        is(equalTo("#ROT13")));
    assertThat(reader.readLine(),
      is(equalTo("Jul qvq gur puvpxra pebff gur ebnq?")));
    reader.close();
  }

}
