# Fourth Exercise

We will now have a closer look how we can use existing Java classes in Clojure.

There is support for image conversion in the JDK. In our case, each conversion is described
by a 3x3 matrix in `imagizer.images.processing`, e.g. the `blur-filter`.

## Step 1

Implement the function `convert` in `imagizer.images.processing` that expects three parameters

  * `op`: the name of the filter as string
  * `from`: the name of the origin image file
  * `to`: the name of the file where the converted image shall be saved
  
The filter matrix can be found by looking it up in the `conversions` map.
  
Here is the Java code that does exactly what we need. `filter` has to be the one-dimensional
float array containing the filter.

    java.awt.image.BufferedImage src = javax.imageio.ImageIO.read(new java.io.File(from));
    java.awt.image.ConvolveOp conv = new java.awt.image.ConvolveOp(new java.awt.image.Kernel(3, 3, filter));
	java.io.File dest = new java.io.File(to);
	java.awt.image.BufferedImage converted = conv.filter(src, null);
	javax.imageio.ImageIO.write(converted, "jpg", dest);

You can use the import section in the namespace definition to avoid 
using fully qualified class names.