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

import java.nio.CharBuffer;

import org.junit.Test;

/**
 * Unit tests for {@link Rot13}.
 *
 * @author Carl Harris
 */
public class Rot13Test {

  @Test
  public void testRotateChar() throws Exception {
    assertThat(Rot13.rotate((char) ('A' - 1)), is(equalTo((char)('A' - 1))));
    assertThat(Rot13.rotate('A'), is(equalTo((char)('A' + 13))));
    assertThat(Rot13.rotate('M'), is(equalTo((char)('M' + 13))));
    assertThat(Rot13.rotate('N'), is(equalTo((char)('N' - 13))));
    assertThat(Rot13.rotate('Z'), is(equalTo((char)('Z' - 13))));
    assertThat(Rot13.rotate((char) ('Z' + 1)), is(equalTo((char)('Z' + 1))));
    assertThat(Rot13.rotate((char) ('a' - 1)), is(equalTo((char)('a' - 1))));
    assertThat(Rot13.rotate('a'), is(equalTo((char)('a' + 13))));
    assertThat(Rot13.rotate('m'), is(equalTo((char)('m' + 13))));
    assertThat(Rot13.rotate('n'), is(equalTo((char)('n' - 13))));
    assertThat(Rot13.rotate('z'), is(equalTo((char)('z' - 13))));
    assertThat(Rot13.rotate((char) ('z' + 1)), is(equalTo((char)('z' + 1))));
  }

  @Test
  public void testRotateCharSequence() throws Exception {
    String input = "Why did the chicken cross the road?";
    assertThat(Rot13.rotate(input),
        is(equalTo("Jul qvq gur puvpxra pebff gur ebnq?")));
  }

  @Test
  public void testRotateCharArray() throws Exception {
    char[] buf = "Why did the chicken cross the road?".toCharArray();
    char[] expected = "Jul qvq gur puvpxra pebff gur ebnq?".toCharArray();
    Rot13.rotate(buf);
    assertThat(buf, is(equalTo(expected)));
  }

  @Test
  public void testRotateCharArraySegment() throws Exception {
    char[] buf = "Why did the chicken cross the road?".toCharArray();
    char[] expected = "Why did gur puvpxra cross the road?".toCharArray();
    Rot13.rotate(buf, 8, 11);
    assertThat(buf, is(equalTo(expected)));
  }

  @Test
  public void testRotateCharBuffer() throws Exception {
    char[] input = "Why did the chicken cross the road?".toCharArray();
    CharBuffer buf = CharBuffer.wrap(input);
    char[] expected = "Jul qvq gur puvpxra pebff gur ebnq?".toCharArray();
    Rot13.rotate(buf);
    assertThat(buf.array(), is(equalTo(expected)));
  }

  @Test
  public void testRotateCharBufferSegment() throws Exception {
    char[] input = "Why did the chicken cross the road?".toCharArray();
    CharBuffer buf = CharBuffer.wrap(input);
    char[] expected = "Why did gur puvpxra cross the road?".toCharArray();
    Rot13.rotate(buf, 8, 11);
    assertThat(buf.array(), is(equalTo(expected)));
  }

}

