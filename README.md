rot13
=====

[![Build Status](https://travis-ci.org/soulwing/rot13.svg?branch=master)](https://travis-ci.org/soulwing/rot13)

A Java implementation of the [ROT-13] (http://en.wikipedia.org/wiki/ROT13)
symmetric substitution cipher, frequently used to obscure possibly objectionable 
text in messages such as those posted on USENET. Also used to hide punchlines, 
spoilers, etc.

The ROT-13 algorithm provides *no cryptographic security*.

This implementation is licensed under ASL 2.0.

Utility Methods
---------------

The `Rot13` class provides static utility methods for performing ROT-13 
operations on strings, character arrays, and the like.

For example, rotating a string can be accomplished like this:

```
String input = "Why did the chicken cross the road?";
assert Rot13.rotate(input) == "Jul qvq gur puvpxra pebff gur ebnq?";
```

Reading/Writing ROT-13 Resources
--------------------------------

If the `Rot13Reader` and `Rot13Writer` classes provide the ability to read
and write simple files containing rotated text.  For example, supposed we have 
a file named `badjoke.txt` containing this rotated text:

```
Jul qvq gur puvpxra pebff gur ebnq?
Gb trg gb gur bgure fvqr!
```

We can easily read this file using `Rot13Reader`:

```
BufferedReader reader = new BufferedReader(
  new Rot13Reader(new FileReader("badjoke.txt"));
StringBuilder joke = reader.readLine();
while (line != null) {
  joke.append(line).append(System.lineSeparator());
  line = reader.readLine();
}
System.out.println(joke);
```
 
Continuing the previous example, if we wish to write the contents of `joke` as
rotated text in a file, we could use `Rot13Writer`:

```
Writer writer = new Rot13Writer(new FileWriter("badjoke.txt"));
writer.write(joke.toString());
writer.close();
```

Using a Header to Distinguished Rotated Content
-----------------------------------------------

In some cases you might wish to read a file which may be rotated.  Using 
`Rot13Reader` you can use a simple one line header to distinguish whether the
file contains rotated content.  If the first line of the file matches the
expected header, the rest of the file is assumed to be rotated content.  If 
the first line does not match the expected header, the file is read as plain
text.

For example, suppose we write our bad joke to a file containing a header:

```
#ROT13
Jul qvq gur puvpxra pebff gur ebnq?
Gb trg gb gur bgure fvqr!
```

We can easily read this file using the previous example code, by specifying the
header to match in the `Rot13Reader` constructor:

```
BufferedReader reader = new BufferedReader(
  new Rot13Reader(new FileReader("badjoke.txt"), "#ROT13");
StringBuilder joke = reader.readLine();
while (line != null) {
  joke.append(line).append(System.lineSeparator());
  line = reader.readLine();
}
System.out.println(joke);
```

The advantage here is that the code works the same if the file is in plain text
with no header.

The `Rot13Writer` constructor also accepts a header parameter, which is 
prepended as the first line of the file to write.
 
