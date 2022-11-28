# The service for an image theme extraction

The service to get rid of the image theme extraction problem.

**Latest version**: 1.0.0

## The API route

You should use route `getTheme/[url]/{format?}[?url=URL]`. This route allows to get the theme by image. 

`format` is an optional route part that can be `rgb255` or `hex`. The first one means 'return
result represented by 3 integer numbers in range [0; 255] in RGB format'. The second one allows to return #hex (a 
string of `#` and 6 hex digits like `#dedb3f`) value.
For detailed information about returned JSON model see 
[this](src/main/kotlin/me/vldf/colors/serializers/Rgb255ColorSerializer.kt) and 
[this](src/main/kotlin/me/vldf/colors/serializers/RgbHexColorSerializer.kt) serializers.

`url` is an optional part that allows to pass the URL of image. This URL should be provided by the `url` query 
parameter.

Examples:
1. `http://localhost/api/getTheme/url/rgb255?url=example.com` returns colors by image from example.com in RGB format.
2. `http://localhost/api/getTheme/hex` (and an image in PUT's body) returns colors by image from the body in HEX format.

## Building
The [multi-stage building](https://docs.docker.com/build/building/multi-stage/) is used here. Run 
`docker build .` to build and start the service. Also, you can save the image with custom name like that: 
`docker build -t dominant-colors-extractor .`. Then you can run it via `docker run dominant-colors-extractor:latest`.

## Running
The container (and the http server too) will be run immediately after build. Also, in case of `-t` usage you can run it
via command `docker run dominant-colors-extractor:latest`. Port `8080` will be automatically used as default, but you 
can set the port via `docker run -p 12312:8080 dominant-colors-extractor:latest`
