spans
-----

an immutable kotlin library for constructing nested span tree with arbitrary styles, including
translations with replacements, and converting the result to SpannableString (or other structures via
the NodeReader interface)

* Development

After cloning the repository to a development environment, development tasks can be managed with gradle:

    `./gradlew tasks`

* Enhancements

** Should
TODO: Eliminate need for StyleBuilder.build() (just have StyleBuilder be a StyleReader)
TODO: Rename all the types and methods to be more easily understood
TODO: Use callbacks for subSections to take advantage of kotlin's "it" so they need not refer over and over to top level factories `spans` and `styles`
TODO: Use get() fields for no-arg methods of the style and other interfaces
TODO: Memoize all instance computations (even functions with same arguments can be memoized)
TODO: Support %d, %dm, %dd, %dh - tough because we would need to retrieve subSpans as number and date objects (now we have only fullText())
TODO: Automate tests upon build

** Could
TODO: Publish as open source and consume in CD
TODO: Add individual unit tests with code coverage for every class