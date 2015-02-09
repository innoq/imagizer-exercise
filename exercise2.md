# Second Exercise

We added some features to the imagizer app after the last exercise.
The start page now contains a search field where you can enter a URL where imagizer shall search for images. After pressing `search` there shall be displayed a list with all images found at the URL. (For now you will not see any images.)

## Step 1

To be able to show the images we have to load the given web page. So we need to trigger HTTP requests from our source code.

We will use [clj-http](https://github.com/dakrone/clj-http) for that. Add the dependency to project.clj.

## Step 2

All the HTTP requests take place in the namespace `imagizer.images.retrieval`. Require the `clj-http.client` namespace and implement the function `load-html` that accepts one parameter, the url, and sends then a HTTP GET to this url and returns the content of the response body.

This function is called by the function `imagizer.core.images-page` which is responsible for displaying all images of the given url. It will take the HTML returned by `load-html`, parse it and filter out all images. You do not have to add anything here.
