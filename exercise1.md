# First Exercise

If you start the server with

    lein ring server-headless
    
and open [http://localhost:3000] in a browser you will receive a 404.

## Step 1

For defining the different routs for our pages we use 
[Compojure](https://github.com/weavejester/compojure).
Add a route in `src/imagizer/core.clj` so that the server will return a simple 
string saying `Welcome to imagizer!`


## Step 2

To be able to make the page looking much better, we want to return HTML using 
[Hiccup](https://github.com/weavejester/hiccup).

Change `core.clj` so that you are returning a simple HTML page with some 
header saying `Welcome to imagizer!`. You can use the `hiccup/html5` function for that.

## Step 3 

Because we also want to have some styling we prepared a page layout in 
`src/imagizer/view.clj`.

The `layout` function expects one parameter which contains the HICCUP structure
for the page content. Use this function so that your page gets some layout.