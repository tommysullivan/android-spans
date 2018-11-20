spans
-----

an immutable kotlin library for constructing nested span tree with arbitrary styles, including
translations with replacements, and converting the result to SpannableString (or other structures via
the NodeReader interface)

* Development

After cloning the repository to a development environment, development tasks can be managed with gradle:

    `./gradlew tasks`

* Enhancements

TODO: Support %d, %dm, %dd, %dh - tough because we would need to retrieve subSpans as number and date objects (now we have only fullText())
TODO: Use get() fields for no-arg methods of the style and other interfaces
TODO: Memoize all instance computations (even functions with same arguments can be memoized)
