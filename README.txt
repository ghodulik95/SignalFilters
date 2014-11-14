Please note that I misunderstood the assignment and began
coding.  If my design is confusing, please feel free to look at
the code, but note that it is not 100% complete. 

This file contains descriptions of each interface, abstract class,
and class in my design.  I have also included a PNG image of
this heirarchy.  Please use the image, this text file, and
the included code to get an understanding of my design.

GenericFilter is an interface with Generic T that specifies
the public functions of a filter, which is just the one function
filter(T input) which outputs a T.

Filter is an abstract class that implements GenericFilter.
It sets up the general outline for how a filter works:
it processes an input, and returns the local output.

IdentityFilter extends Filter, and overrides
the processInput function to just set the output to the
current input.

FilterCascade extends Filter.  It's constructor
takes a list of filters, and then when processing the input
it puts the input through each filter in the list.

NBuffer is an auxiliary class I made to store N generic
objects.  It is essentially a FIFO queue, except it only
has a push function, which will return the oldest object
in the list if and only if the list is full.
It also has an iterator function that returns an iterator of
the list of objects.

FilterN is an abstract class that extends Filter.  It
initializes an NBuffer which will store the last N inputs.

ComparableFilter is an abstract class that extends Filter.
It's generic object must extend Comparable.  ComparableFilter
calls an abstract function compare() when processing the input.
This function will decide whether the current input should become
the output based on some condition in compare(), ie maximum or min.

MaxFilter extends ComparableFilter, setting the compare function
such that if the current input is greater than the previous (via
compareTo() function), it sets the output to the current input.

MinFilter extends ComparableFilter, setting the compare function
such that if the current input is less than the previous (via
compareTo() function), it sets the output to the current input.

ComparableFilterN is an abstract class that extends FilterN.
ComparableFilterN works similarly to ComparableFilter, where
an abstract compare() function is made to decide if the current
input should be the next input, except
it must keep track of how many inputs there has been since the
most recent update of the output.
When deciding the next output, it must check to see
if there has been N inputs since the last update of the output,
and if there has, it must use the abstract compare() function
to find the next output.

MaxFilterN extends ComparableFilterN, setting the compare function
such that if the current input is greater than the previous (via
compareTo() function), it returns true.

MinFilterN extends ComparableFilterN, setting the compare function
such that if the current input is less than the previous (via
compareTo() function), it returns true.

AveragingFilter extends Filter, enforcing a double input and
keeping track of the number of inputs there has been.
It simple overrides the processInput function such that the 
next output is set to be the average. This is simple arithemetic:
size++;
average = (((average * (size - 1)) + input) / size);

AveragingFilterN extends FilterN, enforcing a double input and
keeping track of the number of inputs there has been .
However, once the size reaches n, the calculation for the average
changes, subtracting the oldest input an adding the newest:
average = average - (last / N) + (input / N);

ScalarLinearFilter extends Filter, and implements the scalar filter
described in the assignment. It uses NBuffer to remember previous
inputs and outputs.

FIRFilter extends Filter, and expresses commonality with
ScalarLinearFilter through containment.  It simple initializes
a ScalarLinearFilter with an empty a input and the processInput
function simple sets the output to the output of calling filter
on the contained ScalarLinearFilter.

GainFilter extends Filter, and expresses commonality with
FIRFilter through containment.  It simply initializes a
FIRFilter with an b input of only one element, where b1 = the gain.
The processInput function simple sets the output to the output
of calling filter on the contained FIRFilter.

BinomialFilter extends Filter, and expresses commonality with
FIRFilter through containment.  It simply initializes a FIRFIlter
with a b input of the binomial coefficients up to N.
The processInput function simple sets the output to the output
of calling filter on the contained FIRFilter.

ERROR Handling:
Some of these classes will not allow null input.  Therefore
a null check in processInput() and factory methods will throw
a NullPointerException.

Testing:
For each concrete class, I test filtering several inputs.

Handling optional reset:
reset() is an abstract function in Filter.  Each class may
handle reset a little differently so it must be overwritten.
For example, the reset for ScalarLinearFilter is very specific,
but in AveragingFilter, it would just be resetting the calculated
average and the size.
